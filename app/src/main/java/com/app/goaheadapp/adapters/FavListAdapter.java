package com.app.goaheadapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.goaheadapp.HomeViewModel;
import com.app.goaheadapp.databinding.ItemFavBinding;
import com.app.goaheadapp.models.Favorite;

import java.util.List;

public class FavListAdapter extends RecyclerView.Adapter<FavListAdapter.VH> {
    Context context;
    List<Favorite> Favorite;

    HomeViewModel viewModel;

    public FavListAdapter(Context context, List<Favorite> Favorite) {
        this.context = context;
        this.Favorite = Favorite;
        this.viewModel = new HomeViewModel(context);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemFavBinding itemBinding =
                ItemFavBinding.inflate(layoutInflater, parent, false);
        return new VH(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        final Favorite favorite = Favorite.get(position);
        holder.bind(favorite);


        holder.binding.fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (favorite.getPrice() != null)
                        viewModel.setFavorite(context, favorite.getId() + "");
                    else
                        viewModel.setFavoriteStore(context, favorite.getId() + "");
                } else {
                    if (favorite.getPrice() != null)
                        viewModel.removeFavorite(context, favorite.getId() + "");
                    else
                        viewModel.removeFavoriteStore(context, favorite.getId() + "");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Favorite.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        private final ItemFavBinding binding;

        public VH(ItemFavBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Favorite item) {
            binding.setItem(item);
            binding.executePendingBindings();

            if (item.getIs_favourite() == 1) {
                binding.fav.setChecked(true);
            }
        }
    }
}
