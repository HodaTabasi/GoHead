package com.app.goaheadapp.fragment.privacy;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.goaheadapp.BaseActivity;
import com.app.goaheadapp.R;
import com.app.goaheadapp.adapters.PrivacyAdapter;
import com.app.goaheadapp.databinding.FragmentPrivacyBinding;
import com.app.goaheadapp.models.PrivacyResponse;


public class PrivacyFragment extends Fragment {


    FragmentPrivacyBinding binding;
    private PrivacyViewModel viewModel;

    public PrivacyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_privacy, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(PrivacyViewModel.class);
        binding.setMymodel(viewModel);
        binding.resc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        loadData();
        binding.reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }


    private void loadData() {
        if (BaseActivity.isConnected(getContext())) {
            binding.content.setVisibility(View.VISIBLE);
            binding.reload.setVisibility(View.GONE);
            getData();
        } else {
            binding.content.setVisibility(View.GONE);
            binding.reload.setVisibility(View.VISIBLE);
        }
    }

    private void getData() {
        viewModel.getPrivacy(getContext());
        viewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<PrivacyResponse>() {
            @Override
            public void onChanged(PrivacyResponse privacyResponse) {
                Log.e("ffffffffffffffffffff", "ggggggggg");
                if (privacyResponse.isStatus()) {
                    PrivacyAdapter adapter = new PrivacyAdapter(getContext(), privacyResponse.getItems());
                    binding.resc.setAdapter(adapter);
                }
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
        textView.setText(R.string.pp);
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