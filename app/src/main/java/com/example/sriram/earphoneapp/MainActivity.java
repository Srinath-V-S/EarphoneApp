package com.example.sriram.earphoneapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView headsetpress;
    int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        headsetpress = (TextView)findViewById(R.id.hookpressevent);
        if(flag == 1){
            flag = 0;
            headsetpress.setText("PAUSE mode".toString());
        }
        else if(keyCode == KeyEvent.KEYCODE_HEADSETHOOK){
            headsetpress.setText("PLAY mode!".toString());
            flag = 1;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
