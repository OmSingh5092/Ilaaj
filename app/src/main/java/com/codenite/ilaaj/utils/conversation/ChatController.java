package com.codenite.ilaaj.utils.conversation;

import android.net.Uri;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ChatController {
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference reference = database.getReference("conversations");
    public interface UploadMediaHandler{
        void onSuccess();
        void onFailure();
    }

    public interface SendMessageHandler{
        void onSuccess(ChatModel chat);
        void onFailure(Exception e);
    }

    public interface GetMessageHandler{
        void onSuccess(List<ChatModel> chats);
        void onFailure(Exception e);
    }

    public interface MessageHandler{
        void onMessageAdded(ChatModel chat);
    }


    public static void uploadMedia(String channelId, Uri uri,ChatModel chat, UploadMediaHandler handler){

    }

    public static void sendMessage(String channelId, ChatModel chat,SendMessageHandler handler){
        reference.child(channelId).push().setValue(chat);
    }

    public static void getMessage(String channelId, GetMessageHandler handler){
        List<ChatModel> list = new ArrayList<>();

    }


    public static void setMessageListener(String channelId, MessageHandler handler){
        reference.child(channelId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatModel chat = dataSnapshot.getValue(ChatModel.class);
                handler.onMessageAdded(chat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
