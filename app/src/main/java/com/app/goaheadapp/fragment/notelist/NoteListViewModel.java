package com.app.goaheadapp.fragment.notelist;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.NoteListResponse;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteListViewModel extends ViewModel {
    MutableLiveData<NoteListResponse> postsMutableLiveData = new MutableLiveData<>();

    public void getNoteList(Context homeFragment){
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getNoteList(token,currentLang).enqueue(new Callback<NoteListResponse>() {
            @Override
            public void onResponse(Call<NoteListResponse> call, Response<NoteListResponse> response) {
                postsMutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<NoteListResponse> call, Throwable t) {

            }
        });
    }


}
