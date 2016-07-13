package cn.mianyang.song314.android_cameralib.settings;

import android.hardware.Camera;

import java.util.ArrayList;
import java.util.Iterator;

import cn.mianyang.song314.android_cameralib.hal.IParameters;

/**
 * time: 7/12/16
 * description:
 *
 * @author tangsong
 */
public class SettingFactory {

    public static final String CAPTURE = "拍照";
    public static final String SWAP_CAM = "切换";

    public static ArrayList<BaseSetting> build(final IParameters<Camera.Parameters> parameters) {

        ArrayList<BaseSetting> settings = new ArrayList<>();

        settings.add(new Capture(CAPTURE));
        settings.add(new Capture(SWAP_CAM));
        settings.add(new ExposureSetting("亮度"));
        settings.add(new FocusMode("对焦优先"));


        Iterator<BaseSetting> it = settings.iterator();

        while (it.hasNext()) {
            BaseSetting s = it.next();
            if (!s.isSupport(parameters)) {
                it.remove();
            }
        }

        return settings;

    }
}
