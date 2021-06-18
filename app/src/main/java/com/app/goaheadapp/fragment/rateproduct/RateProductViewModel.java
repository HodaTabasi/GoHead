package com.app.goaheadapp.fragment.rateproduct;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.DeleteCartResponse;
import com.app.goaheadapp.models.SubCatResponse;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RateProductViewModel extends ViewModel {
    MutableLiveData<SubCatResponse> postsMutableLiveData = new MutableLiveData<>();


    public void Rate(String id, String value, String text, final Context homeFragment) {
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().rateProduct(token, currentLang, id, value, text).enqueue(new Callback<DeleteCartResponse>() {
            @Override
            public void onResponse(Call<DeleteCartResponse> call, Response<DeleteCartResponse> response) {
                Toast.makeText(homeFragment, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<DeleteCartResponse> call, Throwable t) {

            }
        });
    }
}
