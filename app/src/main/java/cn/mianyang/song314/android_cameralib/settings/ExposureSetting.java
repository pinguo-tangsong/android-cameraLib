package cn.mianyang.song314.android_cameralib.settings;

import android.hardware.Camera;

/**
 * time: 7/12/16
 * description:
 *
 * @author tangsong
 */
public class ExposureSetting extends BaseSetting<Float> {

    public float[] value;
    public CharSequence[] text;

    @Override
    public boolean isSupport(Camera.Parameters parameters) {
        int max = parameters.getMaxExposureCompensation();
        int min = parameters.getMinExposureCompensation();
        float step = parameters.getExposureCompensationStep();
        int count = (int) ((max - min) / step);

        if (max == min||
                step == 0 ||
                count == 0) {
            return false;
        }


        value = new float[count];
        text = new CharSequence[count];

        for (int i = 0; i < count; i ++) {
            value[i] = min + (i * step);
            text[i] = String.valueOf(value[i]);
        }

        return true;
    }


    @Override
    public void set(Camera.Parameters parameters, Float value) {
        parameters.setExposureCompensation(value.intValue());
    }
}
