package com.example.medicineshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class
MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    DatabaseReference reference;
    public static String Activity_id,Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences login_id=getSharedPreferences("LOGIN_ID",MODE_PRIVATE);
        SharedPreferences signup_id=getSharedPreferences("SIGNUP_ID",MODE_PRIVATE);
        String Login_Id=login_id.getString("id","");
        String Signup_Id=signup_id.getString("id","");


        if (!Signup_Id.isEmpty())
        {
            Activity_id=signup_id.getString("id","");
        }
        else if(!Login_Id.isEmpty())
        {
            Activity_id=login_id.getString("id","");
        }
        else
        {
            Activity_id="Logout Account";
        }

        String separator="@";
        int sepInd=Activity_id.lastIndexOf(separator);
        Id=Activity_id.substring(0,sepInd);



        bottomNavigationView=findViewById(R.id.bottom_navigation);
        loadfragment(new HomeFragment());


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment;
                switch (menuItem.getItemId())
                {
                    case R.id.navigation_home:
                        fragment=new HomeFragment();
                        loadfragment(fragment);
                        return true;
                    case R.id.navigation_account:
                        fragment=new AccountFragment();
                        loadfragment(fragment);
                        return true;
                    case R.id.navigation_orders:
                        fragment=new OrdersFragment();
                        loadfragment(fragment);
                        return true;
                }
                return false;
            }
        });

    }
    private void loadfragment(Fragment fragment)
    {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shooping_cart,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                SharedPreferences login_id=getSharedPreferences("LOGIN_ID",MODE_PRIVATE);
                SharedPreferences signup_id=getSharedPreferences("SIGNUP_ID",MODE_PRIVATE);

                SharedPreferences.Editor login_editor=login_id.edit();
                SharedPreferences.Editor signup_editor=signup_id.edit();

                login_editor.clear();
                login_editor.commit();
                signup_editor.clear();
                signup_editor.commit();

                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

                finish();
                break;
            case R.id.shopping_cart:
                try {
                    Intent in=new Intent(getApplicationContext(),AddToCart.class);
                    Intent inte=new Intent(getApplicationContext(),Product_listadapter.class);
                    inte.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    in.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(in);
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),""+e,Toast.LENGTH_LONG).show();
                }


        }
        return super.onOptionsItemSelected(item);
    }
}