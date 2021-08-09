package com.app.goaheadapp.fragment.profile;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.app.goaheadapp.BaseActivity;
import com.app.goaheadapp.HomeViewModel;
import com.app.goaheadapp.R;
import com.app.goaheadapp.databinding.FragmentProfileBinding;
import com.app.goaheadapp.models.SignUpResponse;

import java.util.Locale;

public class ProfileFragment extends Fragment {


    FragmentProfileBinding binding;
    ProfileViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_profile, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setTransfer(new HomeViewModel(getContext()));

        viewModel = new ViewModelProvider(getActivity()).get(ProfileViewModel.class);
        binding.setMymodel(viewModel);
        binding.langSwitch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    Toast.makeText(getContext(), "ar", Toast.LENGTH_SHORT).show();
                    Locale locale = new Locale("ar");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
                    restartActivity();
                } else if (i == 2) {
                    Toast.makeText(getContext(), "en", Toast.LENGTH_SHORT).show();
                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
                    restartActivity();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
        viewModel.getProfile(getActivity());
        viewModel.postsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<SignUpResponse>() {
            @Override
            public void onChanged(SignUpResponse signUpResponse) {
                binding.setItem(signUpResponse.getItems());
            }
        });
    }


    private void restartActivity() {
        Intent intent = getActivity().getIntent();
        getActivity().finish();
        startActivity(intent);
    }

//    public static void setLocale(Activitycontext) {
//        Locale locale;
//        Sessions session = new Sessions(context);
//        //Log.e("Lan",session.getLanguage());
//        locale = new Locale(langCode);
//        Configuration config = new Configuration(context.getResources().getConfiguration());
//        Locale.setDefault(locale);
//        config.setLocale(locale);
//
//        context.getBaseContext().getResources().updateConfiguration(config,
//                context.getBaseContext().getResources().getDisplayMetrics());
//    }

    @Override
    public void onResume() {
        super.onResume();
        View view = ((AppCompatActivity) getActivity()).findViewById(R.id.toolbar_main);
        AppCompatTextView textView = view.findViewById(R.id.page_title);
        AppCompatImageView clear = view.findViewById(R.id.clear);
        AppCompatImageView cart = view.findViewById(R.id.cart);
        AppCompatImageView back = view.findViewById(R.id.back);
        textView.setText(R.string.uprofile);
        cart.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);
    }
}