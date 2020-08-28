package com.codenite.ilaaj.activities;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.codenite.ilaaj.R;
import com.codenite.ilaaj.databinding.ActivityDoctorHomeBinding;
import com.codenite.ilaaj.fragments.ConversationsFragment;
import com.codenite.ilaaj.fragments.DashboardDoctorFragment;
import com.codenite.ilaaj.fragments.PaymentFragment;
import com.codenite.ilaaj.fragments.ProfileFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class DoctorHomeActivity extends AppCompatActivity {
    ActivityDoctorHomeBinding binding;
    FragmentTransaction ft;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorHomeBinding.inflate(getLayoutInflater());
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
                }else if(item.getItemId() == R.id.payment){
                    changeFragment(1);
                }else if(item.getItemId() == R.id.conversation){
                    changeFragment(2);
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
                fragment = new DashboardDoctorFragment();
                break;
            case 1:
                fragment = new PaymentFragment();
                break;
            case 2:
                fragment = new ConversationsFragment();
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

    private void signOut(){

    }
}