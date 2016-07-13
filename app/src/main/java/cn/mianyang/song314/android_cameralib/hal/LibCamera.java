package cn.mianyang.song314.android_cameralib.hal;

import android.app.Application;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * time: 7/7/16
 * description:
 *
 * @author tangsong
 */
public class LibCamera implements ICamera<Camera.Parameters> {

    private IParameters<Camera.Parameters> mParameters;

    public static Application gApp;
    private final int mCameraId;

    public LibCamera(int cameraId) {
        mCameraId = cameraId;
    }

    public static void init(Application app) {
        gApp = app;
    }

    private Camera mCamera;

    @Override
    public Camera get() {
        return mCamera;
    }

    @Override
    public void open() {
        mCamera = Camera.open(mCameraId);
        mParameters = new LibParameters(mCamera.getParameters());
    }
    @Override
    public void startPreview() {
        mCamera.startPreview();;
    }

    @Override
    public void stopPreview() {
        mCamera.stopPreview();;
    }

    @Override
    public void release() {
        mCamera.release();
        mCamera = null;
    }

    @Override
    public void setParameters(IParameters<Camera.Parameters> parameters) {
        mCamera.setParameters(parameters.get());
    }

    @Override
    public void setPreviewDisplay(SurfaceHolder holder) throws IOException {
        mCamera.setPreviewDisplay(holder);
    }

    @Override
    public IParameters<Camera.Parameters> getParameters() {
        return mParameters;
    }

    /** Check if this device has a camera */
    public boolean checkCameraHardware() {
        if (gApp.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /** A safe way to get an instance of the Camera object. */
    public static LibCamera getCameraInstance(int cameraId) throws Exception {
        return new LibCamera(cameraId); // attempt to get a Camera instance
    }

}
