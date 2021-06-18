package com.app.goaheadapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.goaheadapp.databinding.ItemRestruntContentBinding;
import com.app.goaheadapp.databinding.ItemReteDriverBinding;
import com.app.goaheadapp.models.Rate;

import java.util.List;

public class RateDriverAdapter extends RecyclerView.Adapter<RateDriverAdapter.VH> {
    Context context;
    List<Rate> rates;

    public RateDriverAdapter(Context context, List<Rate> rates) {
        this.context = context;
        this.rates = rates;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemReteDriverBinding itemBinding =
                ItemReteDriverBinding.inflate(layoutInflater, parent, false);
        return new VH(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(rates.get(position));
    }

    @Override
    public int getItemCount() {
        return rates.size();
    }

    public void addMore(List<Rate> rate) {
        rates.addAll(rate);
        notifyDataSetChanged();
    }

    public class VH extends RecyclerView.ViewHolder {
        ItemReteDriverBinding binding;

        public VH(ItemReteDriverBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Rate item) {
            binding.setItem(item);
            binding.executePendingBindings();

        }
    }
}
