package com.app.goaheadapp.fragment.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.goaheadapp.HomeViewModel;
import com.app.goaheadapp.R;
import com.app.goaheadapp.adapters.CategoryAdapter;
import com.app.goaheadapp.databinding.FragmentHomeBinding;
import com.app.goaheadapp.models.CatResponse;


public class HomeFragment extends Fragment {

    View view;
    FragmentHomeBinding binding;
    private HomeFViewModel viewModel;
    private CategoryAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home, container, false);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setTransfer(new HomeViewModel(getContext()));

        binding.resc.setLayoutManager(new GridLayoutManager(getContext(),3));
        viewModel = new ViewModelProvider(getActivity()).get(HomeFViewModel.class);
        binding.setModels(viewModel);

        putData("1");

        binding.look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.look.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shap_tab_select));
                binding.look.setTextColor(Color.WHITE);

                binding.shopping.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shap_tab_defult));
                binding.shopping.setTextColor(Color.BLACK);

                binding.resc.setLayoutManager(new GridLayoutManager(getContext(),3));
                putData("1");

            }
        });

        binding.shopping.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                binding.shopping.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shap_tab_defult_blue));
                binding.shopping.setTextColor(Color.WHITE);

                binding.look.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shap_tab_select_white));
                binding.look.setTextColor(Color.BLACK);

                binding.resc.setLayoutManager(new GridLayoutManager(getContext(),2));
                putData("0");

            }
        });

    }

    private void putData(final String id) {
        viewModel.getCat(id,getContext());
        viewModel.mutableLiveData.observe(getActivity(), new Observer<CatResponse>() {
            @Override
            public void onChanged(CatResponse catResponse) {
                if (catResponse.isStatus()){
                    adapter = new CategoryAdapter(getContext(),catResponse.getItems(),id);
                    binding.resc.setAdapter(adapter);
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
        textView.setText("Home");
        cart.setVisibility(View.VISIBLE);
        clear.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController((Activity) getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.cartFragment);
            }
        });
    }
}