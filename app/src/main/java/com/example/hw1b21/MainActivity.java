package com.example.hw1b21;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public static final String SOUND_ID = "sound id";
    public static final int SOUND_REQUEST = 0;
    private int current_sound = 0;

    private MediaPlayer buttonPlayer;
    static public Uri[] sounds;
    private boolean pause = false;

    public final static String CONTACT_ID = "contact id";
    public static final int CONTACT_REQUEST = 1;
    private String current_contact = "John Doe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sounds = new Uri[6];                                                                        //Zdefiniowanie dźwięków
        sounds[0] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring01);
        sounds[1] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring02);
        sounds[2] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring03);
        sounds[3] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring04);
        sounds[4] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ringd);
        sounds[5] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mario);

        TextView textContact = findViewById(R.id.MATextContact);                                    //Wyświetlanie aktualnego kontaktu (tekst)
        textContact.setText(current_contact);

        buttonPlayer = new MediaPlayer();                                                           //Zdefiniowanie przycisku do dźwięków
        buttonPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        FloatingActionButton fab = findViewById(R.id.fab);                                          //Funkcjonalność floating action button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //buttonPlayer.setDataSource(getApplicationContext(),sounds[current_sound]);

                    if (pause){
                        buttonPlayer.pause();
                        pause = false;
                    }
                    else {
                        playSound();
                    }
                //buttonPlayer.prepareAsync();
            }
        });


        final Button chSound = (Button) findViewById(R.id.MAbCS);
        final Button chContact = (Button) findViewById(R.id.MAbCC);

        chSound.setOnClickListener(new View.OnClickListener() {                                     //Przycisk "zmiana dźwięku"
            @Override
            public void onClick(View v) {
                Intent soundPick = new Intent(getApplicationContext(), ChangeSoundActivity.class);
                soundPick.putExtra(SOUND_ID, current_sound);
                startActivityForResult(soundPick, SOUND_REQUEST);
            }
        });

        chContact.setOnClickListener(new View.OnClickListener() {                                   //Przycisk "zmiana kontaktu"
            @Override
            public void onClick(View v) {

                Intent contactPick = new Intent(getApplicationContext(), ChangeContactActivity.class);
                contactPick.putExtra(CONTACT_ID, current_contact);
                startActivityForResult(contactPick, CONTACT_REQUEST);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){                  //Funkcja wywołująca kolejną aktywność
        if (resultCode == RESULT_OK){
            if (requestCode == SOUND_REQUEST){                                                      //Zmiana dźwięku
                current_sound = data.getIntExtra(SOUND_ID, 0);
            }
            if (requestCode == CONTACT_REQUEST){                                                    //Zmiana kontaktu
                current_contact = data.getStringExtra(CONTACT_ID);
                TextView contactPick = findViewById(R.id.MATextContact);
                contactPick.setText(current_contact);

                int random = (int) (Math.random() * ((5 - 1) + 1));

                ImageView contactPickImage = findViewById(R.id.MAImageContact);

                switch(random){                                                                     //Losowanie zdjęcia kontaktu
                    case 1:
                        contactPickImage.setImageResource(R.drawable.avatar1);
                        break;
                    case 2:
                        contactPickImage.setImageResource(R.drawable.avatar2);
                        break;
                    case 3:
                        contactPickImage.setImageResource(R.drawable.avatar3);
                        break;
                    case 4:
                        contactPickImage.setImageResource(R.drawable.avatar4);
                        break;
                    case 5:
                        contactPickImage.setImageResource(R.drawable.avatar5);
                        break;
                        default:
                            contactPickImage.setImageResource(R.drawable.avatar6);
                }
            }
        }
        else if (resultCode == RESULT_CANCELED){                                                    //Jeżeli anulowano
            Toast.makeText(getApplicationContext(), getText(R.string.back_message), Toast.LENGTH_SHORT).show();
        }
    }

    @Override                                                                                       //Jeżeli zapauzowano
    protected void onPause(){
        super.onPause();
        //backgroundPlayer.pause();
        buttonPlayer.pause();
        pause = false;
    }

    @Override                                                                                       //Zniszczenie aktywności
    protected void onDestroy(){
        super.onDestroy();
        buttonPlayer.release();
    }

    /*@Override
    protected void onStart(){
        super.onStart();
    }*/

    protected void playSound(){                                                                     //Włączenie dźwięku
        buttonPlayer = MediaPlayer.create(this, sounds[current_sound]);
        buttonPlayer.start();
        buttonPlayer.setLooping(true);
        pause = true;
    }
}
