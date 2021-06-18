package com.app.goaheadapp.fragment.cart;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.AddSuccessfullyResponse;
import com.app.goaheadapp.models.CartResponse;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartViewModel extends ViewModel {
    MutableLiveData<CartResponse> mutableLiveData = new MutableLiveData<>();
    MutableLiveData<AddSuccessfullyResponse> clearMutableLiveData = new MutableLiveData<>();

    public void getCart(Context homeFragment){
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getCart(token,currentLang).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                mutableLiveData.setValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {

            }
        });
    }

    public void clearAll(Context homeFragment){
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().clearAll(token,currentLang).enqueue(new Callback<AddSuccessfullyResponse>() {
            @Override
            public void onResponse(Call<AddSuccessfullyResponse> call, Response<AddSuccessfullyResponse> response) {
                clearMutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<AddSuccessfullyResponse> call, Throwable t) {
            }
        });
    }
}
