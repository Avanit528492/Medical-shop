package com.example.medicineshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.io.ByteArrayOutputStream;

public class Category_Diabeticneeds extends AppCompatActivity {
    SliderLayout sliderLayout;

    TextView product_name_1,product_price_1,product_offer_1;
    ImageView product_image_1;
    Button product_button_1;

    TextView product_name_2,product_price_2,product_offer_2;
    ImageView product_image_2;
    Button product_button_2;

    TextView product_name_3,product_price_3,product_offer_3;
    ImageView product_image_3;
    Button product_button_3;

    TextView product_name_4,product_price_4,product_offer_4;
    ImageView product_image_4;
    Button product_button_4;

    SQLitedatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category__diabeticneeds);
        db= new SQLitedatabase(this);


        //slideshow

        sliderLayout=findViewById(R.id.diabetic_sliderlayout);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL);
        sliderLayout.setScrollTimeInSec(2);
        slideshow();
        Product1();
        Product2();
        Product3();
        Product4();
    }
    //slideshow
    public void slideshow()
    {
        for(int i=1;i<=3;i++)
        {
            SliderView sliderView=new SliderView(getApplicationContext());
            switch (i)
            {
                case 1:
                    sliderView.setImageDrawable(R.drawable.diabetic_advertisement);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.advertisement4);
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.healthneed_advertisement_2);
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
            sliderLayout.addSliderView(sliderView);
        }
    }

    public void Product1()
    {
        product_name_1=findViewById(R.id.category_diabetic_name_1);
        product_price_1=findViewById(R.id.category_diabetic_price_1);
        product_offer_1=findViewById(R.id.category_diabetic_offer_1);
        product_image_1=findViewById(R.id.category_diabetic_image_1);
        product_button_1=findViewById(R.id.category_diabetic_button_1);

        product_button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String separator="Rs ";
                int sepInd=product_price_1.getText().toString().indexOf(separator);
                String Product_price_1=product_price_1.getText().toString().substring(sepInd+separator.length());
                btn_showMessage(view,product_name_1.getText().toString(),Product_price_1,product_offer_1.getText().toString(),product_image_1);
            }

        });

    }

    public void Product2()
    {
        product_name_2=findViewById(R.id.category_diabetic_name_2);
        product_price_2=findViewById(R.id.category_diabetic_price_2);
        product_offer_2=findViewById(R.id.category_diabetic_offer_2);
        product_image_2=findViewById(R.id.category_diabetic_image_2);
        product_button_2=findViewById(R.id.category_diabetic_button_2);

        product_button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String separator="Rs ";
                int sepInd=product_price_2.getText().toString().indexOf(separator);
                String Product_price_2=product_price_2.getText().toString().substring(sepInd+separator.length());
                btn_showMessage(view,product_name_2.getText().toString(),Product_price_2,product_offer_2.getText().toString(),product_image_2);
            }
        });
    }
    public void Product3()
    {
        product_name_3=findViewById(R.id.category_diabetic_name_3);
        product_price_3=findViewById(R.id.category_diabetic_price_3);
        product_offer_3=findViewById(R.id.category_diabetic_offer_3);
        product_image_3=findViewById(R.id.category_diabetic_image_3);
        product_button_3=findViewById(R.id.category_diabetic_button_3);

        product_button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String separator="Rs ";
                int sepInd=product_price_3.getText().toString().indexOf(separator);
                String Product_price_3=product_price_3.getText().toString().substring(sepInd+separator.length());
                btn_showMessage(view,product_name_3.getText().toString(),Product_price_3,product_offer_3.getText().toString(),product_image_3);
            }
        });
    }

    public void Product4()
    {
        product_name_4=findViewById(R.id.category_diabetic_name_4);
        product_price_4=findViewById(R.id.category_diabetic_price_4);
        product_offer_4=findViewById(R.id.category_diabetic_offer_4);
        product_image_4=findViewById(R.id.category_diabetic_image_4);
        product_button_4=findViewById(R.id.category_diabetic_button_4);

        product_button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String separator="Rs ";
                int sepInd=product_price_4.getText().toString().indexOf(separator);
                String Product_price_4=product_price_4.getText().toString().substring(sepInd+separator.length());
                btn_showMessage(view,product_name_4.getText().toString(),Product_price_4,product_offer_4.getText().toString(),product_image_4);
            }
        });

    }
    public static byte[] imagetobyte(ImageView image)
    {
        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] byteimage=stream.toByteArray();
        return byteimage;
    }

    public void btn_showMessage(View view, final String item_name, final String item_price, final String item_offer, final ImageView item_image){
        final AlertDialog.Builder alert = new AlertDialog.Builder(Category_Diabeticneeds.this);
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
                    if (item_quantity.getText().toString().equals("0"))
                    {
                        Toast.makeText(getApplicationContext(),"Enter More then 0 Quntity",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        db.INSERT_DATA(item_name,item_price,item_offer,item_quantity.getText().toString(),imagetobyte(item_image));
                        Toast.makeText(getApplicationContext(),"Add Product Successful",Toast.LENGTH_LONG).show();
                        alertDialog.dismiss();
                    }
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