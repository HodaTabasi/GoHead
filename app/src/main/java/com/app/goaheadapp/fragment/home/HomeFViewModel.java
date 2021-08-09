package com.app.goaheadapp.fragment.home;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.AddsResponse;
import com.app.goaheadapp.models.CatResponse;
import com.app.goaheadapp.models.SliderResponse;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFViewModel extends ViewModel {
    MutableLiveData<CatResponse> mutableLiveData = new MutableLiveData<>();

    public void getCat(String id, Context homeFragment) {
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getCat(token, currentLang, id).enqueue(new Callback<CatResponse>() {
            @Override
            public void onResponse(Call<CatResponse> call, Response<CatResponse> response) {
                mutableLiveData.setValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<CatResponse> call, Throwable t) {
                MyProgressDialog.dismissDialog();
            }
        });
    }

}
