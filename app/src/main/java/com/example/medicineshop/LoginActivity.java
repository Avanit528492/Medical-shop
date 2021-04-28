package com.example.medicineshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    ImageButton login_button;
    EditText email, password;
    TextView forgetpwd,signup_page;
    ProgressBar pb;
    DatabaseReference database;
    FirebaseAuth firebaseAuth;

    boolean click;
    public static SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.loginemail);
        password=findViewById(R.id.loginpassword);
        forgetpwd=findViewById(R.id.forgetpassword);
        signup_page=findViewById(R.id.signup);
        pb=findViewById(R.id.loginprogressBar);
        login_button=findViewById(R.id.loginbutton);



        database = FirebaseDatabase.getInstance().getReference("User");



        signup_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),SignUpActivity.class);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                startActivity(i);
                finish();
            }
        });

        forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forget_showmessage(v);
            }
        });


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginprocess();
            }
        });
    }

    public void loginprocess()
    {
        String separator="@";
        String Email = email.getText().toString();
        int sepInd=Email.lastIndexOf(separator);
        String Id=Email.substring(0,sepInd);
        if (email.getText().toString().isEmpty())
        {
            email.setError("Enter Email");
        }
        if (password.getText().toString().isEmpty())
        {
            password.setError("Enter Email");
        }
        if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty())
        {
            database.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot value:dataSnapshot.getChildren())
                    {

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful())
                    {
                        Toast.makeText(getApplicationContext(),"Login UnSuccessful",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        sh=getSharedPreferences("LOGIN_ID",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sh.edit();
                        editor.putString("id",email.getText().toString());
                        editor.commit();
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                        startActivityForResult(i, 0);
                        finish();
                    }
                }
            });
        }
        else
        {
            Toast.makeText(this,"Please Enter Email and Password",Toast.LENGTH_SHORT).show();
        }
    }


    public void forget_showmessage(View view){
        final AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.forgetpassword,null);
        final EditText forget_email = (EditText)mView.findViewById(R.id.forget_email);
        Button forget_btn_cancel = (Button)mView.findViewById(R.id.forget_btn_cancel);
        Button forget_btn_okay = (Button)mView.findViewById(R.id.forget_btn_okay);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        forget_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        forget_btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth=FirebaseAuth.getInstance();
                auth.sendPasswordResetEmail(forget_email.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(), "Reset password instructions has sent to your email",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Email Don't exist",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        alertDialog.show();
    }

}