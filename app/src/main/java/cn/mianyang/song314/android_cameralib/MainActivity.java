package cn.mianyang.song314.android_cameralib;

import android.content.DialogInterface;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.mianyang.song314.android_cameralib.hal.ICamera;
import cn.mianyang.song314.android_cameralib.hal.LibCamera;
import cn.mianyang.song314.android_cameralib.settings.ExposureSetting;

public class MainActivity extends AppCompatActivity {

    private ICamera mCamera;
    private CameraPreview mPreview;
    private int mCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;

    @BindView(R.id.button_switch)
    Button mBtnSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // Create an instance of Camera
        mCamera = new LibCamera(mCameraId);
        mCamera.open();
        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this);
        mPreview.setCamera(mCamera, mCameraId);
        ViewGroup preview = (ViewGroup) findViewById(R.id.camera_preview);
        preview.addView(mPreview, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mBtnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCamera.stopPreview();
                mCamera.release();

                final int cameraId = mCameraId == Camera.CameraInfo.CAMERA_FACING_BACK ? Camera.CameraInfo.CAMERA_FACING_FRONT : Camera.CameraInfo.CAMERA_FACING_BACK;
                mCameraId = cameraId;
                mCamera = new LibCamera(mCameraId);
                mPreview.setCamera(mCamera,cameraId);
                mPreview.surfaceCreated(mPreview.getHolder());
            }
        });
    }

    @OnClick(R.id.button_capture)
    public void expore() {
        final ExposureSetting exposureSetting = new ExposureSetting();
        if (exposureSetting.isSupport(mCamera.getParameters())) {
            new AlertDialog.Builder(this)
                    .setItems(exposureSetting.text, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            exposureSetting.set(mCamera.getParameters(), exposureSetting.value[i]);
                            mCamera.setParameters(mCamera.getParameters());
                        }
                    }).show();
        }
    }

}
