package com.codenite.ilaaj.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codenite.ilaaj.activities.HomeActivity;
import com.codenite.ilaaj.api.controllers.AppointmentController;
import com.codenite.ilaaj.api.dataModels.Appointment;
import com.codenite.ilaaj.databinding.FragmentDashboardBinding;
import com.codenite.ilaaj.recyclerView.adapters.BookedAppointmentsAdapter;
import com.codenite.ilaaj.recyclerView.adapters.ItemClickHandler;
import com.codenite.ilaaj.utils.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class DashboardFragment extends Fragment {
    FragmentDashboardBinding binding;
    HomeActivity activity;
    BookedAppointmentsAdapter adapter;
    List<Appointment> data = new ArrayList<>();
    Context context;
    SharedPrefs prefs;
    public DashboardFragment(HomeActivity activity){
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(LayoutInflater.from(getActivity()),container,false);
        context = getContext();
        prefs = new SharedPrefs(context);
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
        new AppointmentController(context).getByUser(prefs.getUserId(), new AppointmentController.getByUserHandler() {
            @Override
            public void onSuccess(List<Appointment> appointments) {
                DashboardFragment.this.data = appointments;
                Log.i("Data",data.toString());
                setUpAppointmentRecyclerView();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });

    }

    private void openMeetingUrl(String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void setUpAppointmentRecyclerView(){
        if(data.size() != 0){
            binding.noAppointment.setVisibility(View.GONE);
            binding.appointmentRecycler.setVisibility(View.VISIBLE);
        }
        binding.appointmentRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new BookedAppointmentsAdapter(getActivity(), data, new ItemClickHandler() {
            @Override
            public void onViewClick(int position) {
                openMeetingUrl(data.get(position).getMeetLink());
            }
        });

        binding.appointmentRecycler.setAdapter(adapter);
    }
}