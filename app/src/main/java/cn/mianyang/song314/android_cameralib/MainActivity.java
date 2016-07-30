package cn.mianyang.song314.android_cameralib;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.mianyang.song314.android_cameralib.event.OnCameraStartPreviewEvent;
import cn.mianyang.song314.android_cameralib.hal.CameraManager;
import cn.mianyang.song314.android_cameralib.hal.ICamera;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {



    @BindView(R.id.bg_welcome)
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        Logger
                .init("song")                 // default PRETTYLOGGER or use just init()
                .methodCount(1)                 // default 2
                .hideThreadInfo()
//                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(0);             // default 0

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);



        OttoBus.getInstance().register(this);



//        Observable.empty()
//                .delay(1, TimeUnit.SECONDS)
//                .doOnCompleted(new Action0() {
//                    @Override
//                    public void call() {
//                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                        ft.replace(R.id.content_fragment, new BaseFragment());
//                        ft.addToBackStack("d");
//                        ft.commit();
//                    }
//                })
//                .subscribe();
      }


    @Override
    protected void onStart() {
        super.onStart();
        mImageView.setVisibility(View.VISIBLE);
        ICamera camera = CameraManager.getCamera();
        Observable.just(camera)
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<ICamera>() {
                    @Override
                    public void call(ICamera iCamera) {
                        iCamera.open();
                        SystemClock.sleep(2000);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ICamera>() {
                    @Override
                    public void call(ICamera iCamera) {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_fragment, CameraFragment.newInstance("", ""));
                        ft.addToBackStack("d");
                        ft.commit();
                        YoYo.with(Techniques.FadeOut)
                                .duration(500)
                                .withListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        mImageView.setVisibility(View.INVISIBLE);
//                                        mImageView.setBackground(null);
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                })
                                .playOn(mImageView);
                    }
                });

    }

//    public void onEvent(OnCameraStartPreviewEvent event) {
//        mImageView.setVisibility(View.INVISIBLE);
//    }

    @Override
    protected void onDestroy() {
        OttoBus.getInstance().unregister(this);
        super.onDestroy();
    }
}
