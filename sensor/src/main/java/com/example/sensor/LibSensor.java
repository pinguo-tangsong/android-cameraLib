package com.example.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by tianwenjie on 7/20/16.
 */
public class LibSensor implements SensorEventListener {

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
        UP,BOTTOM,LEFT,RIGHT
        //竖
        //横
    }

    private SensorManager mSensorManager;
    private Sensor mSensor;

    private CaptureType mType = CaptureType.FLAT;
    private TipListener mListener;

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

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() != Sensor.TYPE_ORIENTATION) {
            return;
        }
//            magneticFieldValues = sensorEvent.values;
        float X = sensorEvent.values[0];
        float Y = sensorEvent.values[1];
        float Z = sensorEvent.values[2];


//            String x = "x = " + String.format("0:0000.000000"+ X_lateral);
//            String y = ",y = " + String.format("0:0000.000000"+ Y_longitudinal);
//            String z = ",z = " +String.format("0:0000.000000" + Z_vertical);

        String x = "x = " + X;
        String y = "y = " + Y;
        String z = "z = " + Z;
        Log.i("log", x + y + z);
//        textview1.setText(z);
//        textview.setText(y);
//        textview7.setText(x);
//
//        if (((Z >= 85 && Z <= 90) || (Z >= -85 && Z <= -80)) && (Y >= -2 && Y <= 2)) {
//            textview2.setText("横拍");
//        } else if ((Y <= -85 && Y >= -90) && (Z <= 2 && Z >= -2)) {
//            textview2.setText("竖拍");
//        } else if ((Y <= 2 && Y >= -2) && ((Z <= 2 && Z >= -2))) {
//            textview2.setText("水平拍");
//        } else {
//            textview2.setText("");
//        }

//           TODO  水平
        if ((Y >= -10 && Y <= 10 ) && (Z >= -10 && Z <= 10)) {
            mType = CaptureType.FLAT;
            if (Y > mFlatDelta && Y < 10) {
                //up
                mType.moveType = Move.UP;
            } else if (Y > -10 && Y < -mFlatDelta) {
                //bottom
                mType.moveType = Move.BOTTOM;
            } else if (Z > mFlatDelta && Z < 10) {
                //left
                mType.moveType = Move.LEFT;
            } else if (Z > -10 && Z < - mFlatDelta) {
                //right
                mType.moveType = Move.RIGHT;
            } else {
                //
                mType.moveType = Move.OK;
            }

            //  TODO 竖拍
            mListener.onTip(mType);
            return;
        }
//        else if ((Y <= -50 && Y >= -95) && (Z <= 10 && Z >= -10)) {
//            if (Y > -85 && Y < -75) {
//                textview3.setText(R.string.ZOOM_IN);
//            } else {
//                textview3.setText("");
//            }
//
//            if (Y > -95 && Y < -90) {
//                textview6.setText(R.string.ZOOM_IN);
//            } else {
//                textview6.setText("");
//
//            }
//            if (Z > 2 && Z < 10) {
//                textview4.setText(R.string.ZOOM_IN);
//            } else {
//                textview4.setText("");
//            }
//            if (Z > -10 && Z < -2) {
//                textview5.setText(R.string.ZOOM_IN);
//            } else {
//                textview5.setText("");
//            }
//
//
//            // TODO  横拍
//        } else if ((Z >= 50 && Z <= 90)) {
//            if (Y >= 3 && Y <= 50) {
//                textview3.setText(R.string.ZOOM_IN);
//            } else {
//                textview3.setText("");
//            }
//            if (Y <= -3 && Y >= -50) {
//                textview6.setText(R.string.ZOOM_IN);
//            } else {
//                textview6.setText("");
//            }
//            if (Z >= 70 && Z <= 85) {
//                textview5.setText(R.string.ZOOM_IN);
//            } else {
//                textview5.setText("");
//            }
//            if( (Y > 50 && Y < -50)) {
//                textview4.setText(R.string.ZOOM_IN);
//            } else {
//                textview4.setText("");
//            }
//
//
//        } else {
//            return;
//        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public interface TipListener {
        void onTip(CaptureType type);
    }
}
