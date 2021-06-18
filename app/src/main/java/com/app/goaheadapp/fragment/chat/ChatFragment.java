package com.app.goaheadapp.fragment.chat;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.goaheadapp.R;
import com.app.goaheadapp.adapters.MessageAdapter;
import com.app.goaheadapp.databinding.FragmentCartBinding;
import com.app.goaheadapp.databinding.FragmentChatBinding;
import com.app.goaheadapp.models.AddSuccessfullyResponse;
import com.app.goaheadapp.models.ChatDetailsResponse;
import com.app.goaheadapp.models.ChatDitails;
import com.app.goaheadapp.models.MessageList;
import com.app.goaheadapp.models.Order;
import com.app.goaheadapp.models.User;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;


public class ChatFragment extends Fragment {


    FragmentChatBinding binding;
    private ChatViewModel viewModel;
    MessageAdapter adapter;
    List<ChatDitails> ditails;
    MessageList list;
    Order order;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        ditails = new ArrayList<>();
        adapter = new MessageAdapter(ditails);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_chat, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        viewModel.getNoteDitails(getActivity(), list.getId() + "");

        viewModel.postsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<ChatDetailsResponse>() {
            @Override
            public void onChanged(ChatDetailsResponse chatDetailsResponse) {
                if (chatDetailsResponse.isStatus()) {
                    adapter.addMore(chatDetailsResponse.getItems());
                }
            }
        });

        viewModel.postsMutableLiveData1.removeObservers(getViewLifecycleOwner());
        viewModel.postsMutableLiveData1.observe(getViewLifecycleOwner(), new Observer<AddSuccessfullyResponse>() {
            @Override
            public void onChanged(AddSuccessfullyResponse addSuccessfullyResponse) {
                if (addSuccessfullyResponse.isStatus()) {
                    Toast.makeText(getContext(), "" + addSuccessfullyResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    binding.message.setText("");
                }
            }
        });
    }

    private void initView() {
        Bundle bundle = getArguments();
        if (bundle.getInt("flag") == 0) {
            list = bundle.getParcelable("object");
            binding.setMessageList(list);
        } else
            order = bundle.getParcelable("object");

        User user = Paper.book().read("data");
        binding.setUser(user);

        binding.resc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.resc.setAdapter(adapter);

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.sendMessage(getContext(), list.getOrder_id() + "", list.getUser_id() + "", binding.message.getText().toString());
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        View view = ((AppCompatActivity) getActivity()).findViewById(R.id.toolbar_main);
        AppCompatTextView textView = view.findViewById(R.id.page_title);
        AppCompatImageView clear = view.findViewById(R.id.clear);
        AppCompatImageView cart = view.findViewById(R.id.cart);
        AppCompatImageView back = view.findViewById(R.id.back);
        textView.setText("Chat");
        cart.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);
    }
}