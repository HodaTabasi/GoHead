package com.app.goaheadapp.fragment.shopcontent;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.goaheadapp.HomeViewModel;
import com.app.goaheadapp.R;
import com.app.goaheadapp.adapters.ProductAdapter;
import com.app.goaheadapp.databinding.FragmentShopContentBinding;
import com.app.goaheadapp.models.ProductResponse;
import com.app.goaheadapp.models.StoreResponse;
import com.app.goaheadapp.models.SubCategory;
import com.google.android.material.tabs.TabLayout;


public class ShopContentFragment extends Fragment {


    private ShopContentViewModel viewModel;
    private ProductAdapter adapter;
    SubCategory subCategory;

    public ShopContentFragment() {
        // Required empty public constructor
    }


    View view;
    FragmentShopContentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_shop_content, container, false);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setTransfer(new HomeViewModel(getContext()));

        subCategory = getArguments().getParcelable("object");
        binding.setItem(subCategory);

        viewModel = new ViewModelProvider(getActivity()).get(ShopContentViewModel.class);
        binding.setMymodel(viewModel);

        binding.resc.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        getStoreCat(subCategory.getId());
        putData(subCategory.getId(),1);

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                StoreResponse.ItemsBean category = (StoreResponse.ItemsBean) tab.getTag();
                putData(category.getStore_id(),category.getId());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void putData(int id, int i) {
        viewModel.getProduct(id+"",i+"",getContext());
        viewModel.postsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<ProductResponse>() {
            @Override
            public void onChanged(ProductResponse productResponse) {
                adapter = new ProductAdapter(getActivity(),productResponse.getItems(),subCategory);
                binding.resc.setAdapter(adapter);
            }
        });
    }

    private void getStoreCat(int id) {
        viewModel.getStoreCat(id+"",getContext());
        viewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<StoreResponse>() {
            @Override
            public void onChanged(StoreResponse storeResponse) {
                binding.tabLayout.removeAllTabs();
                if (storeResponse.isStatus()){
                    for (StoreResponse.ItemsBean category: storeResponse.getItems()){
                            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(category.getTitle()).setTag(category),false);
                        }
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
        textView.setText("Name");
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