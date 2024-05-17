package com.tanuja.agribuisness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Consultation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);

        TextView linkTextView1 = findViewById(R.id.L1);
        TextView linkTextView2 = findViewById(R.id.L2);
        TextView linkTextView3 = findViewById(R.id.L3);
        TextView linkTextView4 = findViewById(R.id.L4);


        // method to redirect to provided link
        linkTextView1.setMovementMethod(LinkMovementMethod.getInstance());

        // method to change color of link
        linkTextView1.setLinkTextColor(Color.RED);

        linkTextView2.setMovementMethod(LinkMovementMethod.getInstance());

        // method to change color of link
        linkTextView2.setLinkTextColor(Color.RED);

        linkTextView3.setMovementMethod(LinkMovementMethod.getInstance());

        // method to change color of link
        linkTextView3.setLinkTextColor(Color.RED);

        linkTextView4.setMovementMethod(LinkMovementMethod.getInstance());

        // method to change color of link
        linkTextView4.setLinkTextColor(Color.RED);

        Button b = findViewById(R.id.b);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),AddVegetables.class);
                startActivity(intent);
            }
        });


    }
}