package com.codenite.ilaaj.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codenite.ilaaj.api.dataModels.Record;
import com.codenite.ilaaj.databinding.FragmentManageDocsBinding;
import com.codenite.ilaaj.recyclerView.adapters.ItemClickHandler;
import com.codenite.ilaaj.recyclerView.adapters.PreviousReportsAdapter;
import com.codenite.ilaaj.utils.SharedPrefs;

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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentManageDocsBinding.inflate(LayoutInflater.from(getActivity()),container,false);
        context = getContext();
        prefs = new SharedPrefs(context);

        loadData();

        return binding.getRoot();
    }

    void loadData(){

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
}
