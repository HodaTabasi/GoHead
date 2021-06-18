package com.app.goaheadapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.app.goaheadapp.HomeViewModel;
import com.app.goaheadapp.R;
import com.app.goaheadapp.databinding.ItemProductBinding;
import com.app.goaheadapp.models.SubCategory;

import java.util.List;

public class SubCatListAdapter extends RecyclerView.Adapter<SubCatListAdapter.VH> {
    Context context;
    List<SubCategory> workers;
    String cat_id;
    HomeViewModel viewModel;

    public SubCatListAdapter(Context context, List<SubCategory> workers, String cat_id) {
        this.context = context;
        this.workers = workers;
        this.cat_id = cat_id;
        viewModel = new HomeViewModel(context);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemProductBinding itemBinding =
                ItemProductBinding.inflate(layoutInflater, parent, false);
        return new VH(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        final SubCategory subCategory = workers.get(position);
        holder.bind(subCategory);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment);
                if (cat_id.equals("1")) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("object",workers.get(position));
                    navController.navigate(R.id.action_listFragment_to_lookingProductDescrptionFragment,bundle);
                }else {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("object",workers.get(position));
                    navController.navigate(R.id.action_listFragment_to_shopContentFragment,bundle);
                }
            }
        });

        holder.binding.fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    viewModel.setFavoriteStore(context,subCategory.getId()+"");
                }else {
                    viewModel.removeFavoriteStore(context,subCategory.getId()+"");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return workers.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        private final ItemProductBinding binding;

        public VH(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(SubCategory item) {
            binding.setItem(item);
            binding.executePendingBindings();
            if (cat_id.equals("1")) {
                binding.productTime.setVisibility(View.INVISIBLE);
            }
            if (item.getIs_favourite() == 1){
                binding.fav.setChecked(true);
            }
        }
    }
}
