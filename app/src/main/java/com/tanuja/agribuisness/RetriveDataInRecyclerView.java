package com.tanuja.agribuisness;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class RetriveDataInRecyclerView extends AppCompatActivity {



    //here we will retrive data

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;

    List<ProductModels> productModelsList;
   // Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_data_in_recycler_view);


        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference("Farmers");
        mStorage=FirebaseStorage.getInstance();
        recyclerView=findViewById(R.id.recyclerview_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productModelsList= new ArrayList<>();
        productAdapter=new ProductAdapter(RetriveDataInRecyclerView.this,productModelsList);

        mRef.addChildEventListener(new ChildEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                ProductModels productModels=dataSnapshot.getValue(ProductModels.class);
                productModelsList.add(productModels);
                productAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(productAdapter);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}