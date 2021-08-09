package com.app.goaheadapp.fragment.rateproduct;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.app.goaheadapp.HomeViewModel;
import com.app.goaheadapp.R;
import com.app.goaheadapp.databinding.FragmentRateProductBinding;
import com.app.goaheadapp.models.Product;
import com.app.goaheadapp.models.SubCategory;


public class RateProductFragment extends Fragment {


    FragmentRateProductBinding binding;
    View view;
    RateProductViewModel viewModel;
    private HomeViewModel homeViewModel;

    public RateProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_rate_product, container, false);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SubCategory subCategory = getArguments().getParcelable("cat");
        Product product = getArguments().getParcelable("object");

        binding.setProduct(product);
        binding.setSubcat(subCategory);

        viewModel = new ViewModelProvider(this).get(RateProductViewModel.class);
        homeViewModel = new HomeViewModel(getContext());
        binding.setMymodel(viewModel);

        binding.rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.Rate(binding.getProduct().getId() + "", binding.ratingBarr.getRating() + "", binding.text.getText().toString(), getContext());
            }
        });

        binding.fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    homeViewModel.setFavoriteStore(getContext(), subCategory.getId() + "");
                } else {
                    homeViewModel.removeFavoriteStore(getContext(), subCategory.getId() + "");
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
        textView.setText("Product Rating");
        cart.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);

        final NavController navController = Navigation.findNavController((Activity) getActivity(), R.id.nav_host_fragment);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });
    }
}