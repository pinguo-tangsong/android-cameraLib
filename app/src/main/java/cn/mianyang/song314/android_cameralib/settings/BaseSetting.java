package cn.mianyang.song314.android_cameralib.settings;

/**
 * time: 7/12/16
 * description:
 *
 * @author tangsong
 */
public abstract class BaseSetting<T> implements ISetting<T> {

    public String name;
    public CharSequence[] text;
    public float[] value;

    public BaseSetting(String name) {
        this.name = name;
    }
}
