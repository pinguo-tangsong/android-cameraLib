package cn.mianyang.song314.android_cameralib.hal;

import android.hardware.Camera;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * time: 7/7/16
 * description:
 *
 * @author tangsong
 */
public interface ICamera<P> {

    void open();

    void startPreview();

    void stopPreview();

    void release();

    IParameters getParameters();

    void setParameters(IParameters<P> parameters);

    void setPreviewDisplay(SurfaceHolder holder) throws IOException;

    int getCameraId();

    Camera get();

    void autoFocus();
}
