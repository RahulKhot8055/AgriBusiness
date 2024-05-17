package com.tanuja.agribuisness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VegetableAdapter extends RecyclerView.Adapter<VegetableAdapter.ViewHolder> {

    Context context;
    List<VegModel> vegModelList;

    public VegetableAdapter(Context context, List<VegModel> vegModelList) {
        this.context = context;
        this.vegModelList = vegModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_row,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //here we will bind it

        VegModel vegModel=vegModelList.get(position);
        holder.tvLast.setText("Name :"+vegModel.getLastname());
        holder.tvFirst.setText("UPI id :"+vegModel.getFirstname());

        String imageUri = null;
        imageUri= vegModel.getImage();
        Picasso.get().load(imageUri).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return vegModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvFirst,tvLast;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.image_rv_id);
            tvFirst=itemView.findViewById(R.id.tvfirstname_rv_id);
            tvLast=itemView.findViewById(R.id.tvlastname_id);

        }
    }
}
