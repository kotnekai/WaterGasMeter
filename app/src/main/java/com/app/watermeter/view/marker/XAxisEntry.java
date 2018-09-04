package com.app.watermeter.view.marker;

/**
 * Created by rolandxu on 16/1/8.
 */
public class XAxisEntry {


    private String mVal;
    private boolean mVisible;
    private float[] points;
    private int[] colors;

    public XAxisEntry(String mVal, boolean mVisible) {
        this.mVal = mVal;
        this.mVisible = mVisible;
    }

    public void setPoints(float[] points) {
        this.points = points;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }


    public String getVal() {
        return mVal;
    }

    public void setVal(String mVal) {
        this.mVal = mVal;
    }

    public boolean isVisible() {
        return mVisible;
    }

    public void setVisible(boolean mVisible) {
        this.mVisible = mVisible;
    }

    public float[] getPoints() {
        return points;
    }

    public int[] getColors() {
        return colors;
    }
}
