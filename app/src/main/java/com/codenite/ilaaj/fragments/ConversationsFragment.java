package com.codenite.ilaaj.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codenite.ilaaj.activities.ConversationActivity;
import com.codenite.ilaaj.api.controllers.AppointmentController;
import com.codenite.ilaaj.api.dataModels.Appointment;
import com.codenite.ilaaj.databinding.FragmentConversationsBinding;
import com.codenite.ilaaj.recyclerView.adapters.BookedAppointmentsAdapter;
import com.codenite.ilaaj.recyclerView.adapters.ItemClickHandler;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ConversationsFragment extends Fragment {
    FragmentConversationsBinding binding;
    List<Appointment> data;
    BookedAppointmentsAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentConversationsBinding.inflate(getLayoutInflater(),container,false);
        loadData();
        return binding.getRoot();
    }

    private void loadData(){
        AppointmentController.get(new AppointmentController.GetHandler() {
            @Override
            public void onSuccess(List<Appointment> data) {
                ConversationsFragment.this.data = data;
                setUpAppointmentRecyclerView();
            }

            @Override
            public void onFailure(Exception e) {

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
            }
        });

        binding.contacts.setAdapter(adapter);
    }
}
