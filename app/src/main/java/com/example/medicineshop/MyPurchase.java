package com.example.medicineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyPurchase extends AppCompatActivity {
    ListView mlistview;
    ArrayList<Product_Data> mlist = new ArrayList<Product_Data>();
    Product_listadapter adapter;
    SQLitedatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_purchase);
        mlistview = findViewById(R.id.mypurchases_listview);
        db=new SQLitedatabase(this);
        try
        {
            final SQLitedatabase db = new SQLitedatabase(this);
            List<Product_Data> contacts = db.getmypurchaseData();
            for (Product_Data cn : contacts) {
                mlist.add(cn);
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
        }
        adapter =new Product_listadapter(this,R.layout.product_insert_design,mlist);
        mlistview.setAdapter(adapter);
    }
}