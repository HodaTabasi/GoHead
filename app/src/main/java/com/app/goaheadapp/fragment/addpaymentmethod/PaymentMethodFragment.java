package com.app.goaheadapp.fragment.addpaymentmethod;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.goaheadapp.R;
import com.app.goaheadapp.databinding.FragmentPaymentMethodBinding;
import com.app.goaheadapp.models.AddSuccessfullyResponse;
import com.app.goaheadapp.models.MyPayment;
import com.app.goaheadapp.models.MyPaymentResponse;
import com.app.goaheadapp.models.PaymentMethodResponse;

import java.util.HashMap;
import java.util.List;


public class PaymentMethodFragment extends Fragment {

    private FragmentPaymentMethodBinding bindingPayment;
    private View view;
    private int items = 1;
    private PaymentViewModel viewModel;
    HashMap<String, String> map = new HashMap<>();
    MyPayment[] payments;

    public PaymentMethodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bindingPayment = DataBindingUtil.inflate(
                inflater, R.layout.fragment_payment_method, container, false);
        view = bindingPayment.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(PaymentViewModel.class);

        viewModel.getMyPayment(getContext());
        viewModel.myPaymentMutableLiveData.removeObservers(getViewLifecycleOwner());
        viewModel.myPaymentMutableLiveData.observe(getViewLifecycleOwner(), new Observer<MyPaymentResponse>() {
            @Override
            public void onChanged(MyPaymentResponse myPaymentResponse) {
                if (myPaymentResponse.isStatus()) {
                    putData(myPaymentResponse.getItems());
                } else {
                    Toast.makeText(getContext(), "" + myPaymentResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.deletePaymentMutableLiveData.removeObservers(getViewLifecycleOwner());
        viewModel.deletePaymentMutableLiveData.observe(getViewLifecycleOwner(), new Observer<AddSuccessfullyResponse>() {
            @Override
            public void onChanged(AddSuccessfullyResponse addSuccessfullyResponse) {
                Toast.makeText(getContext(), "" + addSuccessfullyResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        bindingPayment.pay.setColorFilter(null);
        bindingPayment.visaa.setColorFilter(ContextCompat.getColor(getContext(), R.color.tintBackground), android.graphics.PorterDuff.Mode.MULTIPLY);
        bindingPayment.card.setColorFilter(ContextCompat.getColor(getContext(), R.color.tintBackground), android.graphics.PorterDuff.Mode.MULTIPLY);
        bindingPayment.paypal.setVisibility(View.VISIBLE);
        bindingPayment.visa.setVisibility(View.GONE);

        bindingPayment.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items = 1;
                selectItem();
            }
        });

        bindingPayment.visaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items = 2;
                selectItem();
            }
        });

        bindingPayment.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items = 3;
                selectItem();
            }
        });

        bindingPayment.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPayment();
            }
        });

        bindingPayment.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPayment();
            }
        });

        bindingPayment.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deletePayment(getContext(), payments[items].getId() + "");
            }
        });

    }

    private void putData(List<MyPayment> items) {
        for (MyPayment payment : items) {
            switch (payment.getType()) {
                case 1:
                case 3:
                    bindingPayment.cardNumber.setText(payment.getCardNo());
                    bindingPayment.holderName.setText(payment.getHoldername());
//                    bindingPayment.cvv.setText(payment.get());
                    String[] arrOfStr = payment.getExpiredate().split("-");
                    bindingPayment.month.setText(arrOfStr[1]);
                    bindingPayment.year.setText(arrOfStr[0]);
                    break;
                case 2:
                    bindingPayment.email.setText(payment.getEmail());
                    break;
            }
            payments[payment.getType()] = payment;
        }
    }

    private void addPayment() {
        switch (items) {
            case 1:
                map.put("email", bindingPayment.email.getText().toString());
                break;
            case 2:
                map.put("holdername", bindingPayment.holderName.getText().toString());
                map.put("cardNo", bindingPayment.cardNumber.getText().toString());
                map.put("expiredate", bindingPayment.month.getText().toString() + "-" + bindingPayment.year.getText().toString());
                break;
            case 3:
                break;
        }

        map.put("type", String.valueOf(items));
        viewModel.addPayment(map, getActivity());
        viewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<PaymentMethodResponse>() {
            @Override
            public void onChanged(PaymentMethodResponse paymentMethodResponse) {
                if (paymentMethodResponse.isStatus()) {
                    Toast.makeText(getContext(), "" + paymentMethodResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "" + paymentMethodResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void selectItem() {
        switch (items) {
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
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = ((AppCompatActivity) getActivity()).findViewById(R.id.toolbar_main);
        AppCompatTextView textView = view.findViewById(R.id.page_title);
        AppCompatImageView clear = view.findViewById(R.id.clear);
        AppCompatImageView cart = view.findViewById(R.id.cart);
        AppCompatImageView back = view.findViewById(R.id.back);
        textView.setText("Place Description");
        cart.setVisibility(View.VISIBLE);
        clear.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
    }
}