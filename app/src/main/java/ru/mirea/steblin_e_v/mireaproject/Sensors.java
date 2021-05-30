package ru.mirea.steblin_e_v.mireaproject;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sensors#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sensors extends Fragment implements SensorEventListener {

    private TextView azimuthTextView;
    private TextView pitchTextView;
    private TextView rollTextView;
    private TextView stationaryTextView;
    private TextView motionTextView;
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private Sensor stationarySensor;
    private Sensor motionSensor;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public Sensors() {
        // Required empty public constructor
    }

    public static Sensors newInstance(String param1, String param2) {
        Sensors fragment = new Sensors();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Activity act = getActivity();
        sensorManager = (SensorManager) act.getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        stationarySensor = sensorManager.getDefaultSensor(Sensor.TYPE_STATIONARY_DETECT);
        motionSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MOTION_DETECT);

        motionTextView = act.findViewById(R.id.motionValue);
        stationaryTextView = act.findViewById(R.id.statValue);
        azimuthTextView = act.findViewById(R.id.accelAzimuth);
        pitchTextView = act.findViewById(R.id.accelPitch);
        rollTextView = act.findViewById(R.id.accelRoll);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sensors, container, false);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float valueAzimuth = event.values[0];
            float valuePitch = event.values[1];
            float valueRoll = event.values[2];
            azimuthTextView.setText(Float.toString(valueAzimuth));
            pitchTextView.setText(Float.toString(valuePitch));
            rollTextView.setText(Float.toString(valueRoll));
        }
        else if(event.sensor.getType() == Sensor.TYPE_STATIONARY_DETECT){
            float stationary = event.values[0];
            stationaryTextView.setText(Float.toString(stationary));
        }
        else if(event.sensor.getType() == Sensor.TYPE_MOTION_DETECT){
            float motion = event.values[0];
            motionTextView.setText(Float.toString(motion));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume(){
        super.onResume();
        sensorManager.registerListener(this,accelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,stationarySensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,motionSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}