package cn.mianyang.song314.android_cameralib;

import android.content.DialogInterface;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.mianyang.song314.android_cameralib.hal.ICamera;
import cn.mianyang.song314.android_cameralib.hal.LibCamera;
import cn.mianyang.song314.android_cameralib.settings.BaseSetting;
import cn.mianyang.song314.android_cameralib.settings.SettingFactory;
import cn.mianyang.song314.android_cameralib.utils.NormalRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements NormalRecyclerViewAdapter.OnItemClickListener {

    private ICamera mCamera;
    private CameraPreview mPreview;
    private int mCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;

    //    @BindView(R.id.button_switch)
//    Button mBtnSwitch;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private NormalRecyclerViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        // Create an instance of Camera
        mCamera = new LibCamera(mCameraId);
        mCamera.open();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new NormalRecyclerViewAdapter(this, SettingFactory.build(mCamera.getParameters()));
        mAdapter.setListener(this);

        mRecyclerView.setAdapter(mAdapter);
        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this);
        mPreview.setCamera(mCamera, mCameraId);
        ViewGroup preview = (ViewGroup) findViewById(R.id.camera_preview);
        preview.addView(mPreview, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void onItemClick(int position, final BaseSetting setting) {
        if (setting.value == null) {

            if (SettingFactory.CAPTURE.equals(setting.name)) {

            }

            if (SettingFactory.SWAP_CAM.equals(setting.name)) {
                mCamera.stopPreview();
                mCamera.release();

                final int cameraId = mCameraId == Camera.CameraInfo.CAMERA_FACING_BACK ? Camera.CameraInfo.CAMERA_FACING_FRONT : Camera.CameraInfo.CAMERA_FACING_BACK;
                mCameraId = cameraId;
                mCamera = new LibCamera(mCameraId);
                mCamera.open();
                mPreview.setCamera(mCamera, cameraId);
                mPreview.surfaceCreated(mPreview.getHolder());
            }

        } else {
            new AlertDialog.Builder(MainActivity.this)
                    .setItems(setting.text, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setting.set(mCamera.getParameters(), setting.value[i]);
                            mCamera.setParameters(mCamera.getParameters());
                        }
                    }).show();
        }

    }

}
