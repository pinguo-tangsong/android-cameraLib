package cn.mianyang.song314.android_cameralib;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_fragment, CameraFragment.newInstance("", ""));
        ft.commit();
      }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
