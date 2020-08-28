package com.codenite.ilaaj.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codenite.ilaaj.api.controllers.UserController;
import com.codenite.ilaaj.api.dataModels.User;
import com.codenite.ilaaj.databinding.FragmentPaymentBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PaymentFragment extends Fragment {
    FragmentPaymentBinding binding;
    String upi, charge;
    Context context;
    User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(getLayoutInflater(),container,false);
        context = getContext();
        loadData();
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getText();
                if(!validate()) {
                    return;
                }
                update();

            }
        });
        return binding.getRoot();
    }

    private void loadData(){
        new UserController(context).getUser(new UserController.userGetHandler() {
            @Override
            public void onSuccess(User user) {
                PaymentFragment.this.user = user;
                setText();
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }

    private void setText(){

    }

    private void getText(){
        upi = binding.upiId.getText().toString();
        charge  = binding.charge.getText().toString();
    }

    private void update(){
        new UserController(context).getUser(new UserController.userGetHandler() {
            @Override
            public void onSuccess(User user) {
                //Set new values
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });
    }

    private boolean validate(){
        if(charge == null){
            Toast.makeText(context, "Please enter a price", Toast.LENGTH_SHORT).show();
            return false;
        }else if(upi == null){
            Toast.makeText(context, "Please enter an UPI ID", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
