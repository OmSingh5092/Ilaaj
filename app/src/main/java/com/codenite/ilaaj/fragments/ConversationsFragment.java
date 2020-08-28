package com.codenite.ilaaj.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codenite.ilaaj.activities.ConversationActivity;
import com.codenite.ilaaj.api.controllers.AppointmentController;
import com.codenite.ilaaj.api.dataModels.Appointment;
import com.codenite.ilaaj.databinding.FragmentConversationsBinding;
import com.codenite.ilaaj.recyclerView.adapters.BookedAppointmentsAdapter;
import com.codenite.ilaaj.recyclerView.adapters.ItemClickHandler;
import com.codenite.ilaaj.utils.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ConversationsFragment extends Fragment {
    FragmentConversationsBinding binding;
    List<Appointment> data = new ArrayList<>();
    BookedAppointmentsAdapter adapter;
    Context context;
    SharedPrefs prefs;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentConversationsBinding.inflate(getLayoutInflater(),container,false);
        context = getContext();
        prefs = new SharedPrefs(context);
        loadData();
        return binding.getRoot();
    }

    private void loadData(){
        new AppointmentController(context).getByUser(prefs.getUserId(), new AppointmentController.getByUserHandler() {
            @Override
            public void onSuccess(List<Appointment> appointments) {
                ConversationsFragment.this.data = appointments;
                setUpAppointmentRecyclerView();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    private void setUpAppointmentRecyclerView(){
        binding.contacts.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new BookedAppointmentsAdapter(getActivity(), data, new ItemClickHandler() {
            @Override
            public void onViewClick(int position) {
                Appointment appointment = data.get(position);
                Intent i = new Intent(getContext(), ConversationActivity.class);
                i.putExtra("channelId",String.valueOf(appointment.getAppointmentId()));
                startActivity(i);
            }
        });

        binding.contacts.setAdapter(adapter);
    }
}
