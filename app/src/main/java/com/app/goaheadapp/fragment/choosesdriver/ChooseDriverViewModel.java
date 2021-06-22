package com.app.goaheadapp.fragment.choosesdriver;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.AddSuccessfullyResponse;
import com.app.goaheadapp.models.DrivierResponse;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseDriverViewModel extends ViewModel {
    MutableLiveData<DrivierResponse> postsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<AddSuccessfullyResponse> postsMutableLiveData1 = new MutableLiveData<>();

    public void getDrivers(Context homeFragment){
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getDrivers(token,currentLang).enqueue(new Callback<DrivierResponse>() {
            @Override
            public void onResponse(Call<DrivierResponse> call, Response<DrivierResponse> response) {
                postsMutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<DrivierResponse> call, Throwable t) {
                MyProgressDialog.dismissDialog();
                Log.e("ffffffffffffff",t.getMessage());
            }
        });
    }

    public void sendOrderToDriver(Context homeFragment,String orderId,String driverId){
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().sendOrderForDriver(token,currentLang,orderId,driverId).enqueue(new Callback<AddSuccessfullyResponse>() {
            @Override
            public void onResponse(Call<AddSuccessfullyResponse> call, Response<AddSuccessfullyResponse> response) {
                postsMutableLiveData1.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<AddSuccessfullyResponse> call, Throwable t) {
                Toast.makeText(homeFragment, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                MyProgressDialog.dismissDialog();
            }
        });
    }
}
