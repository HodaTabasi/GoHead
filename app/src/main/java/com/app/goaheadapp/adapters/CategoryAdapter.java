package com.app.goaheadapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.app.goaheadapp.R;
import com.app.goaheadapp.databinding.ItemCategoryBinding;
import com.app.goaheadapp.databinding.ItemProductBinding;
import com.app.goaheadapp.models.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.VH> {
    Context context;
    List<Category> categories;
    String id;

    public CategoryAdapter(Context context, List<Category> categories, String id) {
        this.context = context;
        this.categories = categories;
        this.id = id;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemCategoryBinding itemBinding =
                ItemCategoryBinding.inflate(layoutInflater, parent, false);
        return new VH(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        holder.bind(categories.get(position));

        holder.binding.device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("cat_id", id);
                bundle.putParcelable("cat", categories.get(position));
                NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment);
                navController.navigate(R.id.action_homeFragment3_to_listFragment, bundle);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("cat_id", id);
                bundle.putParcelable("cat", categories.get(position));
                NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment);
                navController.navigate(R.id.action_homeFragment3_to_listFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        private final ItemCategoryBinding binding;

        public VH(ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Category item) {
            binding.setItem(item);
            binding.executePendingBindings();

            if (id.equals("0")) {
                binding.device.getLayoutParams().width = (int) context.getResources().getDimension(R.dimen.image2_w);
                binding.device.getLayoutParams().height = (int) context.getResources().getDimension(R.dimen.image2_h);
            } else {
                binding.device.getLayoutParams().width = (int) context.getResources().getDimension(R.dimen.image_w);
                binding.device.getLayoutParams().height = (int) context.getResources().getDimension(R.dimen.image_h);
            }
            binding.device.requestLayout();
        }
    }
}
