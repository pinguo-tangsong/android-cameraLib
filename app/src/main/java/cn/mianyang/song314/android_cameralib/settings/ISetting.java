package cn.mianyang.song314.android_cameralib.settings;

import android.hardware.Camera;

/**
 * time: 7/12/16
 * description:
 *
 * @author tangsong
 */
public interface ISetting<T> {

    boolean isSupport(Camera.Parameters parameters);
    void set(Camera.Parameters parameters, T t);

}
