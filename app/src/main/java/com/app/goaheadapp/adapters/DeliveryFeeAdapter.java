package com.app.goaheadapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.goaheadapp.R;
import com.app.goaheadapp.interfaces.TotalInterface;
import com.app.goaheadapp.databinding.ItemDeliveryCostBinding;
import com.app.goaheadapp.models.DeliveryCostResponse;

import java.util.List;

public class DeliveryFeeAdapter extends RecyclerView.Adapter<DeliveryFeeAdapter.VH> {
    Context context;
    List<DeliveryCostResponse.ItemsBean> list;
    int sum = 0;
    TotalInterface totalInterface;
    public DeliveryFeeAdapter(Context context, List<DeliveryCostResponse.ItemsBean> list,TotalInterface totalInterface) {
        this.context = context;
        this.totalInterface = totalInterface;
        this.list = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemDeliveryCostBinding itemBinding =
                ItemDeliveryCostBinding.inflate(layoutInflater, parent, false);
        return new DeliveryFeeAdapter.VH(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(list.get(position));
        totalInterface.onPriceChange(sum);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VH extends RecyclerView.ViewHolder{
            ItemDeliveryCostBinding binding;
            public VH(ItemDeliveryCostBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            public void bind(DeliveryCostResponse.ItemsBean item) {
                binding.setItem(item);
                binding.executePendingBindings();

                String s = context.getResources().getString(R.string.cost_delivery)+" ";
                String s1 = context.getResources().getString(R.string.cost_delivery_out)+" ";

                if (item.getType() == 1) {
                    binding.place.setText(s+item.getStore_id()+s1);
                }else {
                    binding.place.setText(s+item.getStore_id());
                }

                for (DeliveryCostResponse.ItemsBean itemsBean: list)
                    sum+=itemsBean.getCost();
            }
        }
}
