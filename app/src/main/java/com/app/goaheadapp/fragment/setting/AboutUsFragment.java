package com.app.goaheadapp.fragment.setting;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.goaheadapp.R;
import com.app.goaheadapp.databinding.FragmentAboutUsBinding;
import com.app.goaheadapp.models.AboutUs;
import com.app.goaheadapp.models.SetiingResponse;


public class AboutUsFragment extends Fragment {

    public AboutUsFragment() {
        // Required empty public constructor
    }

    FragmentAboutUsBinding binding;
    private SettingViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_about_us, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(SettingViewModel.class);
        binding.setMymodel(viewModel);
        getData();
    }

    private void getData() {
        viewModel.getAbout(getActivity());
        viewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<SetiingResponse>() {
            @Override
            public void onChanged(SetiingResponse setiingResponse) {
                AboutUs aboutUs = setiingResponse.getItems();
                binding.setItem(aboutUs);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = ((AppCompatActivity) getActivity()).findViewById(R.id.toolbar_main);
        AppCompatTextView textView = view.findViewById(R.id.page_title);
        AppCompatImageView clear = view.findViewById(R.id.clear);
        AppCompatImageView cart = view.findViewById(R.id.cart);
        AppCompatImageView back = view.findViewById(R.id.back);
        textView.setText("About Us");
        cart.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}