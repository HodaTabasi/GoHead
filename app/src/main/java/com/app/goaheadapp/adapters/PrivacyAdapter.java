package com.app.goaheadapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.goaheadapp.R;
import com.app.goaheadapp.databinding.ItemPrivacyBinding;
import com.app.goaheadapp.models.Privacy;

import java.util.List;

public class PrivacyAdapter extends RecyclerView.Adapter<PrivacyAdapter.VH> {
    Context context;
    List<Privacy> privacies;

    public PrivacyAdapter(Context context, List<Privacy> privacies) {
        this.context = context;
        this.privacies = privacies;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemPrivacyBinding itemBinding =
                ItemPrivacyBinding.inflate(layoutInflater, parent, false);
        return new VH(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final VH holder, final int position) {
        final Privacy product = privacies.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return privacies.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        ItemPrivacyBinding binding;

        public VH(ItemPrivacyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Privacy item) {
            binding.setItem(item);
            binding.executePendingBindings();

            binding.title.setText(context.getResources().getString(R.string.privacy_policy) + " " + item.getId());
        }
    }
}
