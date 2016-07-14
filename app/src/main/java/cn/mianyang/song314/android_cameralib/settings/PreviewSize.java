package cn.mianyang.song314.android_cameralib.settings;

import android.hardware.Camera;

import java.util.List;

import cn.mianyang.song314.android_cameralib.hal.IParameters;

/**
 * Created by tianwenjie on 7/14/16.
 */
public class PreviewSize extends BaseSetting<Camera.Size> {
    public PreviewSize(String name) {
        super(name);
    }

    @Override
    public boolean isSupport(IParameters parameters) {
        List<Camera.Size> list = parameters.getSupportedPreviewSizes();
        if (list == null) {
            return false;
        }
        int count = list.size();
        value = new Camera.Size[count];
        text = new CharSequence[count];

        for (int i = 0; i < count; i++) {
            value[i] = list.get(i);
            text[i] = String.valueOf(list.get(i).width + "x" + list.get(i).height);
        }

        return true;
    }

    @Override
    public void set(IParameters parameters, Camera.Size size) {
        parameters.setPreviewSize(size.width, size.height);

    }
}
