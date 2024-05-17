package com.tanuja.agribuisness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class recycle extends Activity {
    private List<Item> itemList = new ArrayList<>();
    private RecyclerView recyclerview;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview=(RecyclerView)findViewById(R.id.recycler_view);
        mAdapter = new MyAdapter(itemList);
        RecyclerView.LayoutManager mLayoutManger = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(mLayoutManger);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(mAdapter);
        prepareItem();
    }
    private void prepareItem() {
        Item item = new Item(R.drawable.i1,"Wopro","Wipro is software compnay");
        itemList.add(item);
        item = new Item(R.drawable.appicon,"TCS","TCS is service base compnay");
        itemList.add(item);
        item = new Item(R.drawable.ic_next1,"Ericsson","Ericsson is a networking compnay ");
        itemList.add(item);
        item= new Item(R.drawable.appicon_bg,"Evon","Evon is hub of software devlopment ");
        itemList.add(item);
        mAdapter.notifyDataSetChanged();
        recyclerview.setAdapter(mAdapter);
    }
}