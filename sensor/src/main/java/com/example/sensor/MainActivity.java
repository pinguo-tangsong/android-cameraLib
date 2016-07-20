package com.example.sensor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LibSensor.TipListener {
    private static final String TAG = "sensor";


    private LibSensor mLibSensor;

    float[] magneticFieldValues = new float[3];

    private TextView textview;
    private TextView textview1;
    private TextView mTvCenter;
    private TextView textview3;
    private TextView textview4;
    private TextView textview5;
    private TextView textview6;
    private TextView textview7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLibSensor = new LibSensor(this.getApplicationContext());
        mLibSensor.setFlatDelta(0.5f);
        mLibSensor.setListener(this);
        setContentView(R.layout.activity_main);

        textview = (TextView) findViewById(R.id.textview);
        textview1 = (TextView) findViewById(R.id.textview1);
        mTvCenter = (TextView) findViewById(R.id.tv_center);
        textview3 = (TextView) findViewById(R.id.textview3);
        textview4 = (TextView) findViewById(R.id.textview4);
        textview5 = (TextView) findViewById(R.id.textview5);
        textview6 = (TextView) findViewById(R.id.textview6);
        textview7 = (TextView) findViewById(R.id.textview7);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mLibSensor.registerListener();

    }

    @Override
    protected void onPause() {
        mLibSensor.unregisterListener();
        super.onPause();
    }


    @Override
    public void onTip(LibSensor.CaptureType type) {
        switch (type) {
            case FLAT:
                shuiPing(type);
                break;
        }
    }

    private void shuiPing(LibSensor.CaptureType type) {
        switch (type.moveType) {
            case UP:
                mTvCenter.setText("");
                break;
            case BOTTOM:
                mTvCenter.setText("");
                break;
            case LEFT:
                mTvCenter.setText("");
                break;
            case RIGHT:
                mTvCenter.setText("");
                break;
            case OK:
                mTvCenter.setText("水平拍");
                break;
        }
    }
}
