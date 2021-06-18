package com.app.goaheadapp.activitys.register;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.goaheadapp.R;
import com.app.goaheadapp.databinding.ActivityRegisterBinding;
import com.app.goaheadapp.models.User;

public class Register extends AppCompatActivity {

    RegisterViewModel viewModel;


    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =
                DataBindingUtil.setContentView(this, R.layout.activity_register);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        binding.setMymodel(viewModel);
        binding.setMyactivity(this);
        binding.setMyuser(new User());
    }

    public void Click(View view) {
        User user = binding.getMyuser();
        if (viewModel.validation(user, binding.password.getText().toString(), binding.rePassword.getText().toString(), this)) {
            Log.e("ffffffffffffff", user.getName());
            viewModel.registerUser(user, binding.password.getText().toString(), this);
        }
    }
}