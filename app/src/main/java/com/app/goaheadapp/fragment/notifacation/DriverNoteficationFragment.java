package com.app.goaheadapp.fragment.notifacation;

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

import com.app.goaheadapp.R;
import com.app.goaheadapp.adapters.DriverNotificationAdapter;
import com.app.goaheadapp.adapters.NotificationAdapter;
import com.app.goaheadapp.databinding.FragmentDriverNoteficationBinding;
import com.app.goaheadapp.models.Notification;
import com.app.goaheadapp.models.NotificationResponse;

import java.util.ArrayList;
import java.util.List;


public class DriverNoteficationFragment extends Fragment {
    FragmentDriverNoteficationBinding binding;
    View view;

    private NotificationViewModel viewModel;
    private DriverNotificationAdapter adapter;
    private List<Notification> notifications;

    public DriverNoteficationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notifications = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(NotificationViewModel.class);
        adapter = new DriverNotificationAdapter(getContext(), notifications);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_driver_notefication, container, false);
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_driver_notefication, container, false);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.resc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.resc.setAdapter(adapter);

        viewModel.getNotification(getActivity());
        viewModel.postsMutableLiveData.removeObservers(getViewLifecycleOwner());
        viewModel.postsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<NotificationResponse>() {
            @Override
            public void onChanged(NotificationResponse notificationResponse) {
                if (notificationResponse.isStatus()) {
                    adapter.addMore(notificationResponse.getItems());
                }
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
        textView.setText("Notification");
        cart.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);
    }
}