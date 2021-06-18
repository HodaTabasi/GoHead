package com.app.goaheadapp.fragment.ratedriver;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.RateResponse;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RateViewModel extends ViewModel {
    MutableLiveData<RateResponse> postsMutableLiveData = new MutableLiveData<>();

    public void getRate(Context homeFragment) {
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getRateDriver(token, currentLang).enqueue(new Callback<RateResponse>() {
            @Override
            public void onResponse(Call<RateResponse> call, Response<RateResponse> response) {
                postsMutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<RateResponse> call, Throwable t) {
                MyProgressDialog.dismissDialog();
            }
        });
    }
}
