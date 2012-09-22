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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;

import com.lds.superkey.config.Config;
import com.lds.superkey.model.SKMessage;

public class MainActivity extends Activity implements OnClickListener,
		OnLongClickListener, OnTouchListener {
	private static final String TAG = MainActivity.class.getSimpleName();

	private SensorManager mSensorManager;
	private MyRenderer mRenderer;

	private boolean useSensor = !false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
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

	private ImageButton key01;
	private ImageButton key02;
	private ImageButton key03;
	private ImageButton key04;
	private ImageButton key05;
	private ImageButton key06;
	private ImageButton key07;
	private ImageButton key08;
	private ImageButton key09;
	private ImageButton key10;

	private void initViews() {
		key01 = (ImageButton) findViewById(R.id.key01);
		key02 = (ImageButton) findViewById(R.id.key02);
		key03 = (ImageButton) findViewById(R.id.key03);
		key04 = (ImageButton) findViewById(R.id.key04);
		key05 = (ImageButton) findViewById(R.id.key05);
		key06 = (ImageButton) findViewById(R.id.key06);
		key07 = (ImageButton) findViewById(R.id.key07);
		key08 = (ImageButton) findViewById(R.id.key08);
		key01.setTag(Config.KEYCODE_SUPER_01);
		key02.setTag(Config.KEYCODE_SUPER_02);
		key03.setTag(Config.KEYCODE_SUPER_03);
		key04.setTag(Config.KEYCODE_SUPER_04);
		key05.setTag(Config.KEYCODE_SUPER_05);
		key06.setTag(Config.KEYCODE_SUPER_06);
		key07.setTag(Config.KEYCODE_SUPER_07);
		key08.setTag(Config.KEYCODE_SUPER_08);
		key01.setOnTouchListener(this);
		key02.setOnTouchListener(this);
		key03.setOnTouchListener(this);
		key04.setOnTouchListener(this);
		key05.setOnTouchListener(this);
		key06.setOnTouchListener(this);
		key07.setOnTouchListener(this);
		key08.setOnTouchListener(this);

		key09 = (ImageButton) findViewById(R.id.text);
		key09.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(0, 0);
				// imm.hideSoftInputFromWindow(findViewById(R.id.text).getWindowToken(),
				// InputMethodManager.SHOW_FORCED);
			}
		});
	}

	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		// TODO Auto-generated method stub
		int keyCode = (Integer) view.getTag();
		
		switch (motionEvent.getAction()) {
		case MotionEvent.ACTION_DOWN:
			view.setSelected(true);
			view.requestFocus();
			Communication.sendMessage(SKMessage.valueOf(
					keyCode, MotionEvent.ACTION_DOWN));
			return true;
		case MotionEvent.ACTION_UP:
			view.setSelected(false);
			view.clearFocus();
			Communication.sendMessage(SKMessage.valueOf(
					keyCode, MotionEvent.ACTION_UP));
			return true;
		default:
			break;
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.key01:
			Communication.sendMessage(SKMessage.valueOf(
					Config.KEYCODE_SUPER_01, MotionEvent.ACTION_DOWN));
			break;
		case R.id.key02:
			Communication.sendMessage(SKMessage.valueOf(
					Config.KEYCODE_SUPER_02, MotionEvent.ACTION_DOWN));
			break;
		case R.id.key03:
			Communication.sendMessage(SKMessage.valueOf(
					Config.KEYCODE_SUPER_03, MotionEvent.ACTION_DOWN));
			break;
		case R.id.key04:
			Communication.sendMessage(SKMessage.valueOf(
					Config.KEYCODE_SUPER_04, MotionEvent.ACTION_DOWN));
			break;
		case R.id.key05:
			Communication.sendMessage(SKMessage.valueOf(
					Config.KEYCODE_SUPER_05, MotionEvent.ACTION_DOWN));
			break;
		case R.id.key06:
			Communication.sendMessage(SKMessage.valueOf(
					Config.KEYCODE_SUPER_06, MotionEvent.ACTION_DOWN));
			break;
		case R.id.key07:
			Communication.sendMessage(SKMessage.valueOf(
					Config.KEYCODE_SUPER_07, MotionEvent.ACTION_DOWN));
			break;
		case R.id.key08:
			Communication.sendMessage(SKMessage.valueOf(
					Config.KEYCODE_SUPER_08, MotionEvent.ACTION_DOWN));
			break;
		default:
			break;
		}
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onLongClick(View v) {
		switch (v.getId()) {
		case R.id.key01:
			Communication.sendMessage(SKMessage.valueOf(
					Config.KEYCODE_SUPER_01, MotionEvent.ACTION_DOWN));
			return true;
		default:
			break;
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		Log.i(TAG, String.valueOf(keyCode));
		SKMessage msg = new SKMessage();
		msg.setType(SKMessage.TYPE_KEY);
		msg.setKeyCode(keyCode);
		Communication.sendMessage(msg);
		return super.onKeyUp(keyCode, event);
	}

	class MyRenderer implements SensorEventListener {
		private Sensor mRotationVectorSensor;

		public MyRenderer() {
			// find the rotation-vector sensor
			mRotationVectorSensor = mSensorManager
			// .getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
					.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		}

		public void start() {
			// enable our sensor when the activity is resumed, ask for
			// 10 ms updates.
			if (useSensor) {
				mSensorManager.registerListener(this, mRotationVectorSensor,
						SensorManager.SENSOR_DELAY_GAME);
			}
		}

		public void stop() {
			// make sure to turn our sensor off when the activity is paused
			mSensorManager.unregisterListener(this);
		}

		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			// TODO Auto-generated method stub

		}
		
		private int o = 0;
		
		private void onLeft() {
			if (o < 0) {
				// left key has been pressed, do nothing
			} else {
				Communication.sendMessage(SKMessage.valueOf(Config.KEYCODE_SUPER_03, MotionEvent.ACTION_DOWN));
				//Communication.sendMessage(SKMessage.valueOf(Config.KEYCODE_SUPER_03, MotionEvent.ACTION_UP));
			}
			o = -1;
		}
		
		private void onRight() {
			if (o > 0) {
				// right key has been pressed, do nothing
			} else {
				Communication.sendMessage(SKMessage.valueOf(Config.KEYCODE_SUPER_04, MotionEvent.ACTION_DOWN));
				//Communication.sendMessage(SKMessage.valueOf(Config.KEYCODE_SUPER_04, MotionEvent.ACTION_UP));
			}
			o = 1;
		}
		
		private void onV() {
			if (o == 0) {
				// do nothing
			} else if (o < 0) {
				Communication.sendMessage(SKMessage.valueOf(Config.KEYCODE_SUPER_03, MotionEvent.ACTION_UP));
			} else if (o > 0) {
				Communication.sendMessage(SKMessage.valueOf(Config.KEYCODE_SUPER_04, MotionEvent.ACTION_UP));
			}
			o = 0;
			
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			// that we received the proper event
			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				float x = event.values[0];
				float y = event.values[1];
				float z = event.values[2];

				if (Math.abs(y) > 3.5) {
					if (y > 0) {
						System.out.println("------> " + y);
						onRight();
					} else {
						System.out.println("<------ " + y);
						onLeft();
					}
				} else {
					System.out.println("--------- " + y);
					onV();
				}

				// System.out.println("lds:origin: " + x + ", " + y + ", " + z);
				// System.out.println("lds:range: " + rX + ", " + rY + ", " +
				// rZ);
			}
		}

	}

}
