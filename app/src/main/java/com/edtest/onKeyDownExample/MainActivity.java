package com.edtest.onKeyDownExample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> keyPresses;
    ListView listView;
    ArrayAdapter arrayAdapter;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyPresses = new ArrayList<>();
        listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter(this, R.layout.row_layout, R.id.label, keyPresses);
        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

    private static final int SAMSUNG_XCOVER_PRO_PTT_KEY = 1015;
    private static final int SAMSUNG_XCOVER_PRO_TOP_KEY = 1079;
    private static final int SAMSUNG_XCOVER_PRO_VOLUME_UP = 24;
    private static final int SAMSUNG_XCOVER_PRO_VOLUME_DOWN = 25;
    private static final int ANDROID_BACK_KEY_PRESS = 4;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //https://developer.android.com/reference/android/view/KeyEvent
        //log information
        Log.w("KEYTEST", "KEYCODE: " + keyCode);
        Log.w("KEYTEST", "EVENT: " + event.toString());
        Log.w("KEYTEST", "ACTION: " + event.getAction()); //physical buttons seem to always return ACTION_DOWN and never ACTION_UP
        Log.w("KEYTEST", "DOWN TIME: " + event.getDownTime()); //time button pressed in millis since device booted
        Log.w("KEYTEST", "REPEAT COUNT: " + event.getRepeatCount()); //uncertain how this counts - but if button is held this increments
        Log.w("KEYTEST", "SYSTEM UP TIME: " + SystemClock.uptimeMillis());  //time the event down time is based on

        //add to list view
        counter++;
        String keyPress = counter + " - KEYCODE: " + keyCode;
        keyPresses.add(0, keyPress);
        arrayAdapter.notifyDataSetChanged();

        //Handle Known Events
        switch (keyCode) {
            case SAMSUNG_XCOVER_PRO_PTT_KEY:
                //handle PTT press
                break;
            case SAMSUNG_XCOVER_PRO_TOP_KEY:
                //handle TOP press
                break;
            case SAMSUNG_XCOVER_PRO_VOLUME_DOWN:
                //handle volume keys
                break;
            case SAMSUNG_XCOVER_PRO_VOLUME_UP:
                //handle volume keys
                break;
            case ANDROID_BACK_KEY_PRESS:
                //handle back press
                break;
        }
        // if returning only true or false this breaks regular volume function and back key presses
        // if returning super.onKeyDown(keyCode, event) then volume keys and back keys work AND we can catch these key presses
//        return super.onKeyDown(keyCode, event);
        return true;
    }

}