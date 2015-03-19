package com.wolfram.gurbanapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends Activity implements CloudInterface {

	private EditText prompt1, prompt2;
	private ProgressBar bar;
	private ImageView imageView;


	  private String baseURL = "https://www.wolframcloud.com/objects/user-bb7ad2a9-a624-45e5-a867-8da9c0832887/Apps4Transparency/GUrbanApp";
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		prompt1 = (EditText) findViewById(R.id.prompt1);
		prompt2 = (EditText) findViewById(R.id.prompt2);
		bar = (ProgressBar) findViewById(R.id.progressBar);
		imageView = (ImageView) findViewById(R.id.image);
	}

	public void buttonClick(View v) {

		bar.setVisibility(View.VISIBLE);


		imageView.setImageResource(android.R.color.transparent);

		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(prompt2.getWindowToken(), 0);


		int rule = Integer.parseInt(prompt1.getText().toString());
		int steps = Integer.parseInt(prompt2.getText().toString());


		CloudCompute cc = new CloudCompute(this);


		int[] pair = { rule, steps };


		cc.execute(pair);
	}

	@Override
	public void onEvaluateCompleted(Bitmap result) {

		bar.setVisibility(View.GONE);

		imageView.setImageBitmap(result);
	}

	@Override
	public String getBaseURL() {// TODO Auto-generated method stub
		return baseURL;
	}
}