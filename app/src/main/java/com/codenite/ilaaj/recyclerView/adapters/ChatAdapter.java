package com.codenite.ilaaj.recyclerView.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codenite.ilaaj.R;
import com.codenite.ilaaj.databinding.RecyclerChatBinding;
import com.codenite.ilaaj.utils.DateFormatter;
import com.codenite.ilaaj.utils.conversation.ChatModel;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
        if(chat.isDoctor()){
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            holder.message.setTextColor(Color.WHITE);
            holder.time.setTextColor(Color.WHITE);
            holder.layout.setGravity(Gravity.RIGHT);
        }else{

        }
        holder.message.setText(chat.getMessageText());
        holder.time.setText(new DateFormatter(new Date(chat.getTime())).getDateAndTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView message,time,status;
        CardView cardView;
        LinearLayout layout;
        public ViewHolder(View v){
            super(v);
            cardView = binding.card;
            layout = binding.getRoot();
            message = binding.message;
            time =binding.time;
            status =binding.status;
        }
    }
}
