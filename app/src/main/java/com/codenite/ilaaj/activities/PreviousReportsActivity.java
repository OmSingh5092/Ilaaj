package com.codenite.ilaaj.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.codenite.ilaaj.api.controllers.RecordController;
import com.codenite.ilaaj.api.dataModels.Record;
import com.codenite.ilaaj.databinding.ActivityPreviousReportsBinding;
import com.codenite.ilaaj.recyclerView.adapters.ItemClickHandler;
import com.codenite.ilaaj.recyclerView.adapters.PreviousReportsAdapter;
import com.codenite.ilaaj.utils.PermissionHandler;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPreviousReportsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding = ActivityPreviousReportsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Setting up recycler view
        setUpRecyclerView();
        new PermissionHandler(this).askStoragePermissions();

        binding.addReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFile();
            }
        });

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PreviousReportsActivity.this,HomeActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }

    private void setUpRecyclerView(){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PreviousReportsAdapter(this, list, new ItemClickHandler() {
            @Override
            public void onViewClick(int position) {

            }
        });
        binding.recyclerView.setAdapter(adapter);
    }

    private void selectFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
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