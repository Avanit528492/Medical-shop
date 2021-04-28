package com.example.medicineshop;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class AccountFragment extends Fragment {
    View v;
    TextView name,email,mobileno;
    TextView accountedit;
    CardView mypurchases,info,rateus,contactus;
    ProgressBar progressBar;
    DatabaseReference reference;
    String Id;
    ImageView imageView;
    String Name,Email,MobilNO;
    String p;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.account_fragment,container,false);
        name=v.findViewById(R.id.account_name);
        email=v.findViewById(R.id.account_email);
        mobileno=v.findViewById(R.id.account_mobileno);
        progressBar=v.findViewById(R.id.accountprogressbar);

        String s=MainActivity.Activity_id;
        String separator="@";
        int sepInd=s.lastIndexOf(separator);
        Id=s.substring(0,sepInd);
        Toast.makeText(getContext(),""+Id,Toast.LENGTH_LONG).show();




try {
    reference = FirebaseDatabase.getInstance().getReference("UserData");
    reference.child(Id).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            Name=dataSnapshot.child("full_NAME").getValue(String.class);
            Email=dataSnapshot.child("email").getValue(String.class);
            MobilNO=dataSnapshot.child("mobile_NO").getValue(String.class);
            name.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
            mobileno.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            p=Name.substring(0,1);
            imageshow();
            name.setText(Name);
            email.setText(Email);
            mobileno.setText(MobilNO);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
}
catch (Exception e)
{
    Toast.makeText(getContext(),""+e,Toast.LENGTH_LONG).show();
}



        mypurchases=v.findViewById(R.id.mypurchases);
        info=v.findViewById(R.id.information);
        rateus=v.findViewById(R.id.rateus);
        contactus=v.findViewById(R.id.contactus);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"App version 0.0.1(Basic Demo)",Toast.LENGTH_LONG).show();
            }
        });

        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),RateUs.class);
                startActivity(i);
            }
        });

        mypurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),MyPurchase.class);
                startActivity(i);
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),Contact_Us.class);
                startActivity(i);
            }
        });

        accountedit=v.findViewById(R.id.account_edit);
        accountedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                View mView = getLayoutInflater().inflate(R.layout.account_edit,null);
                final TextView editemail=mView.findViewById(R.id.edit_email);
                final EditText edit_name = (EditText)mView.findViewById(R.id.edit_name);
                final EditText edit_mobileno = (EditText)mView.findViewById(R.id.edit_mobileno);
                Button btn_cancel = (Button)mView.findViewById(R.id.cancel);
                Button btn_okay = (Button)mView.findViewById(R.id.save);
                alert.setView(mView);
                editemail.setText(Email);
                edit_name.setText(name.getText().toString());
                edit_mobileno.setText(mobileno.getText().toString());
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
                        reference.child(Id).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                dataSnapshot.getRef().child("full_NAME").setValue(edit_name.getText().toString());
                                dataSnapshot.getRef().child("mobile_NO").setValue(edit_mobileno.getText().toString());
                                alertDialog.dismiss();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                alertDialog.dismiss();
                            }
                        });
                    }
                });
                alertDialog.show();
            }
        });




        return v;
    }
    public void imageshow()
    {
        imageView=v.findViewById(R.id.personimage);
        if (p.equals("a")||p.equals("A"))
        {
            imageView.setImageResource(R.drawable.a);
        }
        else if (p.equals("b")||p.equals("B"))
        {
            imageView.setImageResource(R.drawable.b);
        }
        else if (p.equals("c")||p.equals("C"))
        {
            imageView.setImageResource(R.drawable.c);
        }
        else if (p.equals("d")||p.equals("D"))
        {
            imageView.setImageResource(R.drawable.d);
        }
        else if (p.equals("e")||p.equals("E"))
        {
            imageView.setImageResource(R.drawable.e);
        }
        else if (p.equals("f")||p.equals("F"))
        {
            imageView.setImageResource(R.drawable.f);
        }
        else if (p.equals("g")||p.equals("G"))
        {
            imageView.setImageResource(R.drawable.g);
        }
        else if (p.equals("h")||p.equals("H"))
        {
            imageView.setImageResource(R.drawable.h);
        }
        else if (p.equals("i")||p.equals("I"))
        {
            imageView.setImageResource(R.drawable.i);
        }
        else if (p.equals("j")||p.equals("J"))
        {
            imageView.setImageResource(R.drawable.j);
        }
        else if (p.equals("k")||p.equals("K"))
        {
            imageView.setImageResource(R.drawable.k);
        }
        else if (p.equals("l")||p.equals("L"))
        {
            imageView.setImageResource(R.drawable.l);
        }
        else if (p.equals("m")||p.equals("M"))
        {
            imageView.setImageResource(R.drawable.m);
        }
        else if (p.equals("n")||p.equals("N"))
        {
            imageView.setImageResource(R.drawable.n);
        }
        else if (p.equals("o")||p.equals("O"))
        {
            imageView.setImageResource(R.drawable.o);
        }
        else if (p.equals("p")||p.equals("P"))
        {
            imageView.setImageResource(R.drawable.p);
        }
        else if (p.equals("q")||p.equals("Q"))
        {
            imageView.setImageResource(R.drawable.q);
        }
        else if (p.equals("r")||p.equals("R"))
        {
            imageView.setImageResource(R.drawable.r);
        }
        else if (p.equals("s")||p.equals("S"))
        {
            imageView.setImageResource(R.drawable.s);
        }
        else if (p.equals("t")||p.equals("T"))
        {
            imageView.setImageResource(R.drawable.t);
        }
        else if (p.equals("u")||p.equals("U"))
        {
            imageView.setImageResource(R.drawable.u);
        }
        else if (p.equals("v")||p.equals("V"))
        {
            imageView.setImageResource(R.drawable.v);
        }
        else if (p.equals("w")||p.equals("W"))
        {
            imageView.setImageResource(R.drawable.w);
        }
        else if (p.equals("x")||p.equals("X"))
        {
            imageView.setImageResource(R.drawable.x);
        }
        else if (p.equals("y")||p.equals("Y"))
        {
            imageView.setImageResource(R.drawable.y);
        }
        else if (p.equals("z")||p.equals("Z"))
        {
            imageView.setImageResource(R.drawable.z);
        }
        else
        {
            imageView.setImageResource(R.drawable.imageicon);
        }

    }

}
