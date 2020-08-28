package com.codenite.ilaaj.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codenite.ilaaj.activities.PatientDetailsActivity;
import com.codenite.ilaaj.api.controllers.AppointmentController;
import com.codenite.ilaaj.api.dataModels.Appointment;
import com.codenite.ilaaj.databinding.FragmentDoctorDashboardBinding;
import com.codenite.ilaaj.recyclerView.adapters.ItemClickHandler;
import com.codenite.ilaaj.recyclerView.adapters.PatientAppointmentAdapter;
import com.codenite.ilaaj.utils.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class DashboardDoctorFragment extends Fragment {
    FragmentDoctorDashboardBinding binding;
    PatientAppointmentAdapter adapter;
    List<Appointment> data = new ArrayList<>();
    Context context;
    SharedPrefs prefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDoctorDashboardBinding.inflate(getLayoutInflater(),container,false);
        context = getContext();
        prefs = new SharedPrefs(context);
        loadData();
        return binding.getRoot();
    }

    private void loadData(){
        new AppointmentController(context).getByUser(prefs.getUserId(), new AppointmentController.getByUserHandler() {
            @Override
            public void onSuccess(List<Appointment> appointments) {
                DashboardDoctorFragment.this.data = data;
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
        binding.appointmentRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter  = new PatientAppointmentAdapter(context, data, new ItemClickHandler() {
            @Override
            public void onViewClick(int position) {
                Intent i = new Intent(context, PatientDetailsActivity.class);
                i.putExtra("appointment",data.get(position));
            }
        });

        binding.appointmentRecycler.setAdapter(adapter);
    }
}
