package cn.mianyang.song314.android_cameralib.settings;

import java.util.List;

import cn.mianyang.song314.android_cameralib.hal.IParameters;

/**
 * Created by tianwenjie on 7/14/16.
 */
public class Zoom extends BaseSetting<Integer> {
    public Zoom(String name) {
        super(name);
    }

    @Override
    public boolean isSupport(IParameters parameters) {

        int count = parameters.getMaxZoom();
        if (count == 0 || !parameters.isZoomSupported()) {
            return false;
        }
        value = new Integer[count];
        text = new CharSequence[count];

        for (int i = 0; i < count; i++) {
            value[i] = i;
            text[i] = String.valueOf(i);
        }

        return true;
    }

    @Override
    public void set(IParameters parameters,Integer integer) {
         parameters.setZoom(integer);

    }
}
