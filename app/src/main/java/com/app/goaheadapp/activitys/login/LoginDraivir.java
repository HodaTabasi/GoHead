package com.app.goaheadapp.activitys.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.app.goaheadapp.R;
import com.app.goaheadapp.SplashViewModel;
import com.app.goaheadapp.databinding.ActivityLoginBinding;
import com.app.goaheadapp.databinding.ActivityLoginDraivirBinding;

public class LoginDraivir extends AppCompatActivity {

    LoginViewModel viewModel;
    ActivityLoginDraivirBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         binding =
                DataBindingUtil.setContentView(this, R.layout.activity_login_draivir);
        binding.setMyViewModel(new SplashViewModel(this));

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setMymodel(viewModel);
    }

    public void Login(View view){
        viewModel.LoginUser(binding.email.getText().toString(),binding.password.getText().toString(),this);
    }
}