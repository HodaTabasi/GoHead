package com.app.goaheadapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.goaheadapp.databinding.ItemDrivierNotificationBinding;
import com.app.goaheadapp.databinding.ItemUserNotificationBinding;
import com.app.goaheadapp.models.Notification;

import java.util.List;

public class DriverNotificationAdapter extends RecyclerView.Adapter<DriverNotificationAdapter.DriverViewHolder> {

    Context context;
    List<Notification> notifications;

    public DriverNotificationAdapter(Context context, List<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
    }
    @NonNull
    @Override
    public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemDrivierNotificationBinding itemBinding =
                ItemDrivierNotificationBinding.inflate(layoutInflater, parent, false);
        return new DriverViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {
        holder.bind(notifications.get(position));
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public void addMore(List<Notification> notifications){
        this.notifications.addAll(notifications);
        notifyDataSetChanged();
    }

    class DriverViewHolder extends RecyclerView.ViewHolder {
        ItemDrivierNotificationBinding binding;

        public DriverViewHolder(ItemDrivierNotificationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Notification item) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
}
