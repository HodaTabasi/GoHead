package com.app.goaheadapp.fragment.prouctlist;

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

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.goaheadapp.R;
import com.app.goaheadapp.adapters.SliderAdapter;
import com.app.goaheadapp.adapters.SubCatListAdapter;
import com.app.goaheadapp.databinding.FragmentListBinding;
import com.app.goaheadapp.models.AddsResponse;
import com.app.goaheadapp.models.CatResponse;
import com.app.goaheadapp.models.Category;
import com.app.goaheadapp.models.SubCatResponse;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

public class ListFragment extends Fragment {
    View view;
    FragmentListBinding binding;
    String title;
    ProductViewModel viewModel;
    SubCatListAdapter adapter;
    String cat_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_list, container, false);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        binding.setMymodel(viewModel);

        binding.resc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        Bundle bundle = getArguments();
        Category category = (Category) bundle.getParcelable("cat");
        cat_id = bundle.getString("cat_id");
        getCategory(cat_id, category);
        getAdds(String.valueOf(category.getId()));


        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Category category = (Category) tab.getTag();
                putData(category.getId() + "");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.search.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    Bundle bundle = new Bundle();
                    bundle.putString("data", binding.search.getText().toString());
                    navController.navigate(R.id.action_listFragment_to_filtterFragment, bundle);
                    binding.search.setFocusable(false);
                    return true;
                }
                return false;
            }
        });
    }

    private void getAdds(String cat_id) {
        viewModel.getAdds(cat_id, getContext());
        viewModel.addsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<AddsResponse>() {
            @Override
            public void onChanged(AddsResponse addsResponse) {
                if (addsResponse.isStatus()) {
//                    SliderView sliderView = findViewById(R.id.imageSlider);
                    binding.imageSlider.setSliderAdapter(new SliderAdapter(getContext(), addsResponse.getItems()));
                    binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
                    binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    binding.imageSlider.startAutoCycle();
                } else {
                    Toast.makeText(getContext(), "some thing wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getCategory(final String cat_id, final Category cat) {
        viewModel.getCat(cat_id, getContext());
        viewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<CatResponse>() {
            @Override
            public void onChanged(CatResponse catResponse) {
                binding.tabLayout.removeAllTabs();
                if (catResponse.isStatus()) {
                    for (Category category : catResponse.getItems()) {
                        if (cat.getId() == category.getId()) {
                            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(category.toString()).setTag(category), true);
                            putData(category.getId() + "");
                        } else {
                            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(category.toString()).setTag(category), false);
                        }

                    }
                }
            }
        });
    }

    private void putData(String id) {
        viewModel.getSubCat(id, getContext());
        viewModel.postsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<SubCatResponse>() {
            @Override
            public void onChanged(SubCatResponse subCatResponse) {
                if (subCatResponse.isStatus()) {
                    adapter = new SubCatListAdapter(getActivity(), subCatResponse.getItems(), cat_id);
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
        textView.setText(title);
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