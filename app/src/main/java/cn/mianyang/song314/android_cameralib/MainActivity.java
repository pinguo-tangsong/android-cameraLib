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
import cn.mianyang.song314.android_cameralib.settings.ExposureSetting;

public class MainActivity extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private int mCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;

    @BindView(R.id.button_switch)
    Button mBtnSwitch;

    Camera.Parameters mParameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // Create an instance of Camera
        mCamera = getCameraInstance(mCameraId);

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
                mCamera = getCameraInstance(cameraId);
                mPreview.setCamera(mCamera,cameraId);
                mPreview.surfaceCreated(mPreview.getHolder());
            }
        });
    }

    @OnClick(R.id.button_capture)
    public void expore() {
        final ExposureSetting exposureSetting = new ExposureSetting();
        if (exposureSetting.isSupport(mParameters)) {
            new AlertDialog.Builder(this)
                    .setItems(exposureSetting.text, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            exposureSetting.set(mParameters, exposureSetting.value[i]);
                            mCamera.setParameters(mParameters);
                        }
                    }).show();
        }
    }

    /** A safe way to get an instance of the Camera object.
     * @param id*/
    public Camera getCameraInstance(int id){//todo remove
        Camera c = null;
        try {
            c = Camera.open(id); // attempt to get a Camera instance
            mParameters = c.getParameters();

        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
}
