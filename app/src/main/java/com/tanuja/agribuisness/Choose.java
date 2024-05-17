package com.tanuja.agribuisness;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Choose extends AppCompatActivity {

    RadioGroup rg;
    RadioButton f,c;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        ActionBar actionBar = getSupportActionBar();

        // providing title for the ActionBar
        actionBar.setTitle("Agribusiness");



        rg=findViewById(R.id.rg);
        f=findViewById(R.id.farmer);
        c=findViewById(R.id.customer);
        b=findViewById(R.id.submit);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (f.isChecked())
                {
                    Intent intent = new Intent(getApplicationContext(),Consultation.class);
                    startActivity(intent);
                }
                else if (c.isChecked())
                {
                    Intent intent = new Intent(getApplicationContext(),RetriveDataInRecyclerView.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(Choose.this, "Please select one option...........", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}