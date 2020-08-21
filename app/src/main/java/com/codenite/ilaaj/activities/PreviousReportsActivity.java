package com.codenite.ilaaj.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.codenite.ilaaj.api.controllers.RecordController;
import com.codenite.ilaaj.api.models.Record;
import com.codenite.ilaaj.databinding.ActivityPreviousReportsBinding;
import com.codenite.ilaaj.recyclerView.adapters.PreviousReportsAdapter;
import com.codenite.ilaaj.utils.firebase.Storage;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

public class PreviousReportsActivity extends AppCompatActivity {
    ActivityPreviousReportsBinding binding;
    PreviousReportsAdapter adapter;
    List<Record> list = new ArrayList<>();

    //Activity Request codes
    int SELECT_FILE=100;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        ActivityPreviousReportsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Setting up recycler view
        setUpRecyclerView();

        binding.addReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFile();
            }
        });
    }

    private void setUpRecyclerView(){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PreviousReportsAdapter(this,list);
        binding.recyclerView.setAdapter(adapter);
    }

    private void selectFile(){
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(i,SELECT_FILE);
    }

    private void updateRecyclerView(){
        adapter.notifyDataSetChanged();
    }

    private void uploadFile(Uri uri){
        //upload the file and get the path

        Storage.uploadRecord(uri, new Storage.recordUploadHandler() {
            @Override
            public void onSuccess(Record record) {
                uploadRecord(record);
            }
            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private void uploadRecord(Record record){
        RecordController.create(record, new RecordController.recordDatabaseHandler() {
            @Override
            public void onSuccess(Record record) {
                list.add(record);
                updateRecyclerView();
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_FILE){
            uploadFile(data.getData());
        }
    }


}
