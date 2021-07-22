package com.app.goaheadapp.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.goaheadapp.R;
import com.app.goaheadapp.adapters.DeliveryFeeAdapter;
import com.app.goaheadapp.adapters.ProductOrderDetailsAdapter;
import com.app.goaheadapp.databinding.DialogDeliveryFeeBinding;
import com.app.goaheadapp.databinding.DialogOrderDetailsBinding;
import com.app.goaheadapp.interfaces.TotalInterface;
import com.app.goaheadapp.models.DeliveryCostResponse;
import com.app.goaheadapp.models.Order;

public class ProductDetails extends Dialog implements View.OnClickListener {
    String string;
    DialogOrderDetailsBinding binding;
    ProductOrderDetailsAdapter adapter;
    //    DeliveryCostViewModel viewModel;
    Context context;
    Order order;

    public ProductDetails(@NonNull Context context, int themeResId, Order order) {
        super(context, themeResId);
        this.order = order;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_order_details, null, false);
        setContentView(binding.getRoot());

        adapter = new ProductOrderDetailsAdapter(context, order.getProducts());

        binding.resc.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        binding.resc.setAdapter(adapter);

        binding.orderNumber.setText(order.getId() + "");
        binding.totalPrice.setText(order.getTotal_cost() + "");
        binding.fee.setText(order.getDelivery_cost() + "");
        binding.price.setText(order.getProduct_cost() + "");
        binding.paymentWay.setText(order.getType_payment() + " f");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                dismiss();
                break;
            case R.id.ok:
                Bundle bundle = new Bundle();
                NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment);
                navController.navigate(R.id.orderDeiailsFragment, bundle);
                break;
            default:
                break;
        }
        dismiss();
    }
}
