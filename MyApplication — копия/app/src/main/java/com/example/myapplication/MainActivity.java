package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends Activity {

// поля
    private SoundPool sounds;
    private  int sound_shot;
    private  int sound_shot_false;
    private  int sound_baraban;
    private ImageView krov_image;
    private  int on_shot = 2; // <-- команды чтоб поставить на каком числе будет работать код "убийства"
    private  int max_number = 5; // <-- команда, сколько всего патроном
    private  int random = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSoundPool();
        loadSounds();
        init();

    }
    // команды, чтобы звук работал на всех версиях
    protected void createSoundPool() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            createNewSoundPool();
        }else {
            creatOldSoundPool();
        }
    }
    // чтоб звук работал на 5+ версиях андройд
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void createNewSoundPool(){
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        sounds = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }
    // чтоб звук работал на версиях андройда ниже 5
    @SuppressWarnings("deprecation")
    protected void creatOldSoundPool() {sounds = new SoundPool(5, AudioManager.STREAM_MUSIC, 0 );}

    // просто звук :3
    private void loadSounds(){
        sound_shot = sounds.load( this,R.raw.shot, 1);
        sound_shot_false = sounds.load( this,R.raw.shot_false, 1);
        sound_baraban = sounds.load( this,R.raw.baraban, 1);
    }
    // команды нажимание и работы кнопки и звук
    public  void onShot(View view){
        if(random == on_shot){
        sounds.play(sound_shot,1.0f,1.0f,1,0,1);
        krov_image.setVisibility(View.VISIBLE);
    }
        else {
            sounds.play(sound_shot_false, 1.0f, 1.0f, 1, 0, 1);
        }
    }

    public  void onShotFalse(View view) {
        sounds.play(sound_shot_false, 1.0f, 1.0f, 1, 0, 1);
    }
    public  void onBaraban(View view) {
        sounds.play(sound_baraban, 1.0f, 1.0f, 1, 0, 1);
        krov_image.setVisibility(View.GONE);
        random = new Random().nextInt(max_number);
         Log.d("MainActivity", "Random number" + random);//<--- команда для проверки рандома
    }
    private void init(){
        krov_image = findViewById(R.id.image_krov);
    }
    // ну все, идите проверять;)

}