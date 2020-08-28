package com.codenite.ilaaj.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codenite.ilaaj.api.controllers.UserController;
import com.codenite.ilaaj.api.dataModels.User;
import com.codenite.ilaaj.databinding.ActivityRegisterBinding;
import com.codenite.ilaaj.utils.SharedPrefs;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterPatientActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;

    User user = new User();
    SharedPrefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
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
                goToNextStep();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(RegisterPatientActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToNextStep(){
        Intent i= new Intent(this, PreviousReportsActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void readText(){
        user.setName(binding.name.getText().toString());
        if(binding.age.getText().toString().length()!=0){
            user.setAge(Integer.valueOf(binding.age.getText().toString()));
        }
        user.setPhone(binding.phone.getText().toString());
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
        }else if(binding.age.getText().length() ==0){
            Snackbar.make(binding.getRoot(),messages[2],Snackbar.LENGTH_SHORT);
            return false;
        }

        return true;
    }
}