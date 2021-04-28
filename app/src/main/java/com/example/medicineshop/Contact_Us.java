package com.example.medicineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Contact_Us extends AppCompatActivity {
    ImageView insta,facebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);
        insta=findViewById(R.id.instagram);
        facebook=findViewById(R.id.facebook);

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"It's Just For Design",Toast.LENGTH_LONG).show();
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"It's Just For Design",Toast.LENGTH_LONG).show();
            }
        });
    }
}