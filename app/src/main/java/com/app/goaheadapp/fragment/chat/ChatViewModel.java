package com.app.goaheadapp.fragment.chat;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.AddSuccessfullyResponse;
import com.app.goaheadapp.models.ChatDetailsResponse;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatViewModel extends ViewModel {
    MutableLiveData<ChatDetailsResponse> postsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<AddSuccessfullyResponse> postsMutableLiveData1 = new MutableLiveData<>();

    public void getNoteDitails(Context homeFragment,String id){
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getNoteDetails(token,currentLang,id).enqueue(new Callback<ChatDetailsResponse>() {
            @Override
            public void onResponse(Call<ChatDetailsResponse> call, Response<ChatDetailsResponse> response) {
                postsMutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<ChatDetailsResponse> call, Throwable t) {

            }
        });
    }

    public void sendMessage(Context homeFragment,String userId,String orderId,String message){
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().sendMessage(token,currentLang,orderId,userId,message).enqueue(new Callback<AddSuccessfullyResponse>() {
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
}
