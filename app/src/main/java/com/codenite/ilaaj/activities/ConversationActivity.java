package com.codenite.ilaaj.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codenite.ilaaj.databinding.ActivityConversationBinding;
import com.codenite.ilaaj.recyclerView.adapters.ChatAdapter;
import com.codenite.ilaaj.utils.SharedPrefs;
import com.codenite.ilaaj.utils.conversation.ChatController;
import com.codenite.ilaaj.utils.conversation.ChatModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ConversationActivity extends AppCompatActivity {
    ActivityConversationBinding binding;
    List<ChatModel> chatData =new LinkedList<>();
    ChatAdapter adapter;
    String channelId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConversationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        channelId = getIntent().getStringExtra("channelId");
        loadData();


        
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }
    
    private void sendMessage(){
        if(!validate()){
            return;
        }
        ChatModel model = new ChatModel();
        model.setMessageText(binding.message.getText().toString());
        model.setDoctor(new SharedPrefs(this).isDoctor());
        model.setTime(System.currentTimeMillis());

        ChatController.sendMessage(channelId,model, new ChatController.SendMessageHandler() {
            @Override
            public void onSuccess(ChatModel model) {
                chatData.add(model);
                Collections.sort(chatData,comparator);
                updateRecyclerView();
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(ConversationActivity.this, "Error sending message", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Comparator<ChatModel> comparator = new Comparator<ChatModel>() {
        @Override
        public int compare(ChatModel chatModel, ChatModel t1) {
            if(chatModel.getTime()> t1.getTime()){
                return -1;
            }else{
                return 1;
            }
        }
    };
    
    private boolean validate(){
        if(binding.message.getText().toString().length() == 0){
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    
    private void loadData(){
        setUpRecyclerView();
        ChatController.setMessageListener(channelId, new ChatController.MessageHandler() {
            @Override
            public void onMessageAdded(ChatModel chat) {
                chatData.add(chat);
                updateRecyclerView();
            }
        });
    }
    
    private void setUpRecyclerView(){
        adapter = new ChatAdapter(this,chatData);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.chatRecycler.setLayoutManager(manager);
        binding.chatRecycler.setAdapter(adapter);

    }
    
    private void updateRecyclerView(){
        adapter.notifyDataSetChanged();
        binding.chatRecycler.smoothScrollToPosition(chatData.size()-1);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}