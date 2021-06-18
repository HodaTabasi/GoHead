package com.app.goaheadapp.fragment.addpaymentmethod;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.PaymentMethodResponse;
import com.app.goaheadapp.models.User;

import java.util.HashMap;
import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentViewModel extends ViewModel {
    MutableLiveData<PaymentMethodResponse> mutableLiveData = new MutableLiveData<>();

    public void addPayment(HashMap<String, String> fields, Context homeFragment) {
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().addPaymentMethod(token, currentLang, fields).enqueue(new Callback<PaymentMethodResponse>() {
            @Override
            public void onResponse(Call<PaymentMethodResponse> call, Response<PaymentMethodResponse> response) {
                mutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<PaymentMethodResponse> call, Throwable t) {
            }
        });
    }

}
