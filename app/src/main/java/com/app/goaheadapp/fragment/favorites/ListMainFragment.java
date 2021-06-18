package com.app.goaheadapp.fragment.favorites;

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

import com.app.goaheadapp.R;
import com.app.goaheadapp.adapters.FavListAdapter;
import com.app.goaheadapp.databinding.FragmentListMainBinding;
import com.app.goaheadapp.models.FavoriteResponse;


public class ListMainFragment extends Fragment {


    FragmentListMainBinding binding;
    View view;
    private FavoritesViewModel viewModel;
    private FavListAdapter adapter;

    public ListMainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_list_main, container, false);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(FavoritesViewModel.class);
        binding.setMymodel(viewModel);
        binding.resc.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        Bundle bundle = getArguments();
        if (bundle.getBoolean("isnote")){

        }else {
            getFav();
        }

    }

    private void getFav() {
        viewModel.getFav(getContext());
        viewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<FavoriteResponse>() {
            @Override
            public void onChanged(FavoriteResponse favoriteResponse) {
                if (favoriteResponse.isStatus()) {
                    adapter = new FavListAdapter(getActivity(),favoriteResponse.getItems());
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
        textView.setText("Favorite");
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