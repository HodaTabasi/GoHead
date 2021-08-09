package com.app.goaheadapp.fragment.prouctlist;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.AddsResponse;
import com.app.goaheadapp.models.CatResponse;
import com.app.goaheadapp.models.SearchResponse;
import com.app.goaheadapp.models.SliderResponse;
import com.app.goaheadapp.models.SubCatResponse;
import com.app.goaheadapp.models.User;

import java.util.HashMap;
import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends ViewModel {

    MutableLiveData<SubCatResponse> postsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<CatResponse> mutableLiveData = new MutableLiveData<>();
    MutableLiveData<SearchResponse> searchMutableLiveData = new MutableLiveData<>();
    MutableLiveData<AddsResponse> addsMutableLiveData = new MutableLiveData<>();

    MutableLiveData<SliderResponse> slidersMutableLiveData = new MutableLiveData<>();

    public void getSliders(Context homeFragment) {
        MyProgressDialog.showDialog(homeFragment);
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getSliders(currentLang).enqueue(new Callback<SliderResponse>() {
            @Override
            public void onResponse(Call<SliderResponse> call, Response<SliderResponse> response) {
                slidersMutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<SliderResponse> call, Throwable t) {
                MyProgressDialog.showDialog(homeFragment);
            }
        });
    }

//    public void getProduct(String token) {
//        NetworkUtils1.getInstance().getProduct(token).enqueue(new Callback<GetResponse>() {
//            @Override
//            public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {
//                postsMutableLiveData.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<GetResponse> call, Throwable t) {
//
//            }
//        });
//    }
//
//    public void getProductShop(String token) {
//        NetworkUtils1.getInstance().getProduct(token).enqueue(new Callback<GetResponse>() {
//            @Override
//            public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {
//                postsMutableLiveData.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<GetResponse> call, Throwable t) {
//
//            }
//        });
//    }

    public void search(HashMap<String, String> fields, Context homeFragment) {
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getSearch(token, currentLang, fields).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                searchMutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });
    }

    public void getCat(String id, Context homeFragment) {
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getCat(token, currentLang, id).enqueue(new Callback<CatResponse>() {
            @Override
            public void onResponse(Call<CatResponse> call, Response<CatResponse> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<CatResponse> call, Throwable t) {

            }
        });
    }

    public void getSubCat(String id, Context homeFragment) {
//        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getSubCat(token, currentLang, id).enqueue(new Callback<SubCatResponse>() {
            @Override
            public void onResponse(Call<SubCatResponse> call, Response<SubCatResponse> response) {
//                MyProgressDialog.dismissDialog();
                postsMutableLiveData.postValue(response.body());

            }

            @Override
            public void onFailure(Call<SubCatResponse> call, Throwable t) {

            }
        });
    }

    public void getAdds(String id, Context homeFragment) {
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getAdds(id, currentLang).enqueue(new Callback<AddsResponse>() {
            @Override
            public void onResponse(Call<AddsResponse> call, Response<AddsResponse> response) {
                addsMutableLiveData.postValue(response.body());
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<AddsResponse> call, Throwable t) {
            }
        });
    }
}
