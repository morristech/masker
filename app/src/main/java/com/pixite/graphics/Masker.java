package com.pixite.graphics;

import android.graphics.Bitmap;

public class Masker {

    private final long nativeInstance;

    private final int width, height;

    public Masker(Bitmap src) {
        width = src.getWidth();
        height = src.getHeight();
        nativeInstance = native_init(src);
    }

    public Bitmap getMask(int x, int y) {
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        native_mask(nativeInstance, result, x, y);
        return result;
    }

    private native long native_init(Bitmap src);
    private native void native_mask(long nativeInstance, Bitmap result, int x, int y);

    static {
        System.loadLibrary("masker");
    }

}