package com.codenite.ilaaj.utils.firebase;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.codenite.ilaaj.api.dataModels.Record;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

public class Storage {
    private static FirebaseStorage storage  = FirebaseStorage.getInstance();
    private static FirebaseAuth auth = FirebaseAuth.getInstance();

    Context context;

    public Storage(Context context) {
        this.context = context;
    }

    public interface recordUploadHandler{
        void onSuccess(Record record);
        void onFailure(Exception e);
    }
    public interface recordDownloadHandler{
        void onSuccess();
        void onFailure(Exception e);
    }

    public void uploadRecord(Uri fileUri, recordUploadHandler handler){
        Date date = new Date();
        Record record = new Record();
        String path = "records/"+auth.getUid()+"/"+date.getTime()+"/"+fileUri.getLastPathSegment();

        storage.getReference(path).putFile(fileUri)
            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    record.setLink(path);
                    handler.onSuccess(record);
                }
            }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                handler.onFailure(e);
            }
        });
    }

    public void downloadRecord(String path,recordDownloadHandler handler){
        File root = new File(Environment.getExternalStorageDirectory(),"Ilaaj");
        if(!root.exists()){
            root.mkdir();
        }
        File reportFile = new File(root,"report.jpg");

        storage.getReference(path).getFile(reportFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                openFile(reportFile);
                handler.onSuccess();
            }
        });

    }

    private void openFile(File reportFile){

        Uri contentUri = FileProvider.getUriForFile(context,"com.codenite.ilaaj.provider",reportFile);
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setDataAndType(contentUri,"application/pdf");
        i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(i);
    }
}
