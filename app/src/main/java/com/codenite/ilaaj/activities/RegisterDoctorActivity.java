package com.codenite.ilaaj.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codenite.ilaaj.api.controllers.UserController;
import com.codenite.ilaaj.api.dataModels.User;
import com.codenite.ilaaj.databinding.ActivityRegisterDoctorBinding;
import com.codenite.ilaaj.utils.SharedPrefs;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterDoctorActivity extends AppCompatActivity {
    ActivityRegisterDoctorBinding binding;
    User user = new User();
    SharedPrefs prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefs = new SharedPrefs(this);

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readText();
                uploadData();
            }
        });

    }

    private void uploadData(){
        new UserController(this).addUser(user, new UserController.addUserHandler() {
            @Override
            public void onSuccess(User user) {
                prefs.saveUserId(user.getId());
                prefs.saveNewUser(false);
                goToNextStep();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(RegisterDoctorActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToNextStep(){
        Intent i= new Intent(this, DoctorHomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    private void readText(){
        user.setName(binding.name.getText().toString());
        user.setPhone(binding.phone.getText().toString());
        user.setDoctor(true);
        user.setNewUser(false);

    }

    private boolean validate(){
        String[] messages = new String[]{
                "Please enter a name",
                "Please enter an age",
                "Please enter a phone number"
        };
        if(binding.name.getText().length() ==0){
            Snackbar.make(binding.getRoot(),messages[0],Snackbar.LENGTH_SHORT);
            return false;
        }else if(binding.phone.getText().length() ==0){
            Snackbar.make(binding.getRoot(),messages[1],Snackbar.LENGTH_SHORT);
            return false;
        }

        return true;
    }
}