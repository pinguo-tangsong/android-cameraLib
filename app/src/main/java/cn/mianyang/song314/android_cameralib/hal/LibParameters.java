package cn.mianyang.song314.android_cameralib.hal;

import android.hardware.Camera;

import java.util.List;

/**
 * time: 7/13/16
 * description:
 *
 * @author tangsong
 */
public class LibParameters implements IParameters<Camera.Parameters> {

    private Camera.Parameters mParameters;

    public LibParameters(Camera.Parameters parameters) {
        mParameters = parameters;
    }

    @Override
    public Camera.Parameters get() {
        return mParameters;
    }

    @Override
    public void setPreviewSize(int width, int height) {
        mParameters.setPreviewSize(width, height);
    }

    @Override
    public Camera.Size getPreviewSize() {
        return mParameters.getPreviewSize();
    }

    @Override
    public List<Camera.Size> getSupportedPreviewSizes() {
        return null;
    }

    @Override
    public List<Camera.Size> getSupportedVideoSizes() {
        return null;
    }

    @Override
    public Camera.Size getPreferredPreviewSizeForVideo() {
        return null;
    }

    @Override
    public void setJpegThumbnailSize(int width, int height) {

    }

    @Override
    public Camera.Size getJpegThumbnailSize() {
        return null;
    }

    @Override
    public List<Camera.Size> getSupportedJpegThumbnailSizes() {
        return null;
    }

    @Override
    public void setJpegThumbnailQuality(int quality) {

    }

    @Override
    public int getJpegThumbnailQuality() {
        return 0;
    }

    @Override
    public void setJpegQuality(int quality) {

    }

    @Override
    public int getJpegQuality() {
        return 0;
    }

    @Override
    public void setPreviewFrameRate(int fps) {

    }

    @Override
    public int getPreviewFrameRate() {
        return 0;
    }

    @Override
    public List<Integer> getSupportedPreviewFrameRates() {
        return null;
    }

    @Override
    public void setPreviewFpsRange(int min, int max) {

    }

    @Override
    public void getPreviewFpsRange(int[] range) {

    }

    @Override
    public List<int[]> getSupportedPreviewFpsRange() {
        return null;
    }

    @Override
    public void setPreviewFormat(int pixel_format) {

    }

    @Override
    public int getPreviewFormat() {
        return 0;
    }

    @Override
    public List<Integer> getSupportedPreviewFormats() {
        return null;
    }

    @Override
    public void setPictureSize(int width, int height) {

    }

    @Override
    public Camera.Size getPictureSize() {
        return null;
    }

    @Override
    public List<Camera.Size> getSupportedPictureSizes() {
        return null;
    }

    @Override
    public void setPictureFormat(int pixel_format) {

    }

    @Override
    public int getPictureFormat() {
        return 0;
    }

    @Override
    public List<Integer> getSupportedPictureFormats() {
        return null;
    }

    @Override
    public void setRotation(int rotation) {

    }

    @Override
    public void setGpsLatitude(double latitude) {

    }

    @Override
    public void setGpsLongitude(double longitude) {

    }

    @Override
    public void setGpsAltitude(double altitude) {

    }

    @Override
    public void setGpsTimestamp(long timestamp) {

    }

    @Override
    public void setGpsProcessingMethod(String processing_method) {

    }

    @Override
    public void removeGpsData() {

    }

    @Override
    public String getWhiteBalance() {
        return null;
    }

    @Override
    public void setWhiteBalance(String value) {

    }

    @Override
    public List<String> getSupportedWhiteBalance() {
        return null;
    }

    @Override
    public String getColorEffect() {
        return mParameters.getColorEffect();
    }

    @Override
    public void setColorEffect(String value) {
        mParameters.setColorEffect(value);


    }

    @Override
    public List<String> getSupportedColorEffects() {
        return mParameters.getSupportedColorEffects();
    }

    @Override
    public String getAntibanding() {
        return null;
    }

    @Override
    public void setAntibanding(String antibanding) {

    }

    @Override
    public List<String> getSupportedAntibanding() {
        return null;
    }

    @Override
    public String getSceneMode() {
        return null;
    }

    @Override
    public void setSceneMode(String value) {

    }

    @Override
    public List<String> getSupportedSceneModes() {
        return null;
    }

    @Override
    public String getFlashMode() {
        return mParameters.getFlashMode();
    }

    @Override
    public void setFlashMode(String value) {
        mParameters.setFlashMode(value);
    }

    @Override
    public List<String> getSupportedFlashModes() {
        return mParameters.getSupportedFlashModes();
    }

    @Override
    public String getFocusMode() {
        return null;
    }

    @Override
    public void setFocusMode(String value) {
        mParameters.setFocusMode(value);
    }

    @Override
    public List<String> getSupportedFocusModes() {
        return mParameters.getSupportedFocusModes();
    }

    @Override
    public float getFocalLength() {
        return 0;
    }

    @Override
    public float getHorizontalViewAngle() {
        return 0;
    }

    @Override
    public float getVerticalViewAngle() {
        return 0;
    }

    @Override
    public int getExposureCompensation() {
        return 0;
    }

    @Override
    public void setExposureCompensation(int value) {
        mParameters.setExposureCompensation(value);
    }

    @Override
    public int getMaxExposureCompensation() {
        return mParameters.getMaxExposureCompensation();
    }

    @Override
    public int getMinExposureCompensation() {
        return mParameters.getMinExposureCompensation();
    }

    @Override
    public float getExposureCompensationStep() {
        return mParameters.getExposureCompensationStep();
    }

    @Override
    public void setAutoExposureLock(boolean toggle) {

    }

    @Override
    public boolean getAutoExposureLock() {
        return false;
    }

    @Override
    public boolean isAutoExposureLockSupported() {
        return false;
    }

    @Override
    public void setAutoWhiteBalanceLock(boolean toggle) {

    }

    @Override
    public boolean getAutoWhiteBalanceLock() {
        return false;
    }

    @Override
    public boolean isAutoWhiteBalanceLockSupported() {
        return false;
    }

    @Override
    public int getZoom() {
        return 0;
    }

    @Override
    public void setZoom(int value) {

    }

    @Override
    public boolean isZoomSupported() {
        return false;
    }

    @Override
    public int getMaxZoom() {
        return 0;
    }

    @Override
    public List<Integer> getZoomRatios() {
        return null;
    }

    @Override
    public boolean isSmoothZoomSupported() {
        return false;
    }

    @Override
    public void getFocusDistances(float[] output) {

    }

    @Override
    public int getMaxNumFocusAreas() {
        return 0;
    }

    @Override
    public List<Camera.Area> getFocusAreas() {
        return null;
    }

    @Override
    public void setFocusAreas(List<Camera.Area> focusAreas) {

    }

    @Override
    public int getMaxNumMeteringAreas() {
        return 0;
    }

    @Override
    public List<Camera.Area> getMeteringAreas() {
        return null;
    }

    @Override
    public void setMeteringAreas(List<Camera.Area> meteringAreas) {

    }

    @Override
    public int getMaxNumDetectedFaces() {
        return 0;
    }

    @Override
    public void setRecordingHint(boolean hint) {

    }

    @Override
    public boolean isVideoSnapshotSupported() {
        return false;
    }

    @Override
    public void setVideoStabilization(boolean toggle) {

    }

    @Override
    public boolean getVideoStabilization() {
        return false;
    }

    @Override
    public boolean isVideoStabilizationSupported() {
        return false;
    }

}
