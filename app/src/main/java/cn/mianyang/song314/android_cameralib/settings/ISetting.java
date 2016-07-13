package cn.mianyang.song314.android_cameralib.settings;

import cn.mianyang.song314.android_cameralib.hal.IParameters;

/**
 * time: 7/12/16
 * description:
 *
 * @author tangsong
 */
public interface ISetting<T> {

    boolean isSupport(IParameters parameters);
    void set(IParameters parameters, T t);

}
