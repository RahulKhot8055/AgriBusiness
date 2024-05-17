package com.tanuja.agribuisness;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterProductSeller extends RecyclerView.Adapter<AdapterProductSeller.HolderProductSeller> implements Filterable
{

    private Context context;
    public ArrayList<ModelProduct> productList,filterList;
    private  FilterProduct filter;


    public AdapterProductSeller(Context context,ArrayList<ModelProduct> productList)
    {
        this.context=context;
        this.productList=productList;
        this.filterList=productList;
    }

    @NonNull
    @Override
    public HolderProductSeller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate layout
        View view= LayoutInflater.from(context).inflate(R.layout.raw_product_seller,parent,false);
        return new HolderProductSeller(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderProductSeller holder, int position) {
        //get data
        ModelProduct modelProduct= productList.get(position);
        String id= modelProduct.getProductId();
        String uid= modelProduct.getUid();
        String discountAvailable= modelProduct.getDiscountAvailabel();
        String discountNote= modelProduct.getDiscountNote();
        String discountPrice= modelProduct.getDiscountPrice();
        String Productcategory= modelProduct.getProductcategory();
        String productDescription= modelProduct.getProductDescription();
        String icon= modelProduct.getProductIcon();
        String quantity= modelProduct.getProducQuantity();
        String title= modelProduct.getProductTitle();
        String timestamp= modelProduct.getTimetamp();
        String originalprice= modelProduct.getOriginalPrice();




        // setData
        holder.titleTv.setText(title);
        holder.quantityTv.setText(quantity);
        holder.discountNoteTv.setText(discountNote);
        holder.discountPriceTv.setText("$"+discountPrice);
        holder.origanalPriceTv.setText("$"+originalprice);
        holder.productIconTv.setImageResource(R.drawable.ic_baseline_add_shopping_cart_24);




        if (discountAvailable.equals("true")){
            holder.discountPriceTv.setVisibility(View.VISIBLE);
            holder.discountNoteTv.setVisibility(View.VISIBLE);
            holder.origanalPriceTv.setPaintFlags(holder.origanalPriceTv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }
        else {
            holder.discountPriceTv.setVisibility(View.GONE);
            holder.discountNoteTv.setVisibility(View.GONE);

        }


        try{
            //Picasso.get().load(icon).placeholder(R.drawable.ic_add_shopping_primary).into(holder.productIconTv);

            Picasso.get().load(icon).placeholder(R.drawable.ic_baseline_add_shopping_cart_24).into(holder.productIconTv);

        }catch (Exception e){
            holder.productIconTv.setImageResource(R.drawable.ic_baseline_add_shopping_cart_24);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //handles items click
            }
        });




    }

    @Override
    public int getItemCount() {

        return productList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter==null){
            filter=new FilterProduct(this,filterList);
        }
        return filter;
    }




    protected RecyclerView.ViewHolder getItemView(LayoutInflater inflater, ViewGroup parent) {
        return new HolderProductSeller(inflater.inflate(R.layout.first_fragment, parent, false));
    }

    protected RecyclerView.ViewHolder getHeaderView(LayoutInflater inflater, ViewGroup parent) {
        return new HeaderViewHolder(inflater.inflate(R.layout.activity_addveggie, parent, false));
    }

    protected RecyclerView.ViewHolder getFooterView(LayoutInflater inflater, ViewGroup parent) {
        return new FooterViewHolder(inflater.inflate(R.layout.raw_product_seller, parent, false));
    }




    class HolderProductSeller extends RecyclerView.ViewHolder {
        private ImageView productIconTv;
        private TextView discountNoteTv,titleTv,quantityTv,discountPriceTv,origanalPriceTv;

        public HolderProductSeller(@NonNull View itemView) {
            super(itemView);


            productIconTv=itemView.findViewById(R.id.productIconTv);
            discountNoteTv= itemView.findViewById(R.id.discountNoteTv);
            titleTv= itemView.findViewById(R.id.titleTv);
            quantityTv= itemView.findViewById(R.id.quantityTv);
            discountPriceTv= itemView.findViewById(R.id.discountPriceTv);
            origanalPriceTv= itemView.findViewById(R.id.origanalPriceTv);




        }

        void bind(String item) {
            discountNoteTv.setText(item);
            titleTv.setText(item);
            quantityTv.setText(item);
            discountPriceTv.setText(item);
            origanalPriceTv.setText(item);




        }
    }


    class HeaderViewHolder extends RecyclerView.ViewHolder {
        HeaderViewHolder(View view) {
            super(view);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        FooterViewHolder(View view) {
            super(view);
        }
    }
}
