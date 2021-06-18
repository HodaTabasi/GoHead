package com.app.goaheadapp.activitys.savedaddresses;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.activitys.AddAddressActivity;
import com.app.goaheadapp.models.AddAddressResponse;
import com.app.goaheadapp.models.Address;
import com.app.goaheadapp.models.AddressResponse;
import com.app.goaheadapp.models.User;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavedAddressViewModel extends ViewModel {
    MutableLiveData<AddressResponse> postsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<AddAddressResponse> addMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> posts = new MutableLiveData<>();

    public void getAddress(SavedAddress savedAddress) {
        MyProgressDialog.showDialog(savedAddress);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();

        if (user != null) {
            NetworkUtils.getInstance().getAddress(token).enqueue(new Callback<AddressResponse>() {
                @Override
                public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                    postsMutableLiveData.setValue(response.body());
                    MyProgressDialog.dismissDialog();
                }

                @Override
                public void onFailure(Call<AddressResponse> call, Throwable t) {
                }
            });
        } else {
            Toast.makeText(savedAddress, "no users", Toast.LENGTH_SHORT).show();
        }

    }

    public void addAddress(Address address, final AddAddressActivity addressActivity){
        MyProgressDialog.showDialog(addressActivity);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        NetworkUtils.getInstance().addAddress(token,address).enqueue(new Callback<AddAddressResponse>() {
            @Override
            public void onResponse(Call<AddAddressResponse> call, Response<AddAddressResponse> response) {
                addMutableLiveData.setValue(response.body());
                MyProgressDialog.dismissDialog();
                AddAddressResponse addAddressResponse = addMutableLiveData.getValue();
                if (addAddressResponse.isStatus()){
                    addressActivity.onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<AddAddressResponse> call, Throwable t) {

            }
        });
    }
}
