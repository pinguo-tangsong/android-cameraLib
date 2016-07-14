package cn.mianyang.song314.android_cameralib.settings;

import android.hardware.Camera;

import java.util.ArrayList;
import java.util.Iterator;

import cn.mianyang.song314.android_cameralib.hal.IParameters;

/**
 * time: 7/12/16
 * description:
 *  FlashMode,ExposureSetting,FocusMode,colorEffect, Zoom
 * todo  pictureSize, previewSize, WhiteBalance,
 * @author tangsong
 */
public class SettingFactory {

    public static final String CAPTURE = "拍照";
    public static final String SWAP_CAM = "切换";
    private static final String EXPOSURE = "亮度";
    private  static final String COLOR ="颜色";
    private  static final String ZOOM="变焦";
    private  static  final String WHITE_BALANCE="白平衡";


    /**
     *
     * @param parameters
     * @return
     */
    public static ArrayList<BaseSetting> build(final IParameters<Camera.Parameters> parameters) {

        ArrayList<BaseSetting> settings = new ArrayList<>();


        settings.add(new Capture(CAPTURE));
        settings.add(new Capture(SWAP_CAM));
        settings.add(new ExposureSetting(EXPOSURE));
        settings.add(new FocusMode("对焦优先"));
        settings.add(new FlashMode("闪光灯"));
        settings.add(new ColorEffect(COLOR));
        settings.add(new Zoom(ZOOM));
        settings.add(new WhiteBalance(WHITE_BALANCE));


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
