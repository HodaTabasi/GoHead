package com.app.goaheadapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.goaheadapp.databinding.ItemChatLeftBinding;
import com.app.goaheadapp.databinding.ItemChatRightBinding;
import com.app.goaheadapp.databinding.ItemRestruntContentBinding;
import com.app.goaheadapp.models.ChatDitails;
import com.app.goaheadapp.models.User;

import java.util.List;

import io.paperdb.Paper;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    List<ChatDitails> chats;

    public MessageAdapter(List<ChatDitails> chats) {
        this.chats = chats;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_LEFT) {
            LayoutInflater layoutInflater =
                    LayoutInflater.from(parent.getContext());
            ItemChatLeftBinding itemBinding =
                    ItemChatLeftBinding.inflate(layoutInflater, parent, false);
            return new VHLeft(itemBinding);
        } else {
            LayoutInflater layoutInflater =
                    LayoutInflater.from(parent.getContext());
            ItemChatRightBinding itemBinding =
                    ItemChatRightBinding.inflate(layoutInflater, parent, false);
            return new VHRight(itemBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == MSG_TYPE_LEFT)
            ((VHLeft)holder).bind(chats.get(position));
        else
            ((VHRight)holder).bind(chats.get(position));
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public void addMore(List<ChatDitails> ditails){
        chats.addAll(ditails);
        notifyDataSetChanged();
    }

    public class VHLeft extends RecyclerView.ViewHolder {

        ItemChatLeftBinding binding;

        public VHLeft(ItemChatLeftBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ChatDitails item) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }

    public class VHRight extends RecyclerView.ViewHolder {
        ItemChatRightBinding binding;

        public VHRight(ItemChatRightBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ChatDitails item) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }

    @Override
    public int getItemViewType(int position) {
        User user = Paper.book().read("data");

        if (user.getType() == chats.get(position).getType()){
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }
}
