package com.example.medicineshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    //covid_product

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

    TextView product_name_5,product_price_5,product_offer_5;
    ImageView product_image_5;
    Button product_button_5;

    TextView product_name_6,product_price_6,product_offer_6;
    ImageView product_image_6;
    Button product_button_6;

    TextView covid_viewall;


    //topoffer_product

    TextView product_name_1o,product_price_1o,product_offer_1o;
    ImageView product_image_1o;
    Button product_button_1o;

    TextView product_name_2o,product_price_2o,product_offer_2o;
    ImageView product_image_2o;
    Button product_button_2o;

    TextView product_name_3o,product_price_3o,product_offer_3o;
    ImageView product_image_3o;
    Button product_button_3o;

    TextView product_name_4o,product_price_4o,product_offer_4o;
    ImageView product_image_4o;
    Button product_button_4o;

    TextView product_name_5o,product_price_5o,product_offer_5o;
    ImageView product_image_5o;
    Button product_button_5o;

    TextView product_name_6o,product_price_6o,product_offer_6o;
    ImageView product_image_6o;
    Button product_button_6o;
    TextView topoffer_viewall;

    SQLitedatabase db;


    //catogory_cardview
    CardView cbabyneeds,cpersonalcare,cnutrition,chealthneeds,cvitamins,cdiabeticneeds;

    //top Brand
    CardView vicks,oral_b,sensodyne,horlicks,lifeboy,dubur;
    TextView brand_viewall;

    //medicalstore
    CardView netmeds,medpluse,medidart,imgayush,buydrug,zigy;
    TextView medicalstore_viewall;

    SliderLayout sliderLayout1;
    ViewFlipper flipper;
    View v;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
       v= inflater.inflate(R.layout.home_fragment,container,false);
        db= new SQLitedatabase(getContext());

       //covid_product
        Covid_Product1();
        Covid_Product2();
        Covid_Product3();
        Covid_Product4();
        Covid_Product5();
        Covid_Product6();
        covid_viewall=v.findViewById(R.id.covid_viewall);
        covid_viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"No More Product Available",Toast.LENGTH_LONG).show();
            }
        });

        //topoffer_product
        Topoffer_Product1();
        Topoffer_Product2();
        Topoffer_Product3();
        Topoffer_Product4();
        Topoffer_Product5();
        Topoffer_Product6();
        topoffer_viewall=v.findViewById(R.id.topoffer_viewall);
        topoffer_viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"No More Product Available",Toast.LENGTH_LONG).show();
            }
        });

       //cardview_category

        cbabyneeds=v.findViewById(R.id.babyneeds);
        cpersonalcare=v.findViewById(R.id.personalcare);
        cnutrition=v.findViewById(R.id.nutrition);
        chealthneeds=v.findViewById(R.id.healthneeds);
        cvitamins=v.findViewById(R.id.vitamins);
        cdiabeticneeds=v.findViewById(R.id.diabeticneeds);

        //top Brand

        vicks=v.findViewById(R.id.vicks);
        oral_b=v.findViewById(R.id.oral_b);
        sensodyne=v.findViewById(R.id.sensodyne);
        horlicks=v.findViewById(R.id.horlicks);
        lifeboy=v.findViewById(R.id.lifeboy);
        dubur=v.findViewById(R.id.dabur);
        brand_viewall=v.findViewById(R.id.brand_viewall);
        BrandIntent();

        //medicalstore
        netmeds=v.findViewById(R.id.medicalstore_netmeds);
        medpluse=v.findViewById(R.id.medicalstore_medplus);
        medidart=v.findViewById(R.id.medicalstore_medidart);
        imgayush=v.findViewById(R.id.medicalstore_imgayush);
        buydrug=v.findViewById(R.id.medicalstore_buydrug);
        zigy=v.findViewById(R.id.medicalstore_zigy);
        medicalstore_viewall=v.findViewById(R.id.medicalstore_viewall);
        medicalstore_viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"No more Store",Toast.LENGTH_LONG).show();
            }
        });
        MedicalstoreIntent();


        //flipper


       flipper=v.findViewById(R.id.viewflopper);
       flipper.isFlipping();
       flipper.setFlipInterval(3000);
       flipper.setAutoStart(true);

       //slideview

        sliderLayout1=v.findViewById(R.id.sliderlayout);
        sliderLayout1.setIndicatorAnimation(SliderLayout.Animations.FILL);
        sliderLayout1.setScrollTimeInSec(2);
        slideshow1();


       //catogory_intent
        Category_Intent();

        return v;
    }
    //medicalstore


    public void MedicalstoreIntent() {

        netmeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url=Uri.parse("https://www.netmeds.com/");
                Intent i=new Intent(Intent.ACTION_VIEW,url);
                startActivity(i);
            }
        });
        medpluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url=Uri.parse("https://www.medplusmart.com/");
                Intent i=new Intent(Intent.ACTION_VIEW,url);
                startActivity(i);
            }
        });
        medidart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url=Uri.parse("https://medidart.freshdesk.com/support/home");
                Intent i=new Intent(Intent.ACTION_VIEW,url);
                startActivity(i);
            }
        });
        imgayush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url=Uri.parse("https://www.1mg.com/categories");
                Intent i=new Intent(Intent.ACTION_VIEW,url);
                startActivity(i);
            }
        });
        buydrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url=Uri.parse("http://www.buydrug.in/");
                Intent i=new Intent(Intent.ACTION_VIEW,url);
                startActivity(i);
            }
        });
        zigy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url=Uri.parse("https://www.facebook.com/zigyofficial/");
                Intent i=new Intent(Intent.ACTION_VIEW,url);
                startActivity(i);
            }
        });
    }

    //topoffer_product

    public void Topoffer_Product1()
    {
        product_name_1o=v.findViewById(R.id.topoffer_name_1);
        product_price_1o=v.findViewById(R.id.topoffer_price_1);
        product_offer_1o=v.findViewById(R.id.topoffer_offer_1);
        product_image_1o=v.findViewById(R.id.topoffer_image_1);
        product_button_1o=v.findViewById(R.id.topoffer_button_1);

        product_button_1o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String separator="Rs ";
                int sepInd=product_price_1o.getText().toString().indexOf(separator);
                String Product_price_1=product_price_1o.getText().toString().substring(sepInd+separator.length());
                btn_showMessage(view,product_name_1o.getText().toString(),Product_price_1,product_offer_1o.getText().toString(),product_image_1o);
            }

        });

    }

    public void Topoffer_Product2()
    {
        product_name_2o=v.findViewById(R.id.topoffer_name_2);
        product_price_2o=v.findViewById(R.id.topoffer_price_2);
        product_offer_2o=v.findViewById(R.id.topoffer_offer_2);
        product_image_2o=v.findViewById(R.id.topoffer_image_2);
        product_button_2o=v.findViewById(R.id.topoffer_button_2);

        product_button_2o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String separator="Rs ";
                int sepInd=product_price_2o.getText().toString().indexOf(separator);
                String Product_price_2=product_price_2o.getText().toString().substring(sepInd+separator.length());
                btn_showMessage(view,product_name_2o.getText().toString(),Product_price_2,product_offer_2o.getText().toString(),product_image_2o);
            }
        });
    }
    public void Topoffer_Product3()
    {
        product_name_3o=v.findViewById(R.id.topoffer_name_3);
        product_price_3o=v.findViewById(R.id.topoffer_price_3);
        product_offer_3o=v.findViewById(R.id.topoffer_offer_3);
        product_image_3o=v.findViewById(R.id.topoffer_image_3);
        product_button_3o=v.findViewById(R.id.topoffer_button_3);

        product_button_3o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String separator="Rs ";
                int sepInd=product_price_3o.getText().toString().indexOf(separator);
                String Product_price_3=product_price_3o.getText().toString().substring(sepInd+separator.length());
                btn_showMessage(view,product_name_3o.getText().toString(),Product_price_3,product_offer_3o.getText().toString(),product_image_3o);
            }
        });
    }

    public void Topoffer_Product4()
    {
        product_name_4o=v.findViewById(R.id.topoffer_name_4);
        product_price_4o=v.findViewById(R.id.topoffer_price_4);
        product_offer_4o=v.findViewById(R.id.topoffer_offer_4);
        product_image_4o=v.findViewById(R.id.topoffer_image_4);
        product_button_4o=v.findViewById(R.id.topoffer_button_4);

        product_button_4o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String separator="Rs ";
                int sepInd=product_price_4o.getText().toString().indexOf(separator);
                String Product_price_4=product_price_4o.getText().toString().substring(sepInd+separator.length());
                btn_showMessage(view,product_name_4o.getText().toString(),Product_price_4,product_offer_4o.getText().toString(),product_image_4o);
            }
        });

    }

    public void Topoffer_Product5()
    {
        product_name_5o=v.findViewById(R.id.topoffer_name_5);
        product_price_5o=v.findViewById(R.id.topoffer_price_5);
        product_offer_5o=v.findViewById(R.id.topoffer_offer_5);
        product_image_5o=v.findViewById(R.id.topoffer_image_5);
        product_button_5o=v.findViewById(R.id.topoffer_button_5);

        product_button_5o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String separator="Rs ";
                int sepInd=product_price_5o.getText().toString().indexOf(separator);
                String Product_price_5=product_price_5o.getText().toString().substring(sepInd+separator.length());
                btn_showMessage(view,product_name_5o.getText().toString(),Product_price_5,product_offer_5o.getText().toString(),product_image_5o);
            }
        });

    }

    public void Topoffer_Product6()
    {
        product_name_6o=v.findViewById(R.id.topoffer_name_6);
        product_price_6o=v.findViewById(R.id.topoffer_price_6);
        product_offer_6o=v.findViewById(R.id.topoffer_offer_6);
        product_image_6o=v.findViewById(R.id.topoffer_image_6);
        product_button_6o=v.findViewById(R.id.topoffer_button_6);

        product_button_6o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String separator="Rs ";
                int sepInd=product_price_6o.getText().toString().indexOf(separator);
                String Product_price_6=product_price_6o.getText().toString().substring(sepInd+separator.length());
                btn_showMessage(view,product_name_6o.getText().toString(),Product_price_6,product_offer_6o.getText().toString(),product_image_6o);
            }
        });

    }

    //covid_product

    public void Covid_Product1()
    {
        product_name_1=v.findViewById(R.id.covid_name_1);
        product_price_1=v.findViewById(R.id.covid_price_1);
        product_offer_1=v.findViewById(R.id.covid_offer_1);
        product_image_1=v.findViewById(R.id.covid_image_1);
        product_button_1=v.findViewById(R.id.covid_button_1);

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

    public void Covid_Product2()
    {
        product_name_2=v.findViewById(R.id.covid_name_2);
        product_price_2=v.findViewById(R.id.covid_price_2);
        product_offer_2=v.findViewById(R.id.covid_offer_2);
        product_image_2=v.findViewById(R.id.covid_image_2);
        product_button_2=v.findViewById(R.id.covid_button_2);

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
    public void Covid_Product3()
    {
        product_name_3=v.findViewById(R.id.covid_name_3);
        product_price_3=v.findViewById(R.id.covid_price_3);
        product_offer_3=v.findViewById(R.id.covid_offer_3);
        product_image_3=v.findViewById(R.id.covid_image_3);
        product_button_3=v.findViewById(R.id.covid_button_3);

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

    public void Covid_Product4()
    {
        product_name_4=v.findViewById(R.id.covid_name_4);
        product_price_4=v.findViewById(R.id.covid_price_4);
        product_offer_4=v.findViewById(R.id.covid_offer_4);
        product_image_4=v.findViewById(R.id.covid_image_4);
        product_button_4=v.findViewById(R.id.covid_button_4);

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

    public void Covid_Product5()
    {
        product_name_5=v.findViewById(R.id.covid_name_5);
        product_price_5=v.findViewById(R.id.covid_price_5);
        product_offer_5=v.findViewById(R.id.covid_offer_5);
        product_image_5=v.findViewById(R.id.covid_image_5);
        product_button_5=v.findViewById(R.id.covid_button_5);

        product_button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String separator="Rs ";
                int sepInd=product_price_5.getText().toString().indexOf(separator);
                String Product_price_5=product_price_5.getText().toString().substring(sepInd+separator.length());
                btn_showMessage(view,product_name_5.getText().toString(),Product_price_5,product_offer_5.getText().toString(),product_image_5);
            }
        });

    }

    public void Covid_Product6()
    {
        product_name_6=v.findViewById(R.id.covid_name_6);
        product_price_6=v.findViewById(R.id.covid_price_6);
        product_offer_6=v.findViewById(R.id.covid_offer_6);
        product_image_6=v.findViewById(R.id.covid_image_6);
        product_button_6=v.findViewById(R.id.covid_button_6);

        product_button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String separator="Rs ";
                int sepInd=product_price_6.getText().toString().indexOf(separator);
                String Product_price_6=product_price_6.getText().toString().substring(sepInd+separator.length());
                btn_showMessage(view,product_name_6.getText().toString(),Product_price_6,product_offer_6.getText().toString(),product_image_6);
            }
        });

    }
    //category_intent

    public void Category_Intent()
    {
        cbabyneeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),Category_Babyneeds.class);
                startActivity(i);
            }
        });

        cpersonalcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),Category_Personalcare.class);
                startActivity(i);
            }
        });

        cnutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),Category_Nutrition.class);
                startActivity(i);
            }
        });

        chealthneeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),Category_Healthneeds.class);
                startActivity(i);
            }
        });

        cvitamins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),Category_Vitamins.class);
                startActivity(i);
            }
        });

        cdiabeticneeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),Category_Diabeticneeds.class);
                startActivity(i);
            }
        });
    }

    //top Brand Intent
    public void  BrandIntent()
    {
        vicks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url=Uri.parse("https://vicks.com/en-us");
                Intent i=new Intent(Intent.ACTION_VIEW,url);
                startActivity(i);
            }
        });
        oral_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url=Uri.parse("https://oralb.com/");
                Intent i=new Intent(Intent.ACTION_VIEW,url);
                startActivity(i);
            }
        });
        sensodyne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url=Uri.parse("https://www.sensodyne.in/");
                Intent i=new Intent(Intent.ACTION_VIEW,url);
                startActivity(i);
            }
        });
        horlicks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url=Uri.parse("https://www.horlicks.in/");
                Intent i=new Intent(Intent.ACTION_VIEW,url);
                startActivity(i);
            }
        });
        lifeboy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url=Uri.parse("https://www.lifebuoy.in/");
                Intent i=new Intent(Intent.ACTION_VIEW,url);
                startActivity(i);
            }
        });
        dubur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url=Uri.parse("https://www.dabur.com/");
                Intent i=new Intent(Intent.ACTION_VIEW,url);
                startActivity(i);
            }
        });
        brand_viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"No More Brands",Toast.LENGTH_LONG).show();
            }
        });
    }


    //slide view

    public void slideshow1()
    {
        for(int i=1;i<=4;i++)
        {
            SliderView sliderView=new SliderView(getContext());
            switch (i)
            {
                case 1:
                    sliderView.setImageDrawable(R.drawable.sildeshow1);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.slideshow2);
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.slideshow3);
                    break;
                case 4:
                    sliderView.setImageDrawable(R.drawable.slideshow4);
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
            sliderLayout1.addSliderView(sliderView);
        }
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
        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
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
                        Toast.makeText(getContext(),"Enter More then 0 Quntity",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        db.INSERT_DATA(item_name,item_price,item_offer,item_quantity.getText().toString(),imagetobyte(item_image));
                        Toast.makeText(getContext(),"Add Product Successful",Toast.LENGTH_LONG).show();
                        alertDialog.dismiss();
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getContext(),""+e,Toast.LENGTH_LONG).show();
                }

            }
        });
        alertDialog.show();
    }
}
