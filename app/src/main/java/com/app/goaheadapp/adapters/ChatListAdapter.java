package com.app.goaheadapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.goaheadapp.R;
import com.app.goaheadapp.Utils.FragmentsUtil;
import com.app.goaheadapp.databinding.ItemNoteListBinding;
import com.app.goaheadapp.fragment.chat.ChatFragment;
import com.app.goaheadapp.models.MessageList;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.VH> {
    Context context;
    List<MessageList> MessageLists;

    public ChatListAdapter(Context context, List<MessageList> MessageLists) {
        this.context = context;
        this.MessageLists = MessageLists;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemNoteListBinding itemBinding =
                ItemNoteListBinding.inflate(layoutInflater, parent, false);
        return new VH(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        holder.bind(MessageLists.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("object",MessageLists.get(position));
                bundle.putInt("flag",0);
                ChatFragment fragment = new ChatFragment();
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.my_container, fragment,true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return MessageLists.size();
    }

    public void addAll(List<MessageList> lists){
        MessageLists.addAll(lists);
        notifyDataSetChanged();
    }

    public class VH extends RecyclerView.ViewHolder{
        ItemNoteListBinding binding;

        public VH(ItemNoteListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MessageList item) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
}
