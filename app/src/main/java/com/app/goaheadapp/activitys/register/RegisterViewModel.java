package com.app.goaheadapp.activitys.register;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.activitys.MainOneActivity;
import com.app.goaheadapp.models.SignUpResponse;
import com.app.goaheadapp.models.User;
import com.app.goaheadapp.NetworkUtils;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    MutableLiveData<SignUpResponse> postsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> posts = new MutableLiveData<>();

    public boolean validation(User user, String password, String password1, final Register register) {

        if (TextUtils.isEmpty(user.getEmail())
                || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(password1)
                || TextUtils.isEmpty(user.getMobile())
                || TextUtils.isEmpty(user.getName())) {

            Toast.makeText(register, "ارجو تعبئة الحقول المطلوبة", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.length() <= 6) {
            Toast.makeText(register, "يجب ان يكون الباس ورد اكثر من 6 ارقام", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!isEmailValid(user.getEmail())) {
            Toast.makeText(register, "التاكد من صحة الايميل", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.equals(password1)) {
            Toast.makeText(register, "مش متساويات كلمات المرور", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void registerUser(User user, String password, final Register register) {
        MyProgressDialog.showDialog(register);
        NetworkUtils.getInstance().Register(user, password).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                postsMutableLiveData.setValue(response.body());
                SignUpResponse registerResponse = postsMutableLiveData.getValue();

                if (registerResponse.isStatus()) {
                    Paper.book().write("data", registerResponse.getData());
                    FirebaseMessaging.getInstance().subscribeToTopic("user_"+registerResponse.getData().getId());
                    MyProgressDialog.dismissDialog();
                    register.startActivity(new Intent(register, MainOneActivity.class));
                    register.finish();
                } else {
                    Toast.makeText(register, "" + registerResponse.getMessage(), Toast.LENGTH_LONG).show();
                    MyProgressDialog.dismissDialog();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

            }
        });
    }
}

