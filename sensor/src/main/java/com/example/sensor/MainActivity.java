package com.example.sensor;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LibSensor.TipListener, LibSensor.OnStableListener {


    private LibSensor mLibSensor;

    float[] magneticFieldValues = new float[3];


    private CameraHelpView mHelpView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLibSensor = new LibSensor(this.getApplicationContext());
        mLibSensor.setFlatDelta(0.5f);
        mLibSensor.setListener(this);
        mLibSensor.setStableListener(this);
        setContentView(R.layout.activity_main);


        mHelpView = (CameraHelpView) findViewById(R.id.customview);
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
                break;
            case VERTICAL:
                break;
            case HORIZONTAL:
                break;

        }
    }


    @Override
    public void onStabled() {
        Toast.makeText(this, "Takepicture", Toast.LENGTH_SHORT).show();
        mLibSensor.setStableListener(null);
//        模拟拍照
        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(5000);
                mLibSensor.setStableListener(MainActivity.this);
            }
        }.start();
    }

    @Override
    public void onEnterStable() {

    }

    @Override
    public void onChange(float y, float z) {

        if ((-3 <= y && y <= 3) && (-3 <= z && z <= 3)) {
            mHelpView.setStartT(100 - y);
            mHelpView.setStartB(y + 100);
            mHelpView.setStartL(100 - z);
            mHelpView.setStartR(z + 100);

        } else {

            if (y > 0) {
                mHelpView.setStartT(100 - y);
                mHelpView.setStartB(100);
            } else {
                mHelpView.setStartB(y + 100);
                mHelpView.setStartT(100);
            }

            if (z > 0) {
                mHelpView.setStartL(100 - z);
                mHelpView.setStartR(100);

            } else {
                mHelpView.setStartR(z + 100);
                mHelpView.setStartL(100);
            }
        }
    }
}
