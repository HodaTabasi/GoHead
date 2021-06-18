package com.app.goaheadapp.fragment.setting;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.DeleteCartResponse;
import com.app.goaheadapp.models.SetiingResponse;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingViewModel extends ViewModel {
    MutableLiveData<SetiingResponse> mutableLiveData = new MutableLiveData<>();
    MutableLiveData<DeleteCartResponse> contactMutableLiveData = new MutableLiveData<>();

    public void getAbout(Context homeFragment){
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getSetting(token,currentLang).enqueue(new Callback<SetiingResponse>() {
            @Override
            public void onResponse(Call<SetiingResponse> call, Response<SetiingResponse> response) {
                mutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<SetiingResponse> call, Throwable t) {

            }
        });
    }

    public void sendContact(Context homeFragment,String text,String email){
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().sendContact(token,currentLang,text,email).enqueue(new Callback<DeleteCartResponse>() {
            @Override
            public void onResponse(Call<DeleteCartResponse> call, Response<DeleteCartResponse> response) {
                contactMutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<DeleteCartResponse> call, Throwable t) {

            }
        });
    }
}
