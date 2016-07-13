package cn.mianyang.song314.android_cameralib.hal;

import android.hardware.Camera;

import java.util.List;

/**
 * time: 7/13/16
 * description:
 *
 * @author tangsong
 */
public interface IParameters<P> {

    P get();

    String ANTIBANDING_50HZ = "50hz";
    String ANTIBANDING_60HZ = "60hz";
    String ANTIBANDING_AUTO = "auto";
    String ANTIBANDING_OFF = "off";
    String EFFECT_AQUA = "aqua";
    String EFFECT_BLACKBOARD = "blackboard";
    String EFFECT_MONO = "mono";
    String EFFECT_NEGATIVE = "negative";
    String EFFECT_NONE = "none";
    String EFFECT_POSTERIZE = "posterize";
    String EFFECT_SEPIA = "sepia";
    String EFFECT_SOLARIZE = "solarize";
    String EFFECT_WHITEBOARD = "whiteboard";
    String FLASH_MODE_AUTO = "auto";
    String FLASH_MODE_OFF = "off";
    String FLASH_MODE_ON = "on";
    String FLASH_MODE_RED_EYE = "red-eye";
    String FLASH_MODE_TORCH = "torch";
    int FOCUS_DISTANCE_FAR_INDEX = 2;
    int FOCUS_DISTANCE_NEAR_INDEX = 0;
    int FOCUS_DISTANCE_OPTIMAL_INDEX = 1;
    String FOCUS_MODE_AUTO = "auto";
    String FOCUS_MODE_CONTINUOUS_PICTURE = "continuous-picture";
    String FOCUS_MODE_CONTINUOUS_VIDEO = "continuous-video";
    String FOCUS_MODE_EDOF = "edof";
    String FOCUS_MODE_FIXED = "fixed";
    String FOCUS_MODE_INFINITY = "infinity";
    String FOCUS_MODE_MACRO = "macro";
    int PREVIEW_FPS_MAX_INDEX = 1;
    int PREVIEW_FPS_MIN_INDEX = 0;
    String SCENE_MODE_ACTION = "action";
    String SCENE_MODE_AUTO = "auto";
    String SCENE_MODE_BARCODE = "barcode";
    String SCENE_MODE_BEACH = "beach";
    String SCENE_MODE_CANDLELIGHT = "candlelight";
    String SCENE_MODE_FIREWORKS = "fireworks";
    String SCENE_MODE_HDR = "hdr";
    String SCENE_MODE_LANDSCAPE = "landscape";
    String SCENE_MODE_NIGHT = "night";
    String SCENE_MODE_NIGHT_PORTRAIT = "night-portrait";
    String SCENE_MODE_PARTY = "party";
    String SCENE_MODE_PORTRAIT = "portrait";
    String SCENE_MODE_SNOW = "snow";
    String SCENE_MODE_SPORTS = "sports";
    String SCENE_MODE_STEADYPHOTO = "steadyphoto";
    String SCENE_MODE_SUNSET = "sunset";
    String SCENE_MODE_THEATRE = "theatre";
    String WHITE_BALANCE_AUTO = "auto";
    String WHITE_BALANCE_CLOUDY_DAYLIGHT = "cloudy-daylight";
    String WHITE_BALANCE_DAYLIGHT = "daylight";
    String WHITE_BALANCE_FLUORESCENT = "fluorescent";
    String WHITE_BALANCE_INCANDESCENT = "incandescent";
    String WHITE_BALANCE_SHADE = "shade";
    String WHITE_BALANCE_TWILIGHT = "twilight";
    String WHITE_BALANCE_WARM_FLUORESCENT = "warm-fluorescent";


//    String flatten();
//
//    void unflatten(String flattened);

//    void remove(String key);
//
//    void set(String key, String value);
//
//    void set(String key, int value);

//    String get(String key);
//
//    int getInt(String key);

    void setPreviewSize(int width, int height);

    Camera.Size getPreviewSize();

    List<Camera.Size> getSupportedPreviewSizes();

    List<Camera.Size> getSupportedVideoSizes();

    Camera.Size getPreferredPreviewSizeForVideo();

    void setJpegThumbnailSize(int width, int height);

    Camera.Size getJpegThumbnailSize();

    List<Camera.Size> getSupportedJpegThumbnailSizes();

    void setJpegThumbnailQuality(int quality);

    int getJpegThumbnailQuality();

    void setJpegQuality(int quality);

    int getJpegQuality();


    void setPreviewFrameRate(int fps);

    int getPreviewFrameRate();

    List<Integer> getSupportedPreviewFrameRates();

    void setPreviewFpsRange(int min, int max);

    void getPreviewFpsRange(int[] range);

    List<int[]> getSupportedPreviewFpsRange();

    void setPreviewFormat(int pixel_format);

    int getPreviewFormat();

    List<Integer> getSupportedPreviewFormats();

    void setPictureSize(int width, int height);

    Camera.Size getPictureSize();

    List<Camera.Size> getSupportedPictureSizes();

    void setPictureFormat(int pixel_format);

    int getPictureFormat();

    List<Integer> getSupportedPictureFormats();

    void setRotation(int rotation);

    void setGpsLatitude(double latitude);

    void setGpsLongitude(double longitude);

    void setGpsAltitude(double altitude);

    void setGpsTimestamp(long timestamp);

    void setGpsProcessingMethod(String processing_method);

    void removeGpsData();

    String getWhiteBalance();

    void setWhiteBalance(String value);

    List<String> getSupportedWhiteBalance();

    String getColorEffect();

    void setColorEffect(String value);

    List<String> getSupportedColorEffects();

    String getAntibanding();

    void setAntibanding(String antibanding);

    List<String> getSupportedAntibanding();

    String getSceneMode();

    void setSceneMode(String value);

    List<String> getSupportedSceneModes();

    String getFlashMode();

    void setFlashMode(String value);

    List<String> getSupportedFlashModes();

    String getFocusMode();

    void setFocusMode(String value);

    List<String> getSupportedFocusModes();

    float getFocalLength();

    float getHorizontalViewAngle();

    float getVerticalViewAngle();

    int getExposureCompensation();

    void setExposureCompensation(int value);

    int getMaxExposureCompensation();

    int getMinExposureCompensation();

    float getExposureCompensationStep();

    void setAutoExposureLock(boolean toggle);

    boolean getAutoExposureLock();

    boolean isAutoExposureLockSupported();

    void setAutoWhiteBalanceLock(boolean toggle);

    boolean getAutoWhiteBalanceLock();

    boolean isAutoWhiteBalanceLockSupported();

    int getZoom();

    void setZoom(int value);

    boolean isZoomSupported();

    int getMaxZoom();

    List<Integer> getZoomRatios();

    boolean isSmoothZoomSupported();

    void getFocusDistances(float[] output);

    int getMaxNumFocusAreas();

    List<Camera.Area> getFocusAreas();

    void setFocusAreas(List<Camera.Area> focusAreas);

    int getMaxNumMeteringAreas();

    List<Camera.Area> getMeteringAreas();

    void setMeteringAreas(List<Camera.Area> meteringAreas);

    int getMaxNumDetectedFaces();

    void setRecordingHint(boolean hint);

    boolean isVideoSnapshotSupported();

    void setVideoStabilization(boolean toggle);

    boolean getVideoStabilization();

    boolean isVideoStabilizationSupported();

}
