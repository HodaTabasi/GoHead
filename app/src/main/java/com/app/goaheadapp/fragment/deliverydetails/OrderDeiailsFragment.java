package com.app.goaheadapp.fragment.deliverydetails;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.RadioButton;
import android.widget.Toast;

import com.app.goaheadapp.OrderViewModel;
import com.app.goaheadapp.R;
import com.app.goaheadapp.SplashViewModel;
import com.app.goaheadapp.databinding.FragmentOrderDeiailsBinding;
import com.app.goaheadapp.databinding.StubCardPaymentOptionsBinding;
import com.app.goaheadapp.interfaces.GetUserData;
import com.app.goaheadapp.models.User;
import com.app.goaheadapp.models.getPaymentRresponse;

import io.paperdb.Paper;


public class OrderDeiailsFragment extends Fragment implements GetUserData {

    boolean flag = false;
    View my_View;
    private OrderDetailsViewModel viewModel;
    int items;
    String lat;
    String lng;

    public OrderDeiailsFragment() {
        // Required empty public constructor
    }

    FragmentOrderDeiailsBinding binding;
    View view;
    View optionsBinding;
    StubCardPaymentOptionsBinding bindingPayment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_order_deiails, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(OrderDetailsViewModel.class);
        binding.setMymodel(viewModel);

        OrderViewModel orderViewModel = new OrderViewModel(getContext(), this);
        binding.setMyordermodel(orderViewModel);
        binding.setMyViewModel(new SplashViewModel(getActivity()));

        binding.totalPrice.setText(getArguments().getString("cost"));
        User user = Paper.book().read("data");

        binding.name.setText(user.getName());
        binding.phone.setText(user.getMobile());
        getPaymentMethod();
        optionsBinding = binding.layoutStub.getViewStub().inflate();

//        RadioGroup radioGroup = optionsBinding.findViewById(R.id.payment);

//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                switch (i) {
//                    case R.id.card:
//                        flag = true;
//                        break;
//                    case R.id.upon:
//                        flag = false;
//                        break;
//                }
//            }
//        });

        binding.two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.two.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.c_blue_shape));
                binding.two.setTextColor(Color.WHITE);
                if (flag) {
                    binding.layoutStub3.setOnInflateListener(new ViewStub.OnInflateListener() {
                        @Override
                        public void onInflate(ViewStub stub, View inflated) {
                            bindingPayment = DataBindingUtil.bind(inflated);
                        }
                    });
//                    my_View = binding.layoutStub3.getViewStub().inflate();
                    layTwo();
                } else {
                    my_View = binding.layoutStub2.getViewStub().inflate();
                    layOne(my_View);
                }

            }
        });


    }

    private void getPaymentMethod() {
        viewModel.getPayment(getActivity());
        viewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<getPaymentRresponse>() {
            @Override
            public void onChanged(getPaymentRresponse getPaymentRresponse) {
                if (getPaymentRresponse.isStatus()) {
                    items = getPaymentRresponse.getItems();
                    if (getPaymentRresponse.getItems() == 0) {
                        flag = false;
                        RadioButton radioButton = optionsBinding.findViewById(R.id.upon);
                        RadioButton radioButton2 = optionsBinding.findViewById(R.id.card);
                        radioButton.setChecked(true);
                        radioButton2.setEnabled(false);
                    } else {
                        flag = true;
                        RadioButton radioButton = optionsBinding.findViewById(R.id.upon);
                        RadioButton radioButton2 = optionsBinding.findViewById(R.id.card);
                        radioButton2.setChecked(true);
                        radioButton.setEnabled(false);
                    }
                }
            }
        });
    }


    private void layTwo() {
        if (!binding.layoutStub3.isInflated()) {
            my_View = binding.layoutStub3.getViewStub().inflate();
            bindingPayment.setMymodel(viewModel);
        }

        switch (items){
            case 1:
                bindingPayment.pay.setColorFilter(null);
                bindingPayment.visaa.setColorFilter(ContextCompat.getColor(getContext(), R.color.tintBackground), android.graphics.PorterDuff.Mode.MULTIPLY);
                bindingPayment.card.setColorFilter(ContextCompat.getColor(getContext(), R.color.tintBackground), android.graphics.PorterDuff.Mode.MULTIPLY);
                bindingPayment.paypal.setVisibility(View.VISIBLE);
                bindingPayment.visa.setVisibility(View.GONE);
                break;
            case 2:
                bindingPayment.visaa.setColorFilter(null);
                bindingPayment.pay.setColorFilter(ContextCompat.getColor(getContext(), R.color.tintBackground), android.graphics.PorterDuff.Mode.MULTIPLY);
                bindingPayment.card.setColorFilter(ContextCompat.getColor(getContext(), R.color.tintBackground), android.graphics.PorterDuff.Mode.MULTIPLY);
                bindingPayment.paypal.setVisibility(View.GONE);
                bindingPayment.visa.setVisibility(View.VISIBLE);
                break;
            case 3:
                bindingPayment.card.setColorFilter(null);
                bindingPayment.visaa.setColorFilter(ContextCompat.getColor(getContext(), R.color.tintBackground), android.graphics.PorterDuff.Mode.MULTIPLY);
                bindingPayment.pay.setColorFilter(ContextCompat.getColor(getContext(), R.color.tintBackground), android.graphics.PorterDuff.Mode.MULTIPLY);
                bindingPayment.paypal.setVisibility(View.GONE);
                bindingPayment.visa.setVisibility(View.VISIBLE);
                break;
        }

        bindingPayment.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "ffbkjg dj", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void layOne(View view) {
        AppCompatButton sure = view.findViewById(R.id.sure_payment);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_orderDeiailsFragment_to_chooseDriverFragment);
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
        textView.setText("Order Details");
        cart.setVisibility(View.VISIBLE);
        clear.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
    }

    @Override
    public void getData(String name, String phone) {
        binding.name.setText(name);
        binding.phone.setText(phone);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                String address = data.getStringExtra("result");
                 lat = data.getStringExtra("lat");
                 lng = data.getStringExtra("lng");
                binding.addressInfo.setText(address);
            }
        }
    }
}