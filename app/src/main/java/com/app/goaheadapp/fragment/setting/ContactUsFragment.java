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
import android.widget.Toast;

import com.app.goaheadapp.R;
import com.app.goaheadapp.databinding.FragmentContactUsBinding;
import com.app.goaheadapp.models.AboutUs;
import com.app.goaheadapp.models.DeleteCartResponse;
import com.app.goaheadapp.models.SetiingResponse;

public class ContactUsFragment extends Fragment {

    FragmentContactUsBinding binding;
    private SettingViewModel viewModel;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_contact_us, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(SettingViewModel.class);
        binding.setMymodel(viewModel);
        getData();
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.sendContact(getContext(), binding.message.getText().toString(), binding.email.getText().toString());
                viewModel.contactMutableLiveData.observe(getViewLifecycleOwner(), new Observer<DeleteCartResponse>() {
                    @Override
                    public void onChanged(DeleteCartResponse deleteCartResponse) {
                        if (deleteCartResponse.isStatus())
                            Toast.makeText(getContext(), "" + deleteCartResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
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
        textView.setText("Contact Us");
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