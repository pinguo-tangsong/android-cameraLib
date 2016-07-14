package cn.mianyang.song314.android_cameralib.settings;

import java.util.List;

import cn.mianyang.song314.android_cameralib.hal.IParameters;

/**
 * Created by tianwenjie on 7/14/16.
 */
public class ColorEffect extends BaseSetting<String> {
    public ColorEffect(String name) {super(name);
    }

    @Override
    public boolean isSupport(IParameters parameters) {
        List<String> list = parameters.getSupportedColorEffects();
        if (list == null || list.isEmpty()) {
            return false;
        }

        int count = list.size();
        value = new String[count];
        text = new CharSequence[count];

        for (int i = 0; i < count; i++) {
            value[i] = list.get(i);
            text[i] = list.get(i);
        }

        return true;
    }
    @Override
    public void set(IParameters parameters, String s) {
            parameters.setColorEffect(s);
        }

    }
