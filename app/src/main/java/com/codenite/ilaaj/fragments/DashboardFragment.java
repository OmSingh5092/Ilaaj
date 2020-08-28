package com.codenite.ilaaj.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codenite.ilaaj.activities.ConversationActivity;
import com.codenite.ilaaj.activities.HomeActivity;
import com.codenite.ilaaj.api.controllers.AppointmentController;
import com.codenite.ilaaj.api.dataModels.Appointment;
import com.codenite.ilaaj.databinding.FragmentDashboardBinding;
import com.codenite.ilaaj.recyclerView.adapters.BookedAppointmentsAdapter;
import com.codenite.ilaaj.recyclerView.adapters.ItemClickHandler;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class DashboardFragment extends Fragment {
    FragmentDashboardBinding binding;
    HomeActivity activity;
    BookedAppointmentsAdapter adapter;
    List<Appointment> data;
    public DashboardFragment(HomeActivity activity){
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(LayoutInflater.from(getActivity()),container,false);

        binding.appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.changeFragment(1);
            }
        });
        binding.conversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.changeFragment(2);
            }
        });

        loadData();

        return binding.getRoot();
    }

    private void loadData(){
        AppointmentController.get(new AppointmentController.GetHandler() {
            @Override
            public void onSuccess(List<Appointment> data) {
                DashboardFragment.this.data = data;
                setUpAppointmentRecyclerView();
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private void setUpAppointmentRecyclerView(){
        binding.appointmentRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new BookedAppointmentsAdapter(getActivity(), data, new ItemClickHandler() {
            @Override
            public void onViewClick(int position) {
                Appointment appointment = data.get(position);
                Intent i = new Intent(getContext(), ConversationActivity.class);
                i.putExtra("channelId",String.valueOf(appointment.getAppointmentId()));
            }
        });

        binding.appointmentRecycler.setAdapter(adapter);
    }
}