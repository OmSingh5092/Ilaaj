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

public class BookedAppointmentsAdapter extends RecyclerView.Adapter<BookedAppointmentsAdapter.ViewHolder> {
    RecyclerBookedAppointmentBinding binding;
    Context context;
    List<Appointment> data = new ArrayList<>();

    public BookedAppointmentsAdapter(Context context, List<Appointment> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RecyclerBookedAppointmentBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment appointment = data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView doctor, date, time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctor = binding.doctorName;
            date = binding.date;
            time = binding.time;
        }
    }
}
