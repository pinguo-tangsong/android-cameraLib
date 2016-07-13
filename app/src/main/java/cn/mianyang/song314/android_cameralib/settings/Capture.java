package cn.mianyang.song314.android_cameralib.settings;

import cn.mianyang.song314.android_cameralib.hal.IParameters;

/**
 * time: 7/13/16
 * description:
 *
 * @author tangsong
 */
public class Capture extends BaseSetting {

    public Capture(String name) {
        super(name);
    }

    @Override
    public boolean isSupport(IParameters parameters) {
        return true;
    }

    @Override
    public void set(IParameters parameters, Object o) {

    }
}
