package com.faw.lightsensortest;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    private SensorManager sensorManager;
    private TextView lightLevel;
    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float value = sensorEvent.values[0];
            lightLevel.setText("Current light level is "+value+" lx.");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightLevel = (TextView) findViewById(R.id.light_level);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(listener);
        }
    }
}
