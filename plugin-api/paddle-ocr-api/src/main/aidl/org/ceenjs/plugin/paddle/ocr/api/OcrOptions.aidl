package org.ceenjs.plugin.paddle.ocr.api;

parcelable OcrOptions {
    int cpuThreadNum;
    boolean useSlim;
    boolean useOpenCL;
    int detLongSize;
    float scoreThreshold;
    android.os.Bundle extras;
}