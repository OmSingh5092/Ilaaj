package com.codenite.ilaaj.utils.firebase;

import android.net.Uri;

import com.codenite.ilaaj.api.models.Record;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.Date;

public class Storage {
    private static FirebaseStorage storage  = FirebaseStorage.getInstance();
    private static FirebaseAuth auth = FirebaseAuth.getInstance();

    public interface storageEvent{
        void onUploadRecord(Record record);
    }

    public static void uploadRecord(Uri fileUri, storageEvent storageEvent){
        Date date = new Date();
        Record record = new Record();
        String path = "records/"+auth.getUid()+"/"+date.getTime()+"/"+fileUri.getLastPathSegment();

        storage.getReference(path).putFile(fileUri)
            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    record.setLink(path);
                    storageEvent.onUploadRecord(record);
                }
            });
    }
}
