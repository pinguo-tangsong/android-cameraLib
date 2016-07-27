package com.example.sensor;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by tianwenjie on 7/20/16.
 */
public class LibSensor implements SensorEventListener {
    private float YAcquire;
    private float ZAcquire;
    private long mCurrentTimeMillis;
    /**
     * 进入稳定状态后的时间
     */
    private long mStableTimeMillis = -1;

    /**
     * 拍照的类型:水平拍照,竖直拍照,横向拍照
     */
    public enum CaptureType {
        HORIZONTAL,
        VERTICAL,
        FLAT;

        Move moveType;

    }

    private float mFlatDelta = 2;

    /**
     * 用户应该如何矫正手机
     */
    public enum Move {
        //
        OK,
        //水平
        UP, BOTTOM, LEFT, RIGHT
        //竖
        //横
    }

    private SensorManager mSensorManager;
    private Sensor mSensor;

    private CaptureType mType = CaptureType.FLAT;
    private CaptureType vype = CaptureType.VERTICAL;
    private CaptureType hype = CaptureType.HORIZONTAL;

    private TipListener mListener;
    private OnStableListener mStableListener;

    public LibSensor(Context context) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }

    public void setFlatDelta(float delta) {
        mFlatDelta = delta;
    }

    public void registerListener() {
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
    }

    public void unregisterListener() {
        mSensorManager.unregisterListener(this);
    }

    public void setListener(TipListener listener) {
        mListener = listener;
    }

    public void setStableListener(OnStableListener listener) {
        mStableListener = listener;
        mStableTimeMillis = -1;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() != Sensor.TYPE_ORIENTATION) {
            return;
        }
//            magneticFieldValues = sensorEvent.values;
        float X = sensorEvent.values[0];
        float Y = sensorEvent.values[1];
        float Z = sensorEvent.values[2];

        YAcquire = Y;
        ZAcquire = Z;

        mCurrentTimeMillis = System.currentTimeMillis();

        String x = "x = " + X;
        String y = "y = " + Y;
        String z = "z = " + Z;
        Log.i("log", x + y + z);

        float deltaY = Y / 90f * 100f;

        float deltaZ = Z / 90f * 100f;

        if (mListener != null) {
            mListener.onChange(deltaY, deltaZ);
        }

        if ((-3 <= deltaY && deltaY <= 3) && (-2 <= deltaZ && deltaZ <= 2)) {
            mType.moveType = Move.OK;
        } else if ((deltaY <= -97 && Y >= -100) && (-2 <= deltaZ && deltaZ <= 2)) {
            mType.moveType = Move.OK;
        } else {
            mType.moveType =Move.UP;
        }
        Log.i("tt", "y = " + deltaY);
        Log.i("tt", "z = " + deltaZ);
//
//        if ((Y >= -10 && Y <= 10) && (Z >= -10 && Z <= 10)) {
//            mType = CaptureType.FLAT;
//            //检查是否水平
//            if (Y > mFlatDelta && Y < 10) {
//                //up
//                mType.moveType = Move.UP;
//           //     cameraHelpView.setStartT();
//
//            } else if (Y > -10 && Y < -mFlatDelta) {
//                //bottom
//                mType.moveType = Move.BOTTOM;
//            } else if (Z > mFlatDelta && Z < 10) {
//                //left
//                mType.moveType = Move.LEFT;
//            } else if (Z > -10 && Z < -mFlatDelta) {
//                //right
//                mType.moveType = Move.RIGHT;
//            } else {
////                mType.moveType = Move.OK;
//            }


        if (mStableListener != null) {
            //进行稳定检测,满足指定时间,就调用回调方法
            if (mType.moveType == Move.OK) {
                //如果稳定,则准备触发提示
                if (mStableTimeMillis < 0) {
                    //小于0,则是第一次稳定,那么记录下时间
                    mStableTimeMillis = System.currentTimeMillis();
                    mStableListener.onEnterStable();
                } else {
                    //检查时间是否满足
                    if (mCurrentTimeMillis - mStableTimeMillis >= 2000) {
                        mStableListener.onStabled();
                    }
                }
            } else {
                //不稳定,那么清除标记
                mStableTimeMillis = -1;
            }
        }

        mListener.onTip(mType);

    }
//    else
//        if ((Y <= -80 && Y >= -95 ) && (Z <= 10 && Z >= -10)) {
//            //   竖拍
//            if (Y > -85 && Y < -75) {
//                vype.moveType = Move.UP;
//            } else if (Y > -95 && Y < -90) {
//                vype.moveType = Move.BOTTOM;
//            } else if (Z > 2 && Z < 10) {
//                vype.moveType = Move.LEFT;
//            } else if (Z > -10 && Z < -2) {
//                vype.moveType = Move.RIGHT;
//            } else {
//              //  vype.moveType = Move.RIGHT;
////                vype.moveType = Move.OK;
//            }


//            if(vype.moveType == Move.OK){
//                //判断是否进入竖拍
//                if(mStableTimeMillis < 0){
//                    //是否拿到进入竖拍的时间
//                        mStableTimeMillis = System.currentTimeMillis();
//                    //
//                    if(mStableListener !=null) {
//
//                        mStableListener.onEnterStable();
//                    }
//                }else {
//                   // 获取时间后  执行的操作
//                    if(mCurrentTimeMillis - mStableTimeMillis >=2000){
//                        //
//                        if(mStableListener !=null){
//                         mStableListener.onStabled();
//                        }
//                    }
//                }
//            }else {
//                // 如果不平清除数据
//                mStableTimeMillis = -1;
//            }
//
//            mListener.onTip(vype);
//
//            // TODO  横拍
//            return;
//        }
//        else if ((Z >= 80 && Z <= 90) && (Y >= -2 && Y <= 2))  {
//            if (Y >= 3 && Y <= 50) {
//                hype.moveType = Move.UP;
//            } else if (Y <= -3 && Y >= -50) {
//                hype.moveType = Move.BOTTOM;
//            } else if (Z >= 70 && Z <= 85) {
//                hype.moveType = Move.RIGHT;
//            } else if ((Y > 50 && Y < -50)) {
//                hype.moveType = Move.LEFT;
//            } else {
//                hype.moveType = Move.OK;
//            }
//            mListener.onTip(hype);
//            return;
    //     }
    //  }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public interface TipListener {
        void onTip(CaptureType type);

        void onChange(float y, float z);
    }

    public interface OnStableListener {
        void onStabled();

        void onEnterStable();
    }
}
