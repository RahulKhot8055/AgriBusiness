package com.tanuja.agribuisness;

import static android.text.AutoText.get;

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {



    Context context;
    List<ProductModels> productModelsList;

    public ProductAdapter(Context context, List<ProductModels> productModelsList) {
        this.context = context;
        this.productModelsList = productModelsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.design_row_for_recyclerview,parent,false);

        return new ViewHolder(v);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {

        //here we will bind it


        ProductModels productModels=productModelsList.get(position);




        //ProductModels productModels=new ProductModels();
        //ProductModels productModels1 = new ProductModels(productModels.getProductName(), productModels.getProductPrice(), productModels.getImage());

        String imageUri = null;

        //String pname= productModels.getProductName();

        imageUri=productModels.getImage();
        Picasso.get().load(imageUri).into(holder.imageView);


        holder.ProductPrice.setText(productModels.getPrice());
        holder.ProductName.setText(productModels.getName());




    }

    @Override
    public int getItemCount()
    {
        return productModelsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //here declare design


        ImageView imageView;
        TextView ProductName,ProductPrice;


        public ViewHolder(View itemView) {
            super(itemView);


            imageView=itemView.findViewById(R.id.image_recyclerview_id);
            ProductName=itemView.findViewById(R.id.ProductName_recyclerView);
            ProductPrice=itemView.findViewById(R.id.ProductPrice_recyclerView);

            itemView.setOnClickListener(this);




        }

        @Override
        public void onClick(View view)
        {
            Intent intent = new Intent(context,PayActivity.class);
            intent.putExtra("upi",ProductName.getText());
            intent.putExtra("price",ProductPrice.getText());


            context.startActivity(intent);

        }


    }

}
