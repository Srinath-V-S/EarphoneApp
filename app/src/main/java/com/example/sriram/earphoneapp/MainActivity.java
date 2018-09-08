package com.example.sriram.earphoneapp;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView headsetpress;
    ToggleButton toggle;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        headsetpress = (TextView)findViewById(R.id.hookpressevent);
        toggle = (ToggleButton) findViewById(R.id.toggle);
        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggle.isChecked()){
                    audioManager.adjustVolume(AudioManager.ADJUST_MUTE,AudioManager.FLAG_PLAY_SOUND);
                }
                else{
                    audioManager.setMicrophoneMute(false);

                    audioManager.adjustVolume(AudioManager.ADJUST_UNMUTE,AudioManager.FLAG_PLAY_SOUND);
                }
            }
        });
}

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_HEADSETHOOK) {
            headsetpress.setText("Key is releasesd!".toString());
            return true;
        }


        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        headsetpress = (TextView)findViewById(R.id.hookpressevent);
//        if(flag == 1){
//            flag = 0;
//            headsetpress.setText("PAUSE mode".toString());
//        }
        if(keyCode == KeyEvent.KEYCODE_HEADSETHOOK){
            headsetpress.setText("Key is pressed!".toString());
           // flag = 1;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
