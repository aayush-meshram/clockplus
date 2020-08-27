package com.myapp.clock;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmService extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmservice);
        Button button = findViewById(R.id.button);
        final MediaPlayer player = MediaPlayer.create(getApplicationContext(),R.raw.song);
        player.start();
        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long[] pattern = {1500, 800, 800, 800};
        VibrationEffect vibe = VibrationEffect.createWaveform(pattern, 0);
        vibrator.vibrate(vibe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.release();
                vibrator.cancel();
                Intent intent = new Intent(AlarmService.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
