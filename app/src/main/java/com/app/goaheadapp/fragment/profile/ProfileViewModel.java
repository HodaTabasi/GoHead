package com.app.goaheadapp.fragment.profile;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.SignUpResponse;
import com.app.goaheadapp.models.UpdateImageResponse;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {
    MutableLiveData<SignUpResponse> postsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<UpdateImageResponse> updateImageMutableLiveData = new MutableLiveData<>();

    public void getProfile(final Context login) {
        MyProgressDialog.showDialog(login);

        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getProfile(token, currentLang).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                postsMutableLiveData.postValue(response.body());
                    MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

            }
        });
    }

    public void updateProfileImage(final Context login, MultipartBody.Part body) {

        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().uploadImage(token, currentLang,body).enqueue(new Callback<UpdateImageResponse>() {
            @Override
            public void onResponse(Call<UpdateImageResponse> call, Response<UpdateImageResponse> response) {
                updateImageMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<UpdateImageResponse> call, Throwable t) {
                Log.e("fffffffffffff",t.getMessage());
            }
        });
    }
}
