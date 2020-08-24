package com.codenite.ilaaj.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codenite.ilaaj.databinding.FragmentManageDocsBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ManageDocsFragment extends Fragment {
    FragmentManageDocsBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentManageDocsBinding.inflate(LayoutInflater.from(getActivity()),container,false);
        return binding.getRoot();
    }
}
