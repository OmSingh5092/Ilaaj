package com.codenite.ilaaj.recyclerView.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codenite.ilaaj.api.dataModels.User;
import com.codenite.ilaaj.databinding.RecyclerDoctorsBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.ViewHolder> {
    RecyclerDoctorsBinding binding;
    Context context;
    List<User> data = new ArrayList<>();
    ItemClickHandler handler;

    public DoctorsAdapter(Context context, List<User> data, ItemClickHandler handler) {
        this.context = context;
        this.data = data;
        this.handler = handler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RecyclerDoctorsBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = data.get(position);
        holder.doctorName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
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
