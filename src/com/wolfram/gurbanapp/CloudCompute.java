package com.wolfram.gurbanapp;

import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;


public class CloudCompute extends AsyncTask<int[], Void, Bitmap> {

	private String baseURL = "";


	private CloudInterface callback;


	public CloudCompute(Context c) {

		this.callback = (CloudInterface) c;

		baseURL = callback.getBaseURL();
	}


	@Override
	protected Bitmap doInBackground(int[]... params) {

		int rule, steps;
		int month, year;
		Bitmap bitmap = null;


		for (int[] pair : params) {
			try {

				month = pair[0];
				year = pair[1];

				baseURL = baseURL + "?month=" + month + "&year=" + year;

				URL url = new URL(baseURL);
				

				bitmap = BitmapFactory.decodeStream(url.openStream());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bitmap;
	}


	@Override
	protected void onPostExecute(Bitmap result) {
		callback.onEvaluateCompleted(result);
	}

}
