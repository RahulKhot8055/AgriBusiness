package com.tanuja.agribuisness;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class CustomLogin extends AppCompatActivity {

    EditText edUsername;
    EditText edPassword;

    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edUsername=findViewById(R.id.ed_usernameLogin);
        edPassword = findViewById(R.id.ed_passwordLogin);
        Button btnLogin = findViewById(R.id.btn_login);

        myDB = new DBHelper(this);


        btnLogin.setOnClickListener(view -> {

            String strUsername1 = edUsername.getText().toString();
            String strPassword1 = edPassword.getText().toString();



            if(strUsername1.matches("^[a-z]")|| strPassword1.matches("^[0-9]"))
            {
                Toast.makeText(CustomLogin.this, "Please enter the Credentials.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Boolean result = myDB.checkusernamePassword(strUsername1,strPassword1);

                if(result)
                {

                    Intent intent = new Intent(getApplicationContext(), RetriveDataInRecyclerView.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(CustomLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}
