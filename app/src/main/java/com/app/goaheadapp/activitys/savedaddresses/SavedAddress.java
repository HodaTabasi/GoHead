package com.app.goaheadapp.activitys.savedaddresses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.app.goaheadapp.BaseActivity;
import com.app.goaheadapp.R;
import com.app.goaheadapp.SplashViewModel;
import com.app.goaheadapp.databinding.ActivitySavedAddressBinding;
import com.app.goaheadapp.models.Address;
import com.app.goaheadapp.models.AddressResponse;

public class SavedAddress extends AppCompatActivity {

    private SavedAddressViewModel viewModel;

    ActivitySavedAddressBinding activitySavedAddressBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activitySavedAddressBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_saved_address);
        activitySavedAddressBinding.setMyViewModel(new SplashViewModel(this));

        viewModel = new ViewModelProvider(this).get(SavedAddressViewModel.class);
        activitySavedAddressBinding.setAddress(viewModel);

        loadData();

        activitySavedAddressBinding.reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void loadData() {
        if (BaseActivity.isConnected(this)) {
            activitySavedAddressBinding.content.setVisibility(View.VISIBLE);
            activitySavedAddressBinding.reload.setVisibility(View.GONE);
            getData();
        } else {
            activitySavedAddressBinding.content.setVisibility(View.GONE);
            activitySavedAddressBinding.reload.setVisibility(View.VISIBLE);
        }
    }

    private void getData() {
        viewModel.getAddress(this);
        putData();
    }


    private void putData() {
        viewModel.postsMutableLiveData.observe(this, new Observer<AddressResponse>() {
            @Override
            public void onChanged(final AddressResponse addressResponse) {
                ArrayAdapter<Address> adapter = new ArrayAdapter<Address>(SavedAddress.this, R.layout.item_address, R.id.address, addressResponse.getItems());
                activitySavedAddressBinding.resc.setAdapter(adapter);
                activitySavedAddressBinding.resc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result", addressResponse.getItems().get(i).getAddress());
                        returnIntent.putExtra("lat", addressResponse.getItems().get(i).getLat());
                        returnIntent.putExtra("lng", addressResponse.getItems().get(i).getLan());
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }
                });

            }
        });
    }
}
