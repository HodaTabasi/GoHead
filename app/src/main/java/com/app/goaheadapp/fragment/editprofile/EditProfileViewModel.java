package com.app.goaheadapp.fragment.editprofile;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.SignUpResponse;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileViewModel extends ViewModel {
    MutableLiveData<SignUpResponse> mutableLiveData = new MutableLiveData<>();

    public void update(User worker,String password, Context context){
        MyProgressDialog.showDialog(context);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();
        NetworkUtils.getInstance().editProfile(worker,token,currentLang,password).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                mutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                MyProgressDialog.dismissDialog();
            }
        });

    }
//    public void getSingleProduct(String token,String id){
//        NetworkUtils.getInstance().getSingleProduct(id,token).enqueue(new Callback<UpdateResponse>() {
//            @Override
//            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
//                mutableLiveData.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<UpdateResponse> call, Throwable t) {
//
//            }
//        });
//    }
}
