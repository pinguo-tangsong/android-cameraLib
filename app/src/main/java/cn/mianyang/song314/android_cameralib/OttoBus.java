package cn.mianyang.song314.android_cameralib;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * time: 7/15/16
 * description:
 *
 * @author tangsong
 */
public class OttoBus {
    private static Bus ourInstance = new Bus(ThreadEnforcer.ANY);

    public static Bus getInstance() {
        return ourInstance;
    }

    private OttoBus() {
    }
}
