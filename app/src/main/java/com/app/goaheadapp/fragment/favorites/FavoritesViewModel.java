package com.app.goaheadapp.fragment.favorites;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.FavoriteResponse;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesViewModel extends ViewModel {

    MutableLiveData<FavoriteResponse> mutableLiveData = new MutableLiveData<>();

    public void getFav(Context homeFragment) {
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getFav(token, currentLang).enqueue(new Callback<FavoriteResponse>() {
            @Override
            public void onResponse(Call<FavoriteResponse> call, Response<FavoriteResponse> response) {
                mutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<FavoriteResponse> call, Throwable t) {

            }
        });
    }
}
