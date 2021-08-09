package com.app.goaheadapp.fragment.addpaymentmethod;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.AddSuccessfullyResponse;
import com.app.goaheadapp.models.MyPaymentResponse;
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
    MutableLiveData<MyPaymentResponse> myPaymentMutableLiveData = new MutableLiveData<>();
    MutableLiveData<AddSuccessfullyResponse> deletePaymentMutableLiveData = new MutableLiveData<>();

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

    public void getMyPayment(Context homeFragment) {
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getMyPayments(token, currentLang).enqueue(new Callback<MyPaymentResponse>() {
            @Override
            public void onResponse(Call<MyPaymentResponse> call, Response<MyPaymentResponse> response) {
                myPaymentMutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<MyPaymentResponse> call, Throwable t) {
                Log.e("fffffffffffff", t.getMessage());
                MyProgressDialog.dismissDialog();
            }
        });
    }

    public void deletePayment(Context homeFragment, String id) {
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().deletePayment(id, token, currentLang).enqueue(new Callback<AddSuccessfullyResponse>() {
            @Override
            public void onResponse(Call<AddSuccessfullyResponse> call, Response<AddSuccessfullyResponse> response) {
                deletePaymentMutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<AddSuccessfullyResponse> call, Throwable t) {
                Log.e("fffffffffffff", t.getMessage());
                MyProgressDialog.dismissDialog();
            }
        });
    }

}
