package com.example.newsnow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.newsnow.Adapters.HorizontalRecycerAdapter;
import com.example.newsnow.Fragments.Home_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainLayout extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Button btn;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        Log.d("---","username : password : "+mUser.getDisplayName()+mUser.getEmail());
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new Home_Fragment())
                .commit();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId())
                {
                    case R.id.menu_home :
                        fragment = new Home_Fragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment)
                            .commit();
                return true;
            }
        });

    }
}
