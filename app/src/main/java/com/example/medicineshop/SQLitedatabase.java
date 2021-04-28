package com.example.medicineshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class SQLitedatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME=MainActivity.Activity_id+"medicalshop.db";
    public static final int DATABASE_VERSION=1000;
    public static  final String TABLE_NAME=MainActivity.Id+"table";
    public static  final String ORDER_TABLE_NAME=MainActivity.Id+"ordertable";
    public static  final String MYPURCHASE_TABLE_NAME=MainActivity.Id+"mypurchasetable";
    public static final String KEY_ID="id";
    public static final String KEY_PRODUCT_NAME="name";
    public static final String KEY_PRODUCT_PRICE="price";
    public static final String KEY_PRODUCT_OFFER="offer";
    public static final String KEY_PRODUCT_QUANTITY="quantity";
    public static final String KEY_PRODUCT_IMAGE="image";

    public SQLitedatabase(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_PRODUCT_NAME + " TEXT,"
                + KEY_PRODUCT_PRICE + " TEXT,"
                + KEY_PRODUCT_OFFER + " TEXT,"
                + KEY_PRODUCT_QUANTITY + " TEXT,"
                + KEY_PRODUCT_IMAGE + " BLOB" + ")";

        String sql2="CREATE TABLE "+ ORDER_TABLE_NAME+" AS SELECT * FROM "+TABLE_NAME;

        String sql3="CREATE TABLE "+ MYPURCHASE_TABLE_NAME +" AS SELECT * FROM "+TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ORDER_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+MYPURCHASE_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void INSERT_DATA(String name,String price,String offer,String quantity,byte[] image)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues CV=new ContentValues();
        CV.put(KEY_PRODUCT_NAME,name);
        CV.put(KEY_PRODUCT_PRICE,price);
        CV.put(KEY_PRODUCT_OFFER,offer);
        CV.put(KEY_PRODUCT_QUANTITY,quantity);
        CV.put(KEY_PRODUCT_IMAGE,image);
        db.insert(TABLE_NAME,null,CV);
    }

    public List<Product_Data> getALLData()
    {
        List<Product_Data> product_list = new ArrayList<Product_Data>();
        String selectQuery = "SELECT * FROM "+TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Product_Data data = new Product_Data();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setName(cursor.getString(1));
                data.setPrice(cursor.getString(2));
                data.setOffer(cursor.getString(3));
                data.setQuantity(cursor.getString(4));
                data.setImage(cursor.getBlob(5));
                product_list.add(data);
            } while (cursor.moveToNext());
        }
        db.close();
        return product_list;
    }

    public Cursor getdata(String sql)
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery(sql,null);
        return cursor;
    }

    public void deleteproduct(Object item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(item) });
        db.close();
    }
    public void deletebuyproduct(Object item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ORDER_TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(item) });
        db.close();
    }
    public void UPDATE(Object id,String quantity)
    {
        SQLiteDatabase db=getWritableDatabase();
        String str="UPDATE "+TABLE_NAME+" SET "+KEY_PRODUCT_QUANTITY+" = "+quantity+" WHERE "+KEY_ID+" = "+id;
        db.execSQL(str);

    }
    public void InsertBuyData(Object item)
    {
        SQLiteDatabase db=getWritableDatabase();
        String sql="INSERT INTO "+ORDER_TABLE_NAME+" SELECT * FROM "+TABLE_NAME+" Where "+KEY_ID+" = "+item;
        db.execSQL(sql);
    }
    public void InsertMypurchaseData(Object item)
    {
        SQLiteDatabase db=getWritableDatabase();
        String sql="INSERT INTO "+MYPURCHASE_TABLE_NAME+" SELECT * FROM "+TABLE_NAME+" Where "+KEY_ID+" = "+item;
        db.execSQL(sql);
    }
    public List<Product_Data> getOrderData()
    {
        List<Product_Data> product_list = new ArrayList<Product_Data>();
        String selectQuery = "SELECT * FROM "+ORDER_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Product_Data data = new Product_Data();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setName(cursor.getString(1));
                data.setPrice(cursor.getString(2));
                data.setOffer(cursor.getString(3));
                data.setQuantity(cursor.getString(4));
                data.setImage(cursor.getBlob(5));
                product_list.add(data);
            } while (cursor.moveToNext());
        }
        db.close();
        return product_list;
    }
    public List<Product_Data> getmypurchaseData()
    {
        List<Product_Data> product_list = new ArrayList<Product_Data>();
        String selectQuery = "SELECT * FROM "+MYPURCHASE_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Product_Data data = new Product_Data();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setName(cursor.getString(1));
                data.setPrice(cursor.getString(2));
                data.setOffer(cursor.getString(3));
                data.setQuantity(cursor.getString(4));
                data.setImage(cursor.getBlob(5));
                product_list.add(data);
            } while (cursor.moveToNext());
        }
        db.close();
        return product_list;
    }

}
