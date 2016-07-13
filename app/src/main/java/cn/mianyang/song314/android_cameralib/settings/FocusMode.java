package cn.mianyang.song314.android_cameralib.settings;

import java.util.List;

import cn.mianyang.song314.android_cameralib.hal.IParameters;

/**
 * time: 7/13/16
 * description:
 *
 * @author tangsong
 */
public class FocusMode extends BaseSetting<String> {

    public FocusMode(String name) {
        super(name);
    }

    @Override
    public boolean isSupport(IParameters parameters) {
        List<String> list = parameters.getSupportedFocusModes();
        if (list == null || list.isEmpty()) {
            return false;
        }

        int count = list.size();
        value = new String[count];
        text = new CharSequence[count];

        for (int i = 0; i < count; i ++) {
            value[i] = list.get(i);
            text[i] = list.get(i);
        }

        return true;
    }

    @Override
    public void set(IParameters parameters, String s) {
        parameters.setFlashMode(s);
    }
}
