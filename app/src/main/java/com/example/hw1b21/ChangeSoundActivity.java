package com.example.hw1b21;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import static java.security.AccessController.getContext;

public class ChangeSoundActivity extends AppCompatActivity {


    private int selected_sound = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_sound);

        /*Intent received_intent = getIntent();
        Integer sound_id = received_intent.getIntExtra(MainActivity.SOUND_ID, 0);*/

        setSpinnerItemSelectedListener();
    }

    public void setSpinnerItemSelectedListener() {                                                  //Obsługa spinnera
        Spinner spinner = (Spinner) findViewById(R.id.spinner_ss);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {       //Wybranie dźwięku
                    switch (i) {
                        case 0:
                            selected_sound = 0;
                            break;
                        case 1:
                            selected_sound = 1;
                            break;
                        case 2:
                            selected_sound = 2;
                            break;
                        case 3:
                            selected_sound = 3;
                            break;
                        case 4:
                            selected_sound = 4;
                            break;
                        case 5:
                            selected_sound = 5;
                            break;
                        default:
                            selected_sound = 5;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    public void setSound (View view){                                                               //Przycisk "OK"
        Intent data_sound = new Intent();
        data_sound.putExtra(MainActivity.SOUND_ID, selected_sound);

        setResult(RESULT_OK, data_sound);
        finish();
    }

    public void notsetSound (View view){                                                            //Przycisk "Anuluj"
        setResult(RESULT_CANCELED);
        finish();
    }
}
