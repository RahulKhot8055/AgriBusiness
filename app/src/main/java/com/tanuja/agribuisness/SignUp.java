package com.tanuja.agribuisness;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText edUsername;
    EditText edPassword;
    EditText edconfirmpassword;
    Button btncreateuser;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edUsername=findViewById(R.id.ed_username);
        edPassword = findViewById(R.id.ed_password);
        edconfirmpassword = findViewById(R.id.ed_confirmpassword);
        btncreateuser = findViewById(R.id.btn_create_user);

        myDB = new DBHelper(this);

        btncreateuser.setOnClickListener(view -> {

            String strUsername = edUsername.getText().toString();
            String strPassword = edPassword.getText().toString();
            String strConfirmpassword = edconfirmpassword.getText().toString();


            if(strUsername.isEmpty() || strPassword.isEmpty() || strConfirmpassword.isEmpty())
            {
                Toast.makeText(SignUp.this, "fill all the fields", Toast.LENGTH_SHORT).show();

            }
            else
            {

                if (strPassword.equals(strConfirmpassword) && strUsername.matches("^[a-zA-Z]*$") && strPassword.matches("^[0-9]*$"))
                {
                    Boolean usercheckResult = myDB.checkusername(strUsername);

                    if (!usercheckResult)
                    {
                        Boolean regResult = myDB.insertData(strUsername,strPassword);

                        if (regResult)
                        {
                            Toast.makeText(SignUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),OtpActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(SignUp.this, "User already Exists.\n Please Sign In ", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}

