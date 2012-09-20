package com.lds.superkey;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();

	private SensorManager mSensorManager;
	private MyRenderer mRenderer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Get an instance of the SensorManager
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mRenderer = new MyRenderer();

		initViews();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mRenderer.start();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mRenderer.stop();
	}

	private void initViews() {
		Button btn = (Button) findViewById(R.id.text);
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(0, 0);
				// imm.hideSoftInputFromWindow(findViewById(R.id.text).getWindowToken(),
				// InputMethodManager.SHOW_FORCED);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		Log.i(TAG, String.valueOf(keyCode));
		Communication.sendMessage(keyCode);
		return super.onKeyUp(keyCode, event);
	}

	class MyRenderer implements SensorEventListener {
		private Sensor mRotationVectorSensor;

		public MyRenderer() {

			// find the rotation-vector sensor
			mRotationVectorSensor = mSensorManager
					//.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
					.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		}

		public void start() {
			// enable our sensor when the activity is resumed, ask for
			// 10 ms updates.
			mSensorManager.registerListener(this, mRotationVectorSensor, 10000);
		}

		public void stop() {
			// make sure to turn our sensor off when the activity is paused
			mSensorManager.unregisterListener(this);
		}

		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub

		}
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			 // that we received the proper event
            if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            	 float x = event.values[0];
            	 float y = event.values[1];
            	 float z = event.values[2];
            	 
            	if (x > 0.1) {
            		if (x > 0) {
            			System.out.println("------>" + x);
            		} else {
            			System.out.println("<------" + x);
            		}
            	} else {
            		System.out.println("-------" + x);
            	}
            		
            	 
            	//System.out.println("lds:origin: " + x + ", " + y + ", " + z);
            	//System.out.println("lds:range: " + rX + ", " + rY + ", " + rZ);
            }
		}

	}

}
