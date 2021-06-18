package com.app.goaheadapp.fragment.notes;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.goaheadapp.R;
import com.app.goaheadapp.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    FragmentMainBinding binding;
    View view;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);
        view = binding.getRoot();
        return view;
    }

}