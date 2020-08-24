package com.codenite.ilaaj.activities;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.codenite.ilaaj.R;
import com.codenite.ilaaj.databinding.ActivityHomeBinding;
import com.codenite.ilaaj.fragments.DashboardFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    FragmentTransaction ft;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        //Setting up fragment fragment transaction
        ft = getSupportFragmentManager().beginTransaction();

        setUpSideNav();

    }

    private void setUpSideNav(){
        addFragment(0);
        binding.sideNav.navigation1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.make_appointment){

                }else if(item.getItemId() == R.id.conversation){

                }else if(item.getItemId()== R.id.manage_documents){

                }
                return false;
            }
        });

        binding.sideNav.navigation2.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.profile){

                }else if(item.getItemId() == R.id.logout){

                }
                return false;
            }
        });
    }

    void addFragment(int ind){

        switch (ind){
            case 0:
                fragment = new DashboardFragment();
                ft.add(binding.fragmentFrame.getId(),fragment).commit();
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        binding.drawer.openDrawer(Gravity.LEFT);
        return super.onSupportNavigateUp();
    }
}