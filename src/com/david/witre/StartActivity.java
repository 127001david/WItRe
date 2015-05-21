package com.david.witre;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
	}

	private void initViews() {
		initValues();
	}

	private void initValues() {
		startPager();
	}

	private void startPager() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						Intent intent = new Intent(StartActivity.this,
								WitReActivity.class);

						startActivity(intent);
						finish();
					}
				});
			}
		}).start();
	}
}