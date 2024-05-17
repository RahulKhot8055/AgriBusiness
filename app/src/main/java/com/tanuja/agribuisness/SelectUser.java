package com.tanuja.agribuisness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SelectUser extends AppCompatActivity {

    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);


        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton personradioButton = (RadioButton) findViewById(selectedId);
        if(selectedId==-1){
            Toast.makeText(SelectUser.this,"Nothing selected", Toast.LENGTH_SHORT).show();
        }
        else{

            if (selectedId==1) {
                Intent intent = new Intent(getApplicationContext(), RetriveDataInRecyclerView.class);
                startActivity(intent);
            }
            else if (selectedId==2) {
                Intent intent = new Intent(getApplicationContext(), OtpActivity.class);
                startActivity(intent);
            }
        }
    }
}