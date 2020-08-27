package com.codenite.ilaaj.utils.conversation;

import android.net.Uri;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatController {
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference reference = database.getReference("conversations");
    public interface UploadMediaHandler{
        void onSuccess();
        void onFailure();
    }

    public interface SendMessageHandler{
        void onSuccess();
        void onFailure();
    }

    public interface GetMessageHandler{
        void onSuccess();
        void onFailure();
    }
    public static void uploadMedia(String channelId, Uri uri,ChatModel chat, UploadMediaHandler handler){

    }

    public static void sendMessage(String channelId, ChatModel chat,SendMessageHandler handler){

    }

    public static void getMessage(String channelId,GetMessageHandler handler){

    }
}
