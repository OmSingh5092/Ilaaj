package com.codenite.ilaaj.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codenite.ilaaj.api.models.User;
import com.codenite.ilaaj.databinding.ActivityRegisterBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readText();
                if(validate()){
                    uploadData();
                }
            }
        });
    }

    private void uploadData(){
        goToNextStep();
    }

    private void goToNextStep(){
        Intent i= new Intent(this,PreviousReportsActivity.class);
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
        if(user.getName().length() ==0){
            Snackbar.make(binding.getRoot(),messages[0],Snackbar.LENGTH_SHORT);
            return false;
        }else if(user.getPhone().length() ==0){
            Snackbar.make(binding.getRoot(),messages[1],Snackbar.LENGTH_SHORT);
            return false;
        }else if(user.getAge() ==0){
            Snackbar.make(binding.getRoot(),messages[2],Snackbar.LENGTH_SHORT);
            return false;
        }

        return true;
    }
}