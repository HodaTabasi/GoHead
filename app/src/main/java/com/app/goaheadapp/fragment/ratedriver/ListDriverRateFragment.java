package com.app.goaheadapp.fragment.ratedriver;

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
import com.app.goaheadapp.adapters.RateDriverAdapter;
import com.app.goaheadapp.databinding.FragmentListDriverRateBinding;
import com.app.goaheadapp.models.Rate;
import com.app.goaheadapp.models.RateResponse;
import com.app.goaheadapp.models.User;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;


public class ListDriverRateFragment extends Fragment {

    RateViewModel rateViewModel;
    FragmentListDriverRateBinding binding;
    View view;
    List<Rate> rates;
    RateDriverAdapter rateDriverAdapter;

    public ListDriverRateFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rateViewModel = new ViewModelProvider(this).get(RateViewModel.class);
        rates = new ArrayList<>();
        rateDriverAdapter = new RateDriverAdapter(getContext(),rates);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_list_driver_rate, container, false);
        view = binding.getRoot();
        return view;
//        return inflater.inflate(R.layout.fragment_list_driver_rate, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        User user = Paper.book().read("data");
        binding.setUser(user);
        binding.rateList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rateList.setAdapter(rateDriverAdapter);

        rateViewModel.postsMutableLiveData.removeObservers(getViewLifecycleOwner());
        rateViewModel.getRate(getContext());
        rateViewModel.postsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<RateResponse>() {
            @Override
            public void onChanged(RateResponse rateResponse) {
                rateDriverAdapter.addMore(rateResponse.getItems());
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
        textView.setText("Rate");
        cart.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);
    }
}