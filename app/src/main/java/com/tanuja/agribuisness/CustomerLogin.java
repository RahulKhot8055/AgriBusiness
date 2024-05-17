package com.tanuja.agribuisness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CustomerLogin extends AppCompatActivity {

    EditText edUsername;
    EditText edPassword;
    RadioButton PersonradioButton;
    RadioGroup radioGroup;

    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);


        edUsername=findViewById(R.id.ed_usernameLogin);
        edPassword = findViewById(R.id.ed_passwordLogin);
        Button btnLogin = findViewById(R.id.btn_login);
        Button btnSignUp = findViewById(R.id.btn_signup);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        myDB = new DBHelper(this);

        btnSignUp.setOnClickListener(view -> {
            Intent intent=new Intent(CustomerLogin.this,SignUp.class);
            startActivity(intent);

        });

        btnLogin.setOnClickListener(view -> {

            String strUsername1 = edUsername.getText().toString();
            String strPassword1 = edPassword.getText().toString();



            if(strUsername1.matches("[a-z]")|| strPassword1.matches("[0-9]"))
            {
                Toast.makeText(CustomerLogin.this, "Please enter the Credentials.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Boolean result = myDB.checkusernamePassword(strUsername1,strPassword1);

                if(result)
                {

                    Intent intent = new Intent(getApplicationContext(), AddVegetables.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(CustomerLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }



        });
    }
}