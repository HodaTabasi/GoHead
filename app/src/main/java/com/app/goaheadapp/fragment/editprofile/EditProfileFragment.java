package com.app.goaheadapp.fragment.editprofile;

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

import com.app.goaheadapp.BaseActivity;
import com.app.goaheadapp.R;
import com.app.goaheadapp.databinding.FragmentEditProfileBinding;
import com.app.goaheadapp.models.SignUpResponse;
import com.app.goaheadapp.models.User;

import io.paperdb.Paper;


public class EditProfileFragment extends Fragment {


    FragmentEditProfileBinding binding;
    View view;
    private EditProfileViewModel viewModel;
    User worker;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        worker = new User();

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_edit_profile, container, false);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(EditProfileViewModel.class);
        binding.setMymodel(viewModel);
        User user = Paper.book().read("data");
        binding.setMyworker(user);

//        viewModel.getSingleProduct("17",(String) Paper.book().read("token"));

//        viewModel.mutableLiveData.observe(getActivity(), new Observer<UpdateResponse>() {
//            @Override
//            public void onChanged(UpdateResponse updateResponse) {
//                worker = updateResponse.getWorker();
//                binding.setMyworker(worker);
//            }
//        });

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

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
            updateData();
        } else {
            binding.content.setVisibility(View.GONE);
            binding.reload.setVisibility(View.VISIBLE);
        }
    }

    private void updateData() {
        viewModel.update(binding.getMyworker(), binding.password.getText().toString(), getContext());
        viewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<SignUpResponse>() {
            @Override
            public void onChanged(SignUpResponse signUpResponse) {
                if (signUpResponse.isStatus()) {
                    Paper.book().write("data", signUpResponse.getItems());
                    Toast.makeText(getContext(), "" + signUpResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "" + signUpResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
        textView.setText(R.string.edit_profile);
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