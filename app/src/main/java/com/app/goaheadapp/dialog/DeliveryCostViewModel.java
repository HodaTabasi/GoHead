package com.app.goaheadapp.dialog;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.DeliveryCostResponse;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryCostViewModel extends ViewModel {
    MutableLiveData<DeliveryCostResponse> mutableLiveData = new MutableLiveData<>();

    public void getDeliveryCost(Context context) {
        MyProgressDialog.showDialog(context);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getDeliveryCost(token, currentLang).enqueue(new Callback<DeliveryCostResponse>() {
            @Override
            public void onResponse(Call<DeliveryCostResponse> call, Response<DeliveryCostResponse> response) {
                mutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<DeliveryCostResponse> call, Throwable t) {

            }
        });
    }
}
