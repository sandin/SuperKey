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
import android.widget.ImageButton;

import com.lds.superkey.config.Config;
import com.lds.superkey.model.SKMessage;

public class MainActivity extends Activity implements OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private SensorManager mSensorManager;
    private MyRenderer mRenderer;
    
    private boolean useSensor = false;

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
        key01.setOnClickListener(this);
        key02.setOnClickListener(this);
        key03.setOnClickListener(this);
        key04.setOnClickListener(this);
        key05.setOnClickListener(this);
        key06.setOnClickListener(this);
        key07.setOnClickListener(this);
        key08.setOnClickListener(this);

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
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.key01:
            Communication.sendMessage(SKMessage.valueOf(Config.KEYCODE_SUPER_01));
            break;
        case R.id.key02:
            Communication.sendMessage(SKMessage.valueOf(Config.KEYCODE_SUPER_02));
            break;
        case R.id.key03:
            Communication.sendMessage(SKMessage.valueOf(Config.KEYCODE_SUPER_03));
            break;
        case R.id.key04:
            Communication.sendMessage(SKMessage.valueOf(Config.KEYCODE_SUPER_04));
            break;
        case R.id.key05:
            Communication.sendMessage(SKMessage.valueOf(Config.KEYCODE_SUPER_05));
            break;
        case R.id.key06:
            Communication.sendMessage(SKMessage.valueOf(Config.KEYCODE_SUPER_06));
            break;
        case R.id.key07:
            Communication.sendMessage(SKMessage.valueOf(Config.KEYCODE_SUPER_07));
            break;
        case R.id.key08:
            Communication.sendMessage(SKMessage.valueOf(Config.KEYCODE_SUPER_08));
            break;
        default:
            break;
        }
        // TODO Auto-generated method stub

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

        @Override
        public void onSensorChanged(SensorEvent event) {
            // that we received the proper event
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                if (Math.abs(y) > 1) {
                    if (y > 0) {
                        System.out.println("------> " + y);
                        Communication.sendMessage(SKMessage
                                .valueOf(KeyEvent.KEYCODE_DPAD_RIGHT));
                    } else {
                        System.out.println("<------ " + y);
                        Communication.sendMessage(SKMessage
                                .valueOf(KeyEvent.KEYCODE_DPAD_LEFT));
                    }
                } else {
                    System.out.println("-------" + y);
                }

                // System.out.println("lds:origin: " + x + ", " + y + ", " + z);
                // System.out.println("lds:range: " + rX + ", " + rY + ", " +
                // rZ);
            }
        }

    }

}
