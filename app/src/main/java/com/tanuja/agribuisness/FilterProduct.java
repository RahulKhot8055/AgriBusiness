package com.tanuja.agribuisness;


import android.widget.Filter;

import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterProduct extends Filter {

    private AdapterProductSeller adapter;
    private ArrayList<ModelProduct> filterList;


    public FilterProduct(AdapterProductSeller adapter, ArrayList<ModelProduct> filterList){

        this.adapter=adapter;
        this.filterList=filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results= new FilterResults();
        //validate data for search
        if(constraint != null && constraint.length()>0)
        {
            //search filed not empty, searching something , perform search


            //change Upper Case
            constraint  = constraint.toString().toUpperCase();

            ArrayList<ModelProduct> filterModels = new ArrayList<>();

            for(int i=0;i<filterList.size();i++){
                //Check and search by title and category
                if (filterList.get(i).getProductTitle().toUpperCase().contains(constraint) ||
                        filterList.get(i).getProductcategory().toUpperCase().contains(constraint) ){
                    //Add filtered data to list
                    filterModels.add(filterList.get(i));
                }
            }
            results.count=filterModels.size();
            results.values=filterModels;
        }
        else{
            //search filed  empty not serching return original or complete list

            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.productList = (ArrayList<ModelProduct>)results.values;
        adapter.notifyDataSetChanged();

    }
}