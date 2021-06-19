package com.app.goaheadapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.app.goaheadapp.databinding.ItemUserNotificationBinding;
import com.app.goaheadapp.models.Notification;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.UserViewHolder> {
    Context context;
    List<Notification> notifications;

    public NotificationAdapter(Context context, List<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemUserNotificationBinding itemBinding =
                ItemUserNotificationBinding.inflate(layoutInflater, parent, false);
        return new UserViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.UserViewHolder holder, int position) {
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

    class UserViewHolder extends RecyclerView.ViewHolder{
        ItemUserNotificationBinding binding;

        public UserViewHolder(ItemUserNotificationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Notification item) {
            binding.setItem(item);
            binding.executePendingBindings();
            binding.add.setVisibility(View.INVISIBLE);
        }
    }

//    class DriverViewHolder extends RecyclerView.ViewHolder{
//        ItemDrivierNotificationBinding binding;
//
//        public DriverViewHolder(ItemDrivierNotificationBinding binding) {
//            super(binding.getRoot());
//            this.binding = binding;
//        }
//
//        public void bind(Notification item) {
//            binding.setItem(item);
//            binding.executePendingBindings();
//        }
//    }
}
