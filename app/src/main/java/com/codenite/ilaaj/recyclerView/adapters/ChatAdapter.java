package com.codenite.ilaaj.recyclerView.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codenite.ilaaj.databinding.RecyclerChatBinding;
import com.codenite.ilaaj.utils.DateFormatter;
import com.codenite.ilaaj.utils.conversation.ChatModel;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    RecyclerChatBinding binding;
    Context context;
    List<ChatModel> data;

    public ChatAdapter(Context context, List<ChatModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RecyclerChatBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatModel chat = data.get(position);
        holder.message.setText(chat.getMessageText());
        holder.time.setText(new DateFormatter(new Date(chat.getTime())).getDateAndTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView message,time,status;
        public ViewHolder(View v){
            super(v);
            message = binding.message;
            time =binding.time;
            status =binding.status;
        }
    }
}
