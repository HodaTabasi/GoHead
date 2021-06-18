package com.app.goaheadapp.fragment.shopcontent;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.models.ProductResponse;
import com.app.goaheadapp.models.StoreResponse;
import com.app.goaheadapp.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopContentViewModel extends ViewModel {
    List<StoreResponse.ItemsBean> list = new ArrayList<>();
    MutableLiveData<ProductResponse> postsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<StoreResponse> mutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> posts = new MutableLiveData<>();


    public void getStoreCat(String id, Context homeFragment){
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getStoreCat(token,currentLang,id).enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                MyProgressDialog.dismissDialog();
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {

            }
        });
    }

    public void getProduct(String store_id, String category_store_id,Context homeFragment){
//        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().getProducts(token,currentLang,store_id,category_store_id).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                postsMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
    }
}
