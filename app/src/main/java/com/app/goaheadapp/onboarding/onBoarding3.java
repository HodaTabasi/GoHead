package com.app.goaheadapp.onboarding;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.goaheadapp.R;
import com.app.goaheadapp.SplashViewModel;
import com.app.goaheadapp.databinding.FragmentOnBoarding2Binding;
import com.app.goaheadapp.databinding.FragmentOnBoarding3Binding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link onBoarding3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class onBoarding3 extends Fragment {


    public onBoarding3() {
        // Required empty public constructor
    }


    public static onBoarding3 newInstance() {
        onBoarding3 fragment = new onBoarding3();
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
        FragmentOnBoarding3Binding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_on_boarding3, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        binding.setMyViewModel(new SplashViewModel(getActivity()));
        return view;
    }
}