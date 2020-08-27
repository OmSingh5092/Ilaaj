package com.codenite.ilaaj.recyclerView.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codenite.ilaaj.api.dataModels.Appointment;
import com.codenite.ilaaj.databinding.RecyclerAppointmentsBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.ViewHolder> {
    RecyclerAppointmentsBinding binding;
    Context context;
    List<Appointment> data = new ArrayList<>();

    public interface ItemClickHandler{
        void onViewClick(int position);
    }
    ItemClickHandler handler;

    public AppointmentsAdapter(Context context, List<Appointment> data,ItemClickHandler handler) {
        this.context = context;
        this.data = data;
        this.handler = handler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RecyclerAppointmentsBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment appointment = data.get(position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView doctorName,doctorType,rating,charge;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = binding.doctorName;
            doctorType = binding.doctorCategory;
            rating = binding.rating;
            charge = binding.charge;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handler.onViewClick(getAdapterPosition());
                }
            });
        }
    }
}
