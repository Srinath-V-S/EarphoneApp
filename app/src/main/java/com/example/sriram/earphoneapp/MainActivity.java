package com.example.sriram.morsecodeconverter;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockContentResolver;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    String MorseString;

    TextView textView;
    EditText editText;
    Button button;
    Button audio,reset;

    TextView headsetpress,volume;
    ToggleButton toggle;
    AudioManager audioManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.et);
        button = (Button)findViewById(R.id.button);
        audio = (Button)findViewById(R.id.button2);
        textView = (TextView)findViewById(R.id.textView);
        reset = (Button)findViewById(R.id.button3);

        headsetpress = (TextView)findViewById(R.id.hookpressevent);
        //volume = (TextView)findViewById(R.id.volume);
        toggle = (ToggleButton) findViewById(R.id.toggle);
        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);



        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggle.isChecked()){
                    //volume.setText("Volume is " + audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
                    Toast.makeText(getApplicationContext(),"Volume is " + audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),Toast.LENGTH_SHORT).show();
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
                    //audioManager.adjustVolume(AudioManager.ADJUST_MUTE,AudioManager.FLAG_PLAY_SOUND);
                }
                else{
//                    volume.setText("Volume is 0");
                    Toast.makeText(getApplicationContext(),"Volume is 0",Toast.LENGTH_SHORT).show();
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,0,0);
                    //audioManager.adjustVolume(AudioManager.ADJUST_UNMUTE,AudioManager.FLAG_PLAY_SOUND);
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(convert(editText.getText().toString()));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                textView.setText("");
                MorseString = "";
            }
        });

        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MediaPlayer dot = MediaPlayer.create(MainActivity.this,R.raw.beepshort);
                MediaPlayer dash = MediaPlayer.create(MainActivity.this,R.raw.longbeep);

                convertAudio(dot,dash);


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

    private void convertAudio(MediaPlayer dot, MediaPlayer dash) {
        Log.d("TAG",MorseString);
        for(int i = 0; i<MorseString.length(); i++){
            if(MorseString.charAt(i) == '-'){
                dash.start();
                Log.d("TAG",dash.isPlaying() + "DASH");
                dash.setVolume(50,50);

            }
            else{
                dot.start();
                Log.d("TAG : ",dot.isPlaying() + "DOT");
                dot.setVolume(50,50);


            }
        }
    }


    private String convert(String text) {

        String temp = text;

        temp = temp.toUpperCase();
        temp = temp.replace("0","-----");
        temp = temp.replace("1",".----");
        temp = temp.replace("2","..---");
        temp = temp.replace("3","...--");
        temp = temp.replace("4","....-");
        temp = temp.replace("5",".....");
        temp = temp.replace("6","-....");
        temp = temp.replace("7","--...");
        temp = temp.replace("8","---..");
        temp = temp.replace("9","----.");

        temp = temp.replace("A",".-");
        temp = temp.replace("B","-...");
        temp = temp.replace("C","-.-.");
        temp = temp.replace("D","-..");
        temp = temp.replace("E",".");
        temp = temp.replace("F","..-.");
        temp = temp.replace("G","--.");
        temp = temp.replace("H","....");
        temp = temp.replace("I","..");
        temp = temp.replace("J",".---");
        temp = temp.replace("K","-.-");
        temp = temp.replace("L",".-..");
        temp = temp.replace("M","--");
        temp = temp.replace("N","-.");
        temp = temp.replace("O","---");
        temp = temp.replace("P",".--.");
        temp = temp.replace("Q","--.-");
        temp = temp.replace("R",".-.");
        temp = temp.replace("S","...");
        temp = temp.replace("T","-");
        temp = temp.replace("U","..-");
        temp = temp.replace("V","...-");
        temp = temp.replace("W",".--");
        temp = temp.replace("X","-..-");
        temp = temp.replace("Y","-.--");
        temp = temp.replace("Z","--..");
        MorseString = temp;

        return temp;


    }

}
