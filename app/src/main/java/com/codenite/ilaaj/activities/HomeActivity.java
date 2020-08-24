package com.codenite.ilaaj.activities;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.codenite.ilaaj.R;
import com.codenite.ilaaj.databinding.ActivityHomeBinding;
import com.codenite.ilaaj.fragments.ConversationsFragment;
import com.codenite.ilaaj.fragments.DashboardFragment;
import com.codenite.ilaaj.fragments.MakeAppointmentFragment;
import com.codenite.ilaaj.fragments.ManageDocsFragment;
import com.codenite.ilaaj.fragments.ProfileFragment;
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
        changeFragment(0);
        setUpSideNav();



    }

    private void setUpSideNav(){
        binding.sideNav.navigation1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home){
                    changeFragment(0);
                }else if(item.getItemId() == R.id.make_appointment){
                    changeFragment(1);
                }else if(item.getItemId() == R.id.conversation){
                    changeFragment(2);
                }else if(item.getItemId()== R.id.manage_documents){
                    changeFragment(3);
                }
                binding.drawer.closeDrawer(Gravity.LEFT);
                return false;
            }
        });

        binding.sideNav.navigation2.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.profile){
                    changeFragment(4);
                }else if(item.getItemId() == R.id.logout){

                }
                binding.drawer.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }

    public void changeFragment(int ind){
        switch (ind){
            case 0:
                fragment = new DashboardFragment(this);
                break;
            case 1:
                fragment = new MakeAppointmentFragment();
                break;
            case 2:
                fragment = new ConversationsFragment();
                break;
            case 3:
                fragment = new ManageDocsFragment();
                break;
            case 4:
                fragment = new ProfileFragment();
                break;
        }
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(binding.fragmentFrame.getId(),fragment).commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        binding.drawer.openDrawer(Gravity.LEFT);
        return super.onSupportNavigateUp();
    }
}