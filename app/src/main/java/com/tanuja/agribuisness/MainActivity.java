package com.tanuja.agribuisness;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    EditText edUsername;
    EditText edPassword;

    DBHelper myDB;
    private String noWhiteSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();

        // providing title for the ActionBar
        actionBar.setTitle("Agribusiness");

        edUsername=findViewById(R.id.ed_usernameLogin);
        edPassword = findViewById(R.id.ed_passwordLogin);
        Button btnLogin = findViewById(R.id.btn_login);
        Button btnSignUp = findViewById(R.id.btn_signup);

        myDB = new DBHelper(this);

        btnSignUp.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,SignUp.class);
            startActivity(intent);

        });

        btnLogin.setOnClickListener(view -> {

            String strUsername1 = edUsername.getText().toString();
            String strPassword1 = edPassword.getText().toString();



            if (strUsername1.matches("^[a-zA-Z]*$") && strPassword1.matches("^[0-9]*$"))
            {
                Boolean result = myDB.checkusernamePassword(strUsername1,strPassword1);

                if(result) {
                    Intent intent = new Intent(getApplicationContext(), Choose.class);
                    startActivity(intent);
                }
            }
            else
            {
                Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }


        });

           }


}
