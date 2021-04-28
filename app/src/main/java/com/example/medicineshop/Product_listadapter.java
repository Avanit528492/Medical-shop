package com.example.medicineshop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class Product_listadapter extends ArrayAdapter<Product_Data> {
    Context context;
    int layoutResourceId;
    ArrayList<Product_Data> data;


    public Product_listadapter(Context context,  int layoutResourceId, ArrayList<Product_Data> data) {
        super(context, layoutResourceId,data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ImageHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ImageHolder();
            holder.product_name=(TextView)row.findViewById(R.id.product_name);
            holder.product_price=(TextView)row.findViewById(R.id.product_price);
            holder.product_offer=(TextView)row.findViewById(R.id.product_offer);
            holder.product_quantity=(TextView)row.findViewById(R.id.product_quantity);
            holder.product_image=(ImageView) row.findViewById(R.id.product_image);
            row.setTag(holder);
        }
        else
        {
            holder = (ImageHolder)row.getTag();
        }
        Product_Data DATA = data.get(position);
        holder.product_name.setText(DATA.name);
        holder.product_price.setText("RS "+DATA.price);
        holder.product_offer.setText(DATA.offer);
        holder.product_quantity.setText("x "+DATA.quantity);
        byte[] outImage=DATA.image;
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.product_image.setImageBitmap(theImage);
        return row;
    }
    static class ImageHolder
    {
        TextView product_name,product_price,product_offer,product_quantity;
        ImageView product_image;
    }
}
