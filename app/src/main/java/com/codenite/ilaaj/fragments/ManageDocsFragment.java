package com.codenite.ilaaj.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codenite.ilaaj.api.controllers.RecordController;
import com.codenite.ilaaj.api.dataModels.Record;
import com.codenite.ilaaj.databinding.FragmentManageDocsBinding;
import com.codenite.ilaaj.recyclerView.adapters.ItemClickHandler;
import com.codenite.ilaaj.recyclerView.adapters.PreviousReportsAdapter;
import com.codenite.ilaaj.utils.SharedPrefs;
import com.codenite.ilaaj.utils.firebase.Storage;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ManageDocsFragment extends Fragment {
    FragmentManageDocsBinding binding;
    Context context;
    List<Record> data = new ArrayList<>();
    SharedPrefs prefs;
    PreviousReportsAdapter adapter;
    int SELECT_FILE = 100;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentManageDocsBinding.inflate(LayoutInflater.from(getActivity()),container,false);
        context = getContext();
        prefs = new SharedPrefs(context);

        loadData();
        binding.addReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFile();
            }
        });

        return binding.getRoot();
    }

    void loadData(){

    }

    private void uploadFile(Uri uri){
        //upload the file and get the path

        new Storage(context).uploadRecord(uri, new Storage.recordUploadHandler() {
            @Override
            public void onSuccess(Record record) {
                uploadRecord(record);
            }
            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private void selectFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
    }

    private void uploadRecord(Record record){
        RecordController.create(record, new RecordController.recordDatabaseHandler() {
            @Override
            public void onSuccess(Record record) {
                data.add(record);
                updateRecyclerView();
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    void updateRecyclerView(){
        adapter.notifyDataSetChanged();
    }

    void setUpRecyclerView(){
        adapter = new PreviousReportsAdapter(context, data, new ItemClickHandler() {
            @Override
            public void onViewClick(int position) {

            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_FILE){
            uploadFile(data.getData());
        }
    }
}
