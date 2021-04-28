package com.example.medicineshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RateUs extends AppCompatActivity {
    RatingBar rb;
    TextView rbvalue;
    Button submit;
    EditText comment;
    String str_rating,str_name,str_comment;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);
        rb=findViewById(R.id.rateingbar);
        rbvalue=findViewById(R.id.rateingbar_show);
        submit=findViewById(R.id.rateusbutton);
        comment=findViewById(R.id.comment);
        reference = FirebaseDatabase.getInstance().getReference("Rating Review");

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                str_rating=String.valueOf(ratingBar.getRating());
                rbvalue.setText("Rating: "+str_rating);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rate();
                Toast.makeText(getApplicationContext(),"Thank you for Rating",Toast.LENGTH_LONG).show();

            }
        });
    }
    public void rate()
    {
        String s=MainActivity.Activity_id;
        String separator="@";
        int sepInd=s.lastIndexOf(separator);
        final String Id=s.substring(0,sepInd);

        DatabaseReference r=FirebaseDatabase.getInstance().getReference("UserData");
        r.child(Id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                str_name=dataSnapshot.child("full_NAME").getValue(String.class);
                str_comment=comment.getText().toString();
                Rating_DATA data =new Rating_DATA(Id,str_name,str_rating,str_comment);
                reference.child(Id).setValue(data);
                rb.setRating(0);
                rbvalue.setText("Rating: "+0);
                comment.setText("");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}