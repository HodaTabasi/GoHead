package com.app.goaheadapp.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.goaheadapp.BaseActivity;
import com.app.goaheadapp.R;
import com.app.goaheadapp.interfaces.TotalInterface;
import com.app.goaheadapp.adapters.DeliveryFeeAdapter;
import com.app.goaheadapp.databinding.DialogDeliveryFeeBinding;
import com.app.goaheadapp.models.DeliveryCostResponse;
import com.app.goaheadapp.models.copunResponse;

public class DeliveryFeeCost extends Dialog implements View.OnClickListener {
    String string;
    DialogDeliveryFeeBinding binding;
    DeliveryCostViewModel viewModel;
    Context context;

    public DeliveryFeeCost(@NonNull Context context, int themeResId, String string) {
        super(context, themeResId);
        this.string = string;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_delivery_fee, null, false);
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(DeliveryCostViewModel.class);
        binding.cost.setText(string);
        binding.cancel.setOnClickListener(this);
        binding.ok.setOnClickListener(this);
        binding.copun.setOnClickListener(this);
        binding.recs.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        loadData();
        binding.reload.setOnClickListener(v -> {
            loadData();
        });
    }

    private void loadData() {
        if (BaseActivity.isConnected(getContext())) {
            binding.content.setVisibility(View.VISIBLE);
            binding.reload.setVisibility(View.GONE);
            putData();
        } else {
            binding.content.setVisibility(View.GONE);
            binding.reload.setVisibility(View.VISIBLE);
        }
    }

    private void putData() {
        viewModel.getDeliveryCost(context);
        viewModel.mutableLiveData.observe((LifecycleOwner) context, deliveryCostResponse -> binding.recs.setAdapter(new DeliveryFeeAdapter(context, deliveryCostResponse.getItems(), totalInterface)));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                dismiss();
                break;
            case R.id.ok:
                Bundle bundle = new Bundle();
                bundle.putString("cost", string);
                NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment);
                navController.navigate(R.id.orderDeiailsFragment, bundle);
                break;
            case R.id.copun:
                if (BaseActivity.isConnected(getContext())) {
                    if (!TextUtils.isEmpty(binding.code.getText())) {
                        viewModel.getDeliveryCopun(getContext(), binding.code.getText().toString(), binding.cost.getText().toString());
                        viewModel.capunMutableLiveData.observe((LifecycleOwner) context, copunResponse -> {
                            if (copunResponse.isStatus()) {
                                binding.cost.setText(copunResponse.getItems());
                                string = copunResponse.getItems();
                            } else {
                                Toast.makeText(context, "" + copunResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }
        dismiss();
    }

    TotalInterface totalInterface = new TotalInterface() {
        @Override
        public void onPriceChange(int Value) {
            int sum = Integer.parseInt(string);
            sum += Value;
            string = String.valueOf(sum);
            binding.cost.setText(sum + " ");
        }
    };
}
