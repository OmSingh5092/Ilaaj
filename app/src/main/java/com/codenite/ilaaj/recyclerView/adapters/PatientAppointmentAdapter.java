package com.codenite.ilaaj.recyclerView.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codenite.ilaaj.api.dataModels.Appointment;
import com.codenite.ilaaj.databinding.RecyclerBookedAppointmentBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PatientAppointmentAdapter extends RecyclerView.Adapter<PatientAppointmentAdapter.ViewHolder> {
    RecyclerBookedAppointmentBinding binding;
    Context context;
    List<Appointment> data = new ArrayList<>();
    ItemClickHandler handler;

    public PatientAppointmentAdapter(Context context, List<Appointment> data, ItemClickHandler handler) {
        this.context = context;
        this.data = data;
        this.handler = handler;
    }

    @NonNull
    @Override
    public PatientAppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RecyclerBookedAppointmentBinding.inflate(LayoutInflater.from(context),parent,false);
        return new PatientAppointmentAdapter.ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull PatientAppointmentAdapter.ViewHolder holder, int position) {
        Appointment appointment = data.get(position);
        

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView patient, date, time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patient= binding.doctorName;
            date = binding.date;
            time = binding.time;
        }
    }
}
