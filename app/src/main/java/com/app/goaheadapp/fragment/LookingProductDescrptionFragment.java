package com.app.goaheadapp.fragment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.goaheadapp.R;
import com.app.goaheadapp.databinding.FragmentLookingProductDescrptionBinding;
import com.app.goaheadapp.models.SubCategory;

public class LookingProductDescrptionFragment extends Fragment {

    FragmentLookingProductDescrptionBinding binding;
    View view;

    public LookingProductDescrptionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_looking_product_descrption, container, false);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final SubCategory subCategory = getArguments().getParcelable("object");
        binding.setItem(subCategory);
        binding.browes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(subCategory.getWebsite()));
                    startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getContext(), "No application can handle this request."
                            + " Please install a webbrowser", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
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
        textView.setText("Place Description");
        cart.setVisibility(View.VISIBLE);
        clear.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);

        final NavController navController = Navigation.findNavController((Activity) getActivity(), R.id.nav_host_fragment);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.cartFragment);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });

    }
}