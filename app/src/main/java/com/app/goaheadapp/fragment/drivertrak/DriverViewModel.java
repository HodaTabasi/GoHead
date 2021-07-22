package com.app.goaheadapp.fragment.drivertrak;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.AddSuccessfullyResponse;
import com.app.goaheadapp.models.OrderResponse;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverViewModel extends ViewModel {

    MutableLiveData<OrderResponse> postsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<AddSuccessfullyResponse> postsMutableLiveData1 = new MutableLiveData<>();
    MutableLiveData<AddSuccessfullyResponse> postsMutableLiveData2 = new MutableLiveData<>();


    public void getOrder(String id, final Context homeFragment) {
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
//        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getDriverOrder(token, currentLang, id).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                postsMutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                MyProgressDialog.dismissDialog();
                Log.e("ffffffffffffff", t.getMessage());
            }
        });
    }

    public void sendMessage(Context homeFragment, String userId, String orderId, String message) {
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().sendMessage(token, currentLang, orderId, userId, message).enqueue(new Callback<AddSuccessfullyResponse>() {
            @Override
            public void onResponse(Call<AddSuccessfullyResponse> call, Response<AddSuccessfullyResponse> response) {
                postsMutableLiveData1.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<AddSuccessfullyResponse> call, Throwable t) {
            }
        });
    }

    public void approvOrReject(Context homeFragment, String type, String orderId) {
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().approvOrReject(token, currentLang, orderId, type).enqueue(new Callback<AddSuccessfullyResponse>() {
            @Override
            public void onResponse(Call<AddSuccessfullyResponse> call, Response<AddSuccessfullyResponse> response) {
                postsMutableLiveData2.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<AddSuccessfullyResponse> call, Throwable t) {

            }
        });
    }
}
