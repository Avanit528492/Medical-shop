package com.example.medicineshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    ImageButton signup_button;
    EditText sname,semail,smobileno,spassword,sconfirmpassword;
    TextView login_page;
    ProgressBar pb;
    FirebaseAuth firebaseAuth;
    public static DatabaseReference reference;
    public static SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        pb=findViewById(R.id.signupprogressBar);
        login_page=findViewById(R.id.loginpage);
        sname=findViewById(R.id.signup_fullname);
        semail=findViewById(R.id.signupemail);
        smobileno=findViewById(R.id.mobilenumber);
        spassword=findViewById(R.id.signuppassword);
        sconfirmpassword=findViewById(R.id.signupconfirmpassword);
        signup_button=findViewById(R.id.signupbutton);

        reference = FirebaseDatabase.getInstance().getReference("UserData");

        login_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                startActivity(i);
                finish();
            }
        });

        firebaseAuth=FirebaseAuth.getInstance();

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupprocess();

            }
        });
    }
    public void signupprocess()
    {
        String Email_formet="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (sname.getText().toString().isEmpty())
        {
            sname.setError("Enter your name");
        }
        if (semail.getText().toString().isEmpty())
        {
            semail.setError("Enter your Email");
        }
        if (smobileno.getText().toString().isEmpty())
        {
            smobileno.setError("Enter your mobile number");
        }
        if (spassword.getText().toString().isEmpty())
        {
            spassword.setError("Enter your password");
        }
        if (sconfirmpassword.getText().toString().isEmpty())
        {
            sconfirmpassword.setError("Enter your confirm password");
        }
        if (!(sname.getText().toString().isEmpty()) && !(semail.getText().toString().isEmpty()) && !(smobileno.getText().toString().isEmpty()) && !(spassword.getText().toString().isEmpty()) && !(sconfirmpassword.getText().toString().isEmpty()))
        {
            if (!(smobileno.getText().toString().length()==10))
            {
                Toast.makeText(getApplicationContext(),"Please enter valid number",Toast.LENGTH_LONG).show();

            }
            if (!semail.getText().toString().matches(Email_formet))
            {
                Toast.makeText(getApplicationContext(),"Email Formet is Worng",Toast.LENGTH_LONG).show();

            }
            if (!spassword.getText().toString().equals(sconfirmpassword.getText().toString()))
            {
                Toast.makeText(getApplicationContext(),"your password is not same",Toast.LENGTH_LONG).show();
            }
            if (smobileno.getText().toString().length()==10 && semail.getText().toString().matches(Email_formet) && spassword.getText().toString().equals(sconfirmpassword.getText().toString()))
            {
                String Name = sname.getText().toString().trim();
                String Email = semail.getText().toString().trim();
                String MobileNO = smobileno.getText().toString().trim();
                String Password = spassword.getText().toString().trim();

                String separator="@";
                int sepInd=Email.lastIndexOf(separator);

                String Id=Email.substring(0,sepInd);
                User_Data data = new User_Data(Id,Name, Email, MobileNO, Password);
                reference.child(Id).child("Personal Data").setValue(data)
                        .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"SignUp Successful",Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"SignUp UnSuccessful",Toast.LENGTH_LONG).show();
                    }
                });
                sh=getSharedPreferences("SIGNUP_ID",MODE_PRIVATE);
                SharedPreferences.Editor editor=sh.edit();
                editor.putString("id",semail.getText().toString());
                editor.commit();

                sname.setText("");
                semail.setText("");
                smobileno.setText("");
                spassword.setText("");
                sconfirmpassword.setText("");
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"please Enter all details",Toast.LENGTH_LONG);
        }
    }
}