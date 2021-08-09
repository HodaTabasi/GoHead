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
import com.app.goaheadapp.databinding.ItemRestruntContentBinding;
import com.app.goaheadapp.models.AddSuccessfullyResponse;
import com.app.goaheadapp.models.Product;
import com.app.goaheadapp.models.SubCategory;
import com.app.goaheadapp.models.User;

import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.VH> {
    Context context;
    List<Product> products;
    int count = 1;
    HomeViewModel viewModel;
    SubCategory subCategory;

    public ProductAdapter(Context context, List<Product> products, SubCategory subCategory) {
        this.context = context;
        this.products = products;
        viewModel = new HomeViewModel(context);
        this.subCategory = subCategory;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemRestruntContentBinding itemBinding =
                ItemRestruntContentBinding.inflate(layoutInflater, parent, false);
        return new VH(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final VH holder, final int position) {
        final Product product = products.get(position);
        holder.bind(product);
        holder.binding.addOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.binding.quantity.setText(String.valueOf(++count));
            }
        });

        holder.binding.minOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count > 1)
                    holder.binding.quantity.setText(String.valueOf(--count));
            }
        });

        holder.binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProgressDialog.showDialog(context);
                User user = Paper.book().read("data");
                String token = "Bearer " + user.getAccess_token();
                String currentLang = Locale.getDefault().getLanguage();
                NetworkUtils.getInstance().addToCart(token,currentLang,products.get(position).getId()+"",holder.binding.quantity.getText().toString())
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
        });

        holder.binding.fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    viewModel.setFavorite(context,product.getId()+"");
                }else {
                    viewModel.removeFavorite(context,product.getId()+"");
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment);
                Bundle bundle = new Bundle();
                bundle.putParcelable("object",products.get(position));
                bundle.putParcelable("cat",subCategory);
                navController.navigate(R.id.action_shopContentFragment_to_rateProductFragment,bundle);
            }
        });

        holder.binding.ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "go to payment", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        ItemRestruntContentBinding binding;
        public VH(ItemRestruntContentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Product item) {
            binding.setItem(item);
            binding.executePendingBindings();

            if (item.getIs_favourite() == 1) {
                binding.fav.setChecked(true);
            }
        }
    }
}
