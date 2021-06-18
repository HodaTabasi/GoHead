package com.app.goaheadapp.fragment.privacy;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.PrivacyResponse;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivacyViewModel extends ViewModel {
    MutableLiveData<PrivacyResponse> mutableLiveData = new MutableLiveData<>();

    public void getPrivacy(Context homeFragment) {
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().privacy(token, currentLang).enqueue(new Callback<PrivacyResponse>() {
            @Override
            public void onResponse(Call<PrivacyResponse> call, Response<PrivacyResponse> response) {
                mutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<PrivacyResponse> call, Throwable t) {

            }
        });
    }
}
