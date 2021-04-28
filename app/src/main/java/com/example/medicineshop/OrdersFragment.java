package com.example.medicineshop;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment {
    View v;

    ListView mlistview;
    Button clear;
    ArrayList<Product_Data> mlist = new ArrayList<Product_Data>();
    Product_listadapter adapter;
    SQLitedatabase db;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
         v=inflater.inflate(R.layout.order_fragment,container,false);
        mlistview = v.findViewById(R.id.listview);
        clear=v.findViewById(R.id.allclear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("Warning");
                builder.setMessage("Clear ALL Product");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try
                        {
                            Cursor cursor=db.getdata("SELECT * FROM "+db.ORDER_TABLE_NAME);
                            ArrayList<Integer> arry=new ArrayList<Integer>();
                            while (cursor.moveToNext())
                            {
                                arry.add(cursor.getInt(0));
                            }
                            for(int i=0;i<arry.size();i++)
                            {
                                Integer id=arry.get(i);
                                db.deletebuyproduct(id);
                            }
                            loadfragment(new OrdersFragment());
                            Toast.makeText(getContext(),"successful",Toast.LENGTH_LONG).show();

                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getContext(),"Unsuccessful "+e,Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        db=new SQLitedatabase(getContext());
        try
        {
            final SQLitedatabase db = new SQLitedatabase(getContext());
            List<Product_Data> contacts = db.getOrderData();
            for (Product_Data cn : contacts) {
                mlist.add(cn);
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),""+e,Toast.LENGTH_LONG).show();
        }
        adapter =new Product_listadapter(getContext(),R.layout.product_insert_design,mlist);
        mlistview.setAdapter(adapter);



         return v;
    }
    private void loadfragment(Fragment fragment)
    {
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
