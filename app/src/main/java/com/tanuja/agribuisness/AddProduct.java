package com.tanuja.agribuisness;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AddProduct extends AppCompatActivity  implements RecylerViewInterface {

    private TextView tabproductTv, tabOrderTv, filterProductTv;
    private ImageButton logoutbutton, editProfileBtn, addProductBtn;
    private ImageView profileIv;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private RelativeLayout productsRl, OrdersRl;
    private EditText searchProductEt;
    private ImageButton filterProductBtn;
    private RecyclerView productsRv;


    private AdapterProductSeller adapterProductSeller;
    private ArrayList<ModelProduct> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        /*
        nameTv= findViewById(R.id.nameTv);
        shopName= findViewById(R.id.shopName);
        EmailTv= findViewById(R.id.EmailTv);
        profileIv=findViewById(R.id.profileIv);

         */


        profileIv = findViewById(R.id.productIconIv);
        logoutbutton = findViewById(R.id.logoutBtn);
        editProfileBtn = findViewById(R.id.editProfileBtn);
        tabproductTv = findViewById(R.id.tabProductsTv);
        tabOrderTv = findViewById(R.id.tabOrdersTv);
        addProductBtn = findViewById(R.id.add_product);
        productsRl = findViewById(R.id.productsRl);
        OrdersRl = findViewById(R.id.ordersRl);
        searchProductEt = findViewById(R.id.searchProductEt);
        filterProductBtn = findViewById(R.id.filterProductBtn);
        filterProductTv = findViewById(R.id.filterProductTv);
        productsRv = findViewById(R.id.productsRv);


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait....");
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth = FirebaseAuth.getInstance();

        //checkUser();
        loadAllProducts();
        showProductsUI();


        searchProductEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {

                try {
                    adapterProductSeller.getFilter().filter(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        logoutbutton.setOnClickListener(view -> {
            makeMeOffline();
        });

        editProfileBtn.setOnClickListener(view -> {
            startActivity(new Intent(AddProduct.this, ProfileEditSellerActivity.class));
        });

        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(AddProduct.this, Addveggie.class));

            }
        });

        tabproductTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //load products
                showProductsUI();
            }
        });

        tabOrderTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //load orders
                showOrdersUI();
            }
        });

        filterProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AddProduct.this);
                builder.setTitle("Choose Category")
                        .setItems(Constants.productCategories1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String selected = Constants.productCategories1[which];
                                filterProductTv.setText(selected);

                                if (selected.equals("All")) {
                                    loadAllProducts();
                                } else {
                                    loadFilteredProduct(selected);
                                }
                            }
                        })
                        .show();
            }
        });

    }

    private void showProductsUI() {

        //show orders ui and hide products ui

        productsRl.setVisibility(View.VISIBLE);
        OrdersRl.setVisibility(View.GONE);

        tabproductTv.setTextColor(getResources().getColor(R.color.black));
        tabproductTv.setBackgroundResource(R.drawable.sgape_rect_04);

        tabOrderTv.setTextColor(getResources().getColor(R.color.white));
        tabOrderTv.setBackgroundColor(getResources().getColor(android.R.color.transparent));


    }

    private void showOrdersUI() {

        //show products ui and hide orders ui

        productsRl.setVisibility(View.GONE);
        OrdersRl.setVisibility(View.VISIBLE);

        tabproductTv.setTextColor(getResources().getColor(R.color.white));
        tabproductTv.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        tabOrderTv.setTextColor(getResources().getColor(R.color.black));
        tabOrderTv.setBackgroundResource(R.drawable.sgape_rect_04);


    }

    private void loadAllProducts() {
        productList = new ArrayList<>();

        // Init Data
        /*ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add("Example " + i);
        }

        // Add Adapter
        AddProduct adapter = new AddProduct();
        RecyclerView recyclerView = null;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

         */


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(Objects.requireNonNull(firebaseAuth.getUid())).child("Products")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //productList.clear();

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                            productList.add(modelProduct);


                        }
                        adapterProductSeller = new AdapterProductSeller(AddProduct.this, productList);

                        productsRv.setAdapter(adapterProductSeller);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }
                });

    }

    private void makeMeOffline() {

        progressDialog.setMessage("logging out...");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("Online", "false");

        //Update value to database
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseAuth.getUid()).updateChildren(hashMap)
                .addOnSuccessListener((OnSuccessListener) (aVoid) -> {
                    //Update sussfully
                    firebaseAuth.signOut();
                    //checkUser();
                })
                .addOnFailureListener((e) -> {
                    //fail update
                    progressDialog.dismiss();
                    Toast.makeText(AddProduct.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                });


    }

    private void loadFilteredProduct(String selected) {

        productList = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(firebaseAuth.getUid()).child("Products")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        // productList.clear();

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String productCategory = "" + ds.child("productCategory").getValue();

                            if (selected.equals(productCategory)) {
                                ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                                productList.add(modelProduct);
                            }

                        }
                        adapterProductSeller = new AdapterProductSeller(AddProduct.this, productList);

                        productsRv.setAdapter(adapterProductSeller);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }
                });


    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(AddProduct.this,PayActivity.class);
        startActivity(intent);

    }








/*
    private void checkUser(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user== null){
            startActivity(new Intent(AddProduct.this,MainActivity.class));
            finish();
        }
        else {
            //loadMyInfo();
        }
    }

 */

/*
    private void loadMyInfo(){
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Users");
        ref.orderByChild("uid").equalTo(firebaseAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @SuppressLint("ResourceType")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds: dataSnapshot.getChildren())
                        {
                           String name=""+ds.child("name").getValue();
                            String accountType=""+ds.child("accountType").getValue();
                            String email=""+ds.child("email").getValue();
                            String shopNAme=""+ds.child("shopNAme").getValue();


                            String profileImage=""+ds.child("profileImage").getValue();



                           //set data to UI


                            nameTv.setText(name);
                            shopName.setText(shopNAme);
                            EmailTv.setText(email);





                            try {

                                Picasso.get().load(profileImage).placeholder(R.id.);
                            }
                            catch (Exception e){
                                profileIv.setImageResource(R.drawable.ic_store);
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

        }

 */

}
