package com.app.goaheadapp.fragment.deliverydetails;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.User;
import com.app.goaheadapp.models.getPaymentRresponse;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsViewModel extends ViewModel {
    MutableLiveData<getPaymentRresponse> mutableLiveData = new MutableLiveData<>();

    public void getPayment(Context homeFragment){
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getPaymentMethod(token,currentLang).enqueue(new Callback<getPaymentRresponse>() {
            @Override
            public void onResponse(Call<getPaymentRresponse> call, Response<getPaymentRresponse> response) {
                mutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<getPaymentRresponse> call, Throwable t) {
                MyProgressDialog.dismissDialog();
            }
        });
    }
}
