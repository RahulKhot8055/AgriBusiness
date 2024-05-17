package com.tanuja.agribuisness;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddVegetables extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    AlertDialog.Builder builder;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    ImageButton imageButton;
    EditText pName,pPrice,pupi;
    Button btnInsert;


    private static final int Gallery_code=1;
    Uri imageUrl=null;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();

        // providing title for the ActionBar
        actionBar.setTitle("Agribusiness");





        setContentView(R.layout.activity_add_vegetables);


        imageButton=findViewById(R.id.imageButton);

        pName=findViewById(R.id.productName);
        pPrice=findViewById(R.id.productPrice);
        //pupi=findViewById(R.id.upi);
        btnInsert=findViewById(R.id.btnInsert);


        builder = new AlertDialog.Builder(this);
        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference().child("Farmers");
        mStorage=FirebaseStorage.getInstance();

        progressDialog=new ProgressDialog(this);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_code);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==Gallery_code && resultCode==RESULT_OK )
        {
            imageUrl=data.getData();
            imageButton.setImageURI(imageUrl);

        }

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pn= pName.getText().toString();
                String pp=pPrice.getText().toString();
                //String pu=pupi.getText().toString();



                if (!pn.isEmpty() && !pp.isEmpty() && imageUrl != null)
                {
                    progressDialog.setTitle("uploading.......");
                    progressDialog.show();



                    StorageReference filePath=mStorage.getReference().child("imagePost").child(imageUrl.getLastPathSegment());
                    filePath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {

                                    String t=task.getResult().toString();

                                    DatabaseReference newPost = mRef.push();

                                    newPost.child("Name").setValue(pn);
                                    newPost.child("Price").setValue(pp);
                                    //newPost.child("Upi").setValue(pu);
                                    newPost.child("image").setValue(task.getResult().toString());


                                    builder.setMessage("Do you want to go back ?")
                                            .setCancelable(false)
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    finish();
                                                    Toast.makeText(getApplicationContext(),"",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    //  Action for 'NO' Button
                                                    dialog.cancel();
                                                    Toast.makeText(getApplicationContext(),"",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                    //Creating dialog box
                                    AlertDialog alert = builder.create();
                                    //Setting the title manually
                                    alert.show();


                            progressDialog.dismiss();



                                    //Intent intent=new Intent(AddVegetables.this,RetriveDataInRecyclerView.class);
                                    //startActivity(intent);





                                }
                            });
                        }
                    });



                   
                }



            }
        });



    }
}