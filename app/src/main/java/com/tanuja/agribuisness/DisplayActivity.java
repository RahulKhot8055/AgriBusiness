package com.tanuja.agribuisness;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class DisplayActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        imageView=findViewById(R.id.imageView);
        byte[] byteArrayExtra = getIntent().getByteArrayExtra("photo");

        //BitmapOptions is optional you can create bitmap without this also. This is the description of its use from google developer docs.
        //BitmapFactory.Options: null-ok; Options that control downsampling and whether the image should be completely decoded, or just is size returned.

        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayExtra, 0, byteArrayExtra.length, new BitmapFactory.Options());

        //or
         //bitmap = BitmapFactory.decodeByteArray(byteArrayExtra, 0, byteArrayExtra.length);


        imageView.setImageBitmap(bitmap);
    }
}