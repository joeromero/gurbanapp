package com.wolfram.gurbanapp;

import android.graphics.Bitmap;


public interface CloudInterface {

	public void onEvaluateCompleted(Bitmap result);

	public String getBaseURL();
}