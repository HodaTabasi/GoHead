package com.app.goaheadapp.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.app.goaheadapp.R;
import com.app.goaheadapp.SplashViewModel;
import com.app.goaheadapp.databinding.ActivityResetPasswordBinding;

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityResetPasswordBinding activityResetPasswordBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
        activityResetPasswordBinding.setMyViewModel(new SplashViewModel(this));
        activityResetPasswordBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMyDialog();
            }
        });
    }

    private void showMyDialog() {
        final Dialog dialog = new Dialog(this, R.style.mydialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_message);

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}