package com.codenite.ilaaj.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codenite.ilaaj.activities.AppointmentActivity;
import com.codenite.ilaaj.api.controllers.UserController;
import com.codenite.ilaaj.api.dataModels.User;
import com.codenite.ilaaj.databinding.FragmentMakeAppointmentsBinding;
import com.codenite.ilaaj.recyclerView.adapters.DoctorsAdapter;
import com.codenite.ilaaj.recyclerView.adapters.ItemClickHandler;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MakeAppointmentFragment extends Fragment {
    FragmentMakeAppointmentsBinding binding;
    DoctorsAdapter adapter;
    Context context;
    List<User> users = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMakeAppointmentsBinding.inflate(LayoutInflater.from(getActivity()),container,false);
        context = getContext();
        loadData();
        return binding.getRoot();
    }

    private void loadData(){
        new UserController(context).getAllUser(new UserController.userGetAllHandler() {
            @Override
            public void onSuccess(List<User> list) {
                users = list;
                setUpRecyclerView();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void setUpRecyclerView(){
        adapter = new DoctorsAdapter(context, users, new ItemClickHandler() {
            @Override
            public void onViewClick(int position) {
                Intent i = new Intent(context, AppointmentActivity.class);
                i.putExtra("doctor",users.get(position));
                startActivity(i);
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        binding.recyclerView.setAdapter(adapter);
    }
}
