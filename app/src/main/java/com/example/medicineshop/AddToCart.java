package com.example.medicineshop;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddToCart extends AppCompatActivity {
    ListView listview;
    Button buynow;
    TextView total;
    ArrayList<Product_Data> mlist = new ArrayList<Product_Data>();
    Product_listadapter adapter;
    int ID;
    int value;
    SQLitedatabase  db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        listview = findViewById(R.id.listview1);
        buynow=findViewById(R.id.buynow);
        total=findViewById(R.id.total);
        db=new SQLitedatabase(this);
        Toast.makeText(getApplicationContext(),"Long Press The Item",Toast.LENGTH_SHORT).show();
        try {
            Cursor cu=db.getdata("SELECT * FROM "+db.TABLE_NAME);
            ArrayList<String> price=new ArrayList<String>();
            ArrayList<String> quantity=new ArrayList<String>();

            while (cu.moveToNext())
            {
                price.add(cu.getString(4));
                quantity.add(cu.getString(2));


            }
            int value=0;
            for(int i=0;i<price.size();i++)
            {
                value = value+(Integer.valueOf(price.get(i))*Integer.valueOf(quantity.get(i)));
                total.setText("Total Price: Rs "+value);
            }


        }
        catch (Exception e)
        {
            total.setText(""+e);
        }

        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(AddToCart.this);
                builder.setTitle("Warning");
                builder.setMessage("Buy The Product");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try
                        {
                            Cursor cursor=db.getdata("SELECT * FROM "+db.TABLE_NAME);
                            ArrayList<Integer> arry=new ArrayList<Integer>();
                            while (cursor.moveToNext())
                            {
                                arry.add(cursor.getInt(0));
                            }
                            for(int i=0;i<arry.size();i++)
                            {
                                Integer id=arry.get(i);
                                db.InsertBuyData(id);
                                db.InsertMypurchaseData(id);
                                db.deleteproduct(id);
                            }
                            Toast.makeText(getApplicationContext(),"successful ",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),AddToCart.class);
                            finish();
                            overridePendingTransition(0,0);
                            startActivity(intent);
                            overridePendingTransition(0,0);

                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(),"Unsuccessful "+e,Toast.LENGTH_SHORT).show();
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

        List<Product_Data> contacts = db.getALLData();
        for (Product_Data cn : contacts) {
            mlist.add(cn);
        }
        adapter =new Product_listadapter(AddToCart.this,R.layout.product_insert_design,mlist);
        listview.setAdapter(adapter);

        //listviewclick

        listview.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                ID= (int) listview.getItemIdAtPosition(position);
                CharSequence[] item={"Update","Delete"};

                AlertDialog.Builder builder=new AlertDialog.Builder(AddToCart.this);
                builder.setTitle("Choose an action");
                builder.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i==0)
                        {
                            Cursor cursor=db.getdata("SELECT * FROM "+db.TABLE_NAME);
                            ArrayList<Integer> arryid=new ArrayList<Integer>();
                            while (cursor.moveToNext())
                            {
                                arryid.add(cursor.getInt(0));
                            }
                            Update(arryid.get(position));
                        }
                        if (i==1)
                        {
                            Cursor cursor=db.getdata("SELECT * FROM "+db.TABLE_NAME);
                            ArrayList<Integer> arryid=new ArrayList<Integer>();
                            while (cursor.moveToNext())
                            {
                                arryid.add(cursor.getInt(0));
                            }
                            showDialogDelete(arryid.get(position));
                        }

                    }
                });
                builder.show();
                return true;
            }
        });



    }

    private void showDialogDelete(final int ilist) {
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("warning");
        builder.setMessage("Are you sure delete item");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try
                {
                    db.deleteproduct(ilist);
                    Intent intent=new Intent(getApplicationContext(),AddToCart.class);
                    finish();
                    overridePendingTransition(0,0);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    Toast.makeText(getApplicationContext(),"Delete successful",Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Delete unsuccessful"+e,Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    public void Update(final int id){
        final AlertDialog.Builder alert = new AlertDialog.Builder(AddToCart.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_box_layout,null);
        final EditText item_quantity = (EditText)mView.findViewById(R.id.quantity);
        Button btn_cancel = (Button)mView.findViewById(R.id.btn_cancel);
        Button btn_okay = (Button)mView.findViewById(R.id.btn_okay);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.UPDATE(id,item_quantity.getText().toString());
                    Intent intent=new Intent(getApplicationContext(),AddToCart.class);
                    finish();
                    overridePendingTransition(0,0);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    Toast.makeText(getApplicationContext(),"Update Successful",Toast.LENGTH_LONG).show();
                    alertDialog.dismiss();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
                }

            }
        });
        alertDialog.show();
    }

}