package com.codenite.ilaaj.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codenite.ilaaj.databinding.RecyclerBookedAppointmentBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookedAppointmentsRecycler extends RecyclerView.Adapter<BookedAppointmentsRecycler.ViewHolder> {
    RecyclerBookedAppointmentBinding binding;
    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RecyclerBookedAppointmentBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
