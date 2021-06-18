package com.app.goaheadapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.goaheadapp.HomeViewModel;
import com.app.goaheadapp.NetworkUtils;
import com.app.goaheadapp.interfaces.TotalInterface;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.databinding.ItemRestruntContentBinding;
import com.app.goaheadapp.models.Cart;
import com.app.goaheadapp.models.DeleteCartResponse;
import com.app.goaheadapp.models.IncteaseCuntResponse;
import com.app.goaheadapp.models.Product;
import com.app.goaheadapp.models.User;

import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.VH> {
    public Context context;
    List<Cart> carts;
    TotalInterface totalInterface;
    int sum;
    HomeViewModel viewModel;


    public CartAdapter(Context context, List<Cart> carts, TotalInterface totalInterface, int sum) {
        this.context = context;
        this.carts = carts;
        this.totalInterface = totalInterface;
        this.sum = sum;
        viewModel = new HomeViewModel(context);
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
        final int[] count = {carts.get(position).getQuantity()};
        final Cart cart = carts.get(position);
        holder.bind(cart.getProduct());


        holder.binding.addOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.binding.quantity.setText(String.valueOf(++count[0]));
                addCountItem(position);
                cart.setQuantity(Integer.parseInt(holder.binding.quantity.getText().toString()));
                calculatePrice();
            }
        });

        holder.binding.minOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count[0] > 1) {
                    holder.binding.quantity.setText(String.valueOf(--count[0]));
                    decreaseCountItem(position);
                    cart.setQuantity(Integer.parseInt(holder.binding.quantity.getText().toString()));
                    Log.e("dffb", sum + "");
                    calculatePrice();
                }
            }
        });

        holder.binding.fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    viewModel.setFavorite(context, cart.getProduct().getId() + "");
                } else {
                    viewModel.removeFavorite(context, cart.getProduct().getId() + "");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    private void calculatePrice() {
        sum = 0;
        for (Cart cart : carts) {
            sum += (cart.getPrice() * cart.getQuantity());
            Log.e("dffb", sum + "");
        }
        totalInterface.onPriceChange(sum);
    }

    public void deleteItem(int position) {
        MyProgressDialog.showDialog(context);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().deleteFromCart(token, currentLang, carts.get(position).getId() + "")
                .enqueue(new Callback<DeleteCartResponse>() {
                    @Override
                    public void onResponse(Call<DeleteCartResponse> call, Response<DeleteCartResponse> response) {
                        MyProgressDialog.dismissDialog();
                        if (response.body().isStatus()) {
                            Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteCartResponse> call, Throwable t) {

                    }
                });
    }

    public void addCountItem(int position) {
        MyProgressDialog.showDialog(context);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().increaseQuantity(token, currentLang, carts.get(position).getProduct_id() + "")
                .enqueue(new Callback<IncteaseCuntResponse>() {
                    @Override
                    public void onResponse(Call<IncteaseCuntResponse> call, Response<IncteaseCuntResponse> response) {
                        MyProgressDialog.dismissDialog();
                        if (response.body().isStatus()) {
                            Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<IncteaseCuntResponse> call, Throwable t) {

                    }
                });
    }

    public void decreaseCountItem(int position) {
        MyProgressDialog.showDialog(context);
        User user = Paper.book().read("data");
        String token = "Bearer " + user.getAccess_token();
        String currentLang = Locale.getDefault().getLanguage();

        NetworkUtils.getInstance().decreaseQuantity(token, currentLang, carts.get(position).getProduct_id() + "")
                .enqueue(new Callback<IncteaseCuntResponse>() {
                    @Override
                    public void onResponse(Call<IncteaseCuntResponse> call, Response<IncteaseCuntResponse> response) {
                        MyProgressDialog.dismissDialog();
                        if (response.body().isStatus()) {
                            Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<IncteaseCuntResponse> call, Throwable t) {

                    }
                });
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
            binding.add.setVisibility(View.INVISIBLE);

            binding.quantity.setText(carts.get(getAdapterPosition()).getQuantity()+"");
            if (item.getIs_favourite() == 1) {
                binding.fav.setChecked(true);
            }
        }
    }
}
