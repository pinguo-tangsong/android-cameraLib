package cn.mianyang.song314.android_cameralib.hal;

import android.hardware.Camera;

/**
 * time: 7/15/16
 * description:
 *
 * @author tangsong
 */
public class CameraManager {

    private static ICamera<Camera.Parameters> sCamera;

    private CameraManager() {

    }


    public static ICamera getCamera(int id) {
        if (sCamera != null) {
            if (id == sCamera.getCameraId()) {
                return sCamera;
            } else {
                sCamera.stopPreview();
                sCamera.release();
                sCamera = null;
            }
        }

        sCamera = new LibCamera(id);
        return sCamera;
    }

    public static ICamera getCamera() {
        if (sCamera == null) {
            sCamera = new LibCamera(Camera.CameraInfo.CAMERA_FACING_BACK);
            return sCamera;
        } else {
            return sCamera;
        }
    }

}
