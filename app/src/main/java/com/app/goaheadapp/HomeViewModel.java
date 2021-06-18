package com.app.goaheadapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.app.goaheadapp.Utils.FragmentsUtil;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.dialog.DeliveryFeeCost;
import com.app.goaheadapp.fragment.editprofile.EditProfileFragment;
import com.app.goaheadapp.models.AddSuccessfullyResponse;
import com.app.goaheadapp.models.DeleteCartResponse;
import com.app.goaheadapp.models.User;

import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel {
    Context context;

    public HomeViewModel(Context context) {
        this.context = context;
    }

    public void goToFragmentDetails(int i) {
        NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment);
        Bundle bundle = new Bundle();
        switch (i) {
            case 1:
                bundle.putInt("id", 0);
                bundle.putBoolean("flag", true);
                break;
            case 2:
                bundle.putInt("id", 1);
                bundle.putBoolean("flag", true);
                break;
            case 3:
                bundle.putInt("id", 2);
                bundle.putBoolean("flag", true);
                break;
            case 4:
                bundle.putInt("id", 3);
                bundle.putBoolean("flag", true);
                break;
            case 5:
                bundle.putInt("id", 4);
                bundle.putBoolean("flag", true);
                break;
            case 6:
                bundle.putInt("id", 5);
                bundle.putBoolean("flag", true);
                break;
            case 7:
                bundle.putInt("id", 6);
                bundle.putBoolean("flag", true);
                break;
            case 8:
                bundle.putInt("id", 7);
                bundle.putBoolean("flag", true);
                break;
            case 9:
                bundle.putInt("id", 0);
                bundle.putBoolean("flag", false);
                break;
            case 10:
                bundle.putInt("id", 1);
                bundle.putBoolean("flag", false);
                break;
            case 11:
                bundle.putInt("id", 2);
                bundle.putBoolean("flag", false);
                break;
            case 12:
                bundle.putInt("id", 3);
                bundle.putBoolean("flag", false);
                break;
            case 13:
                bundle.putInt("id", 4);
                bundle.putBoolean("flag", false);
                break;
        }

        navController.navigate(R.id.action_homeFragment3_to_listFragment, bundle);
    }

    public void goToFragmentCart() {
        NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment);
        navController.navigate(R.id.action_shopContentFragment_to_cartFragment);
    }

    public void goToFragmentOrderDetails() {
        NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment);
        navController.navigate(R.id.action_cartFragment_to_orderDeiailsFragment);
    }


    public void goToFragmentDetail() {
        NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment);
        navController.navigate(R.id.action_listFragment_to_lookingProductDescrptionFragment);
    }

    public void goToFragmentEditProfile() {
        FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.my_container, new EditProfileFragment(),true);
//        NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment);
//        navController.navigate(R.id.action_profile_to_editProfileFragment);
    }

    public void setFavorite(final Context context,String id){
        MyProgressDialog.showDialog(context);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();
        NetworkUtils.getInstance().addToFav(token,currentLang,id)
                .enqueue(new Callback<AddSuccessfullyResponse>() {
                    @Override
                    public void onResponse(Call<AddSuccessfullyResponse> call, Response<AddSuccessfullyResponse> response) {
                        MyProgressDialog.dismissDialog();
                        Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<AddSuccessfullyResponse> call, Throwable t) {

                    }
                });
    }

    public void removeFavorite(final Context context,String id){
        MyProgressDialog.showDialog(context);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();
        NetworkUtils.getInstance().removeFromFav(token,currentLang,id)
                .enqueue(new Callback<AddSuccessfullyResponse>() {
                    @Override
                    public void onResponse(Call<AddSuccessfullyResponse> call, Response<AddSuccessfullyResponse> response) {
                        MyProgressDialog.dismissDialog();
                        Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<AddSuccessfullyResponse> call, Throwable t) {

                    }
                });
    }

    public void setFavoriteStore(final Context context,String id){
        MyProgressDialog.showDialog(context);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();
        NetworkUtils.getInstance().addToFavStore(token,currentLang,id)
                .enqueue(new Callback<AddSuccessfullyResponse>() {
                    @Override
                    public void onResponse(Call<AddSuccessfullyResponse> call, Response<AddSuccessfullyResponse> response) {
                        MyProgressDialog.dismissDialog();
                        Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<AddSuccessfullyResponse> call, Throwable t) {

                    }
                });
    }

    public void removeFavoriteStore(final Context context,String id){
        MyProgressDialog.showDialog(context);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();
        NetworkUtils.getInstance().removeFromFavStore(token,currentLang,id)
                .enqueue(new Callback<AddSuccessfullyResponse>() {
                    @Override
                    public void onResponse(Call<AddSuccessfullyResponse> call, Response<AddSuccessfullyResponse> response) {
                        MyProgressDialog.dismissDialog();
                        Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<AddSuccessfullyResponse> call, Throwable t) {

                    }
                });
    }

    public void showFeeDialog(String s){
        DeliveryFeeCost customDialog = new DeliveryFeeCost(context, R.style.mydialog,s);
        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog.show();
        Window window = customDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public void RateDriver(String id, String value, String text, final Context homeFragment) {
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().rateDriver(token, currentLang, id, value, text).enqueue(new Callback<DeleteCartResponse>() {
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

    public void RateStore(String id, String value, String text, final Context homeFragment) {
        MyProgressDialog.showDialog(homeFragment);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().rateStore(token, currentLang, id, value, text).enqueue(new Callback<DeleteCartResponse>() {
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
