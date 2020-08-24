package com.codenite.ilaaj.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codenite.ilaaj.databinding.FragmentConversationsBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ConversationsFragment extends Fragment {
    FragmentConversationsBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentConversationsBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }
}
