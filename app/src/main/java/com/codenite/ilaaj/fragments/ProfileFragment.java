package com.codenite.ilaaj.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codenite.ilaaj.databinding.FragmentProfileBinding;

import androidx.fragment.app.Fragment;
public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(LayoutInflater.from(getActivity()),container,false);

        return binding.getRoot();
    }
}