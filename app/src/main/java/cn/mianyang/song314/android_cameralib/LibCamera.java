package cn.mianyang.song314.android_cameralib;

import android.app.Application;
import android.content.pm.PackageManager;
import android.hardware.Camera;

/**
 * time: 7/7/16
 * description:
 *
 * @author tangsong
 */
public class LibCamera implements ICamera{


    public static Application gApp;

    public static void init(Application app) {
        gApp = app;
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
    public static Camera getCameraInstance() throws Exception {
        return Camera.open(); // attempt to get a Camera instance
    }

}
