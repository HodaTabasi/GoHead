package com.app.goaheadapp.onboarding;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.goaheadapp.R;
import com.app.goaheadapp.SplashViewModel;
import com.app.goaheadapp.databinding.FragmentOnBoarding1Binding;

public class onBoarding1 extends Fragment {


    public onBoarding1() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static onBoarding1 newInstance() {
        onBoarding1 fragment = new onBoarding1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FragmentOnBoarding1Binding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_on_boarding1, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        binding.setMyViewModel(new SplashViewModel(getActivity()));
        return view;
    }
}