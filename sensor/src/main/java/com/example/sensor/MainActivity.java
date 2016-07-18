package com.example.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "sensor";
    //1.得到传感器
    private SensorManager sm;
    //需要两个Sensor
    private Sensor aSensor;

    float[] magneticFieldValues = new float[3];

    private static TextView textview;
    private static TextView textview1;
    private static TextView textview2;
    private static TextView textview3;
    private static TextView textview4;
    private static TextView textview5;
    private static TextView textview6;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        aSensor = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        textview = (TextView) findViewById(R.id.textview);
        textview1 = (TextView) findViewById(R.id.textview1);
        textview2 = (TextView) findViewById(R.id.textview2);
        textview3 = (TextView) findViewById(R.id.textview3);
        textview4 = (TextView) findViewById(R.id.textview4);
        textview5 = (TextView) findViewById(R.id.textview5);
        textview6 = (TextView) findViewById(R.id.textview6);


    }

    @Override
    protected void onStart() {
        super.onStart();
        sm.registerListener(myListener, aSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        sm.unregisterListener(myListener);
        super.onPause();
    }

    final SensorEventListener myListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION)
                magneticFieldValues = sensorEvent.values;
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
            textview1.setText(z);
            textview.setText(y);
            if ((Y <= 2&& Y >= -2) && ((Z >= 80 && Z <= 90) || (Z >= -90 && Z <= -80))){
                textview2.setText("横拍");
            }else if((Y <= -85 && Y> -90) &&(Z <= 2 && Z >= -2)){
                textview2.setText("竖拍");
            }else if ( (Y <=2 && Y >= -2) && ((Z <=2 && Z >= -2 ))){
                textview2.setText("水平拍");
            }else {
                textview2.setText("");
            }

            if (Z > 2 && Z < 10){
                textview4.setText(R.string.ZOOM_IN);
            }else {
                textview4.setText("");
            }
            if (Z > -10 && Z < -2 ){
                textview5.setText(R.string.ZOOM_IN);
            }else {
                textview5.setText("");
            }
            if (Y > 2 && Y <10){
                textview3.setText(R.string.ZOOM_IN);
            }else {
                textview3.setText("");
            }
            if (Y > -10  && Y <-2 ){
                textview6.setText(R.string.ZOOM_IN);
            }else {textview6.setText("");
            }
//            if (Y > -85 && Y < -75){
//                textview3.setText(R.string.ZOOM_IN);
//            }else {
//                textview3.setText("");
//            }
//            if (Y > -95 && Y < -90){
//                textview6.setText(R.string.ZOOM_IN);
//            }else {
//                textview6.setText("");
//            }if((Z > 70 && Z < 80) &&(Y > -80)){
//                textview5.setText(R.string.ZOOM_IN);
//            }else {
//                textview4.setText("");
//            }
//            if((Z >70 && Z<80)  && (Y < -150)) {
//                textview4.setText(R.string.ZOOM_IN);
//            }else {
//                textview4.setText("");
//            }



        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };




}
