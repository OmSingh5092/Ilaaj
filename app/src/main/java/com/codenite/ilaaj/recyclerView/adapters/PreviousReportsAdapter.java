package com.codenite.ilaaj.recyclerView.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codenite.ilaaj.api.models.Record;
import com.codenite.ilaaj.databinding.RecyclerDocumentsBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PreviousReportsAdapter extends RecyclerView.Adapter<PreviousReportsAdapter.ViewHolder> {
    RecyclerDocumentsBinding binding;
    Context context;
    List<Record> data;

    public PreviousReportsAdapter(Context context, List<Record> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RecyclerDocumentsBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Record item = data.get(position);

        holder.documentName.setText(item.getFileName());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDocument(position);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView remove;
        TextView documentName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            remove = binding.remove;
            documentName = binding.documentName;
        }
    }

    private void deleteDocument(int position){
        //Make the query to database to delete the record
        data.remove(position);
        this.notifyItemRemoved(position);
    }
}
