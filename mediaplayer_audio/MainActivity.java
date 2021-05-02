package com.fdananda.gitmediaplayeraudio;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayerAudio;
    private SeekBar seekBarVolume;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayerAudio = MediaPlayer.create(getApplicationContext(), R.raw.musica);
        controlarVolume();
    }

    private void controlarVolume(){

        seekBarVolume   =   findViewById(R.id.seekBarVolume);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int volMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volAtual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        seekBarVolume.setMax(volMax);
        seekBarVolume.setProgress(volAtual);

        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_SHOW_UI);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void play(View view){

        if(mediaPlayerAudio != null) {
            mediaPlayerAudio.start();
        }
    }

    public void pause(View view){
        if(mediaPlayerAudio.isPlaying()) {
            mediaPlayerAudio.pause();
        }
    }

    public void stop(View view){
        if(mediaPlayerAudio.isPlaying()) {
            mediaPlayerAudio.stop();
            mediaPlayerAudio = MediaPlayer.create(getApplicationContext(), R.raw.musica);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mediaPlayerAudio.isPlaying()) {
            mediaPlayerAudio.pause();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(!mediaPlayerAudio.isPlaying()) {
            mediaPlayerAudio.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayerAudio != null && mediaPlayerAudio.isPlaying()) {
            mediaPlayerAudio.stop();
            mediaPlayerAudio.release();
            mediaPlayerAudio = null;
        }
    }
}
