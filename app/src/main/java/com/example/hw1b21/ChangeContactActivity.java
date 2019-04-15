package com.example.hw1b21;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static java.security.AccessController.getContext;

public class ChangeContactActivity extends AppCompatActivity {

    private int selected_contact = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_contact);

    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton)view).isChecked();
        if (checked){
            RadioGroup group = findViewById(R.id.RadioGroup);
            switch (group.getId()){
                case R.id.CCArb1:
                    selected_contact = 0;
                    break;
                case R.id.CCArb2:
                    selected_contact = 1;
                    break;
                case R.id.CCArb3:
                    selected_contact = 2;
                    break;
                case R.id.CCArb4:
                    selected_contact = 3;
                    break;
                case R.id.CCArb5:
                    selected_contact = 4;
            }
        }
    }

    public void setContact(View view){
        Intent data = new Intent();
        data.putExtra(MainActivity.CONTACT_ID, selected_contact);
        setResult(RESULT_OK, data);
        finish();
    }

    public void nosetContact(View view){
        setResult(RESULT_CANCELED);
        finish();
    }
}
