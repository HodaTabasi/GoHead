package com.app.goaheadapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.app.goaheadapp.HomeViewModel;
import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.R;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.databinding.ItemOrderDetailsBinding;
import com.app.goaheadapp.databinding.ItemRestruntContentBinding;
import com.app.goaheadapp.models.AddSuccessfullyResponse;
import com.app.goaheadapp.models.Product;
import com.app.goaheadapp.models.ProductOrder;
import com.app.goaheadapp.models.SubCategory;
import com.app.goaheadapp.models.User;

import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductOrderDetailsAdapter extends RecyclerView.Adapter<ProductOrderDetailsAdapter.VH> {
    Context context;
    List<ProductOrder> products;

    public ProductOrderDetailsAdapter(Context context, List<ProductOrder> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemOrderDetailsBinding itemBinding =
                ItemOrderDetailsBinding.inflate(layoutInflater, parent, false);
        return new VH(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final VH holder, final int position) {
        final ProductOrder product = products.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        ItemOrderDetailsBinding binding;
        public VH(ItemOrderDetailsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ProductOrder item) {
            binding.setItem(item);
            binding.executePendingBindings();

        }
    }
}
