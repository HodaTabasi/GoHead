package com.app.goaheadapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.goaheadapp.databinding.ItemOrderBillBinding;
import com.app.goaheadapp.databinding.ItemPrivacyBinding;
import com.app.goaheadapp.interfaces.GetOrderDetails;
import com.app.goaheadapp.models.Order;

import java.util.List;

public class OrderTrakingStoreAdapter extends RecyclerView.Adapter<OrderTrakingStoreAdapter.VH> {
    Context context;
    List<Order> orders;
    GetOrderDetails orderDetails;

    public OrderTrakingStoreAdapter(Context context, List<Order> orders, GetOrderDetails getOrderDetails) {
        this.context = context;
        this.orders = orders;
        this.orderDetails = getOrderDetails;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemOrderBillBinding itemBinding =
                ItemOrderBillBinding.inflate(layoutInflater, parent, false);
        return new OrderTrakingStoreAdapter.VH(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        holder.bind(orders.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderDetails.getData(orders.get(position));
            }
        });
    }

    public void addMore(List<Order> orders){
        this.orders.addAll(orders);
        notifyDataSetChanged();
    }
    public void clear(){
        this.orders.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        ItemOrderBillBinding binding;

        public VH(ItemOrderBillBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Order item) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
}
