package cn.mianyang.song314.android_cameralib.settings;

/**
 * time: 7/12/16
 * description:
 *
 * @author tangsong
 */
public abstract class BaseSetting<T> implements ISetting<T> {

    public String name; //选项名称
    public CharSequence[] text; //功能名称
    public T[] value;//参数类型

    public BaseSetting(String name) {
        this.name = name;
    }
}
