package com.app.goaheadapp.activitys.login;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.activitys.MainDriverActivity;
import com.app.goaheadapp.activitys.MainOneActivity;
import com.app.goaheadapp.models.SignUpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    MutableLiveData<SignUpResponse> postsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> posts = new MutableLiveData<>();


    public void LoginUser(String userName, String password, final Login login) {
        MyProgressDialog.showDialog(login);
        NetworkUtils.getInstance().Login(userName, password).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                postsMutableLiveData.setValue(response.body());
                SignUpResponse registerResponse = postsMutableLiveData.getValue();

                if (registerResponse.isStatus()) {
                    Paper.book().write("data", registerResponse.getData());
                    MyProgressDialog.dismissDialog();

                    FirebaseMessaging.getInstance().subscribeToTopic("user_"+registerResponse.getData().getId());

//                    FirebaseMessaging.getInstance().subscribeToTopic("weather")
                    login.startActivity(new Intent(login, MainOneActivity.class));
                    login.finish();

                } else {
                    Toast.makeText(login, "" + registerResponse.getMessage(), Toast.LENGTH_LONG).show();
                    MyProgressDialog.dismissDialog();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

            }
        });
    }

    public void LoginUser(String userName, String password, final LoginDraivir login) {
        MyProgressDialog.showDialog(login);
        NetworkUtils.getInstance().Login(userName, password).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                postsMutableLiveData.setValue(response.body());
                SignUpResponse registerResponse = postsMutableLiveData.getValue();
                if (registerResponse.isStatus()) {
                    Paper.book().write("data", registerResponse.getData());
                    FirebaseMessaging.getInstance().subscribeToTopic("user_"+registerResponse.getData().getId());
                    MyProgressDialog.dismissDialog();
                    login.startActivity(new Intent(login, MainDriverActivity.class));
                    login.finish();
                } else {
                    Toast.makeText(login, "" + registerResponse.getMessage(), Toast.LENGTH_LONG).show();
                    MyProgressDialog.dismissDialog();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
            }
        });
    }
}
