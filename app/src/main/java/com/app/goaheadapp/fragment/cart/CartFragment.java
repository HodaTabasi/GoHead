package com.app.goaheadapp.fragment.cart;

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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.goaheadapp.HomeViewModel;
import com.app.goaheadapp.R;
import com.app.goaheadapp.interfaces.TotalInterface;
import com.app.goaheadapp.Utils.SwipeToDeleteCallback;
import com.app.goaheadapp.adapters.CartAdapter;
import com.app.goaheadapp.databinding.FragmentCartBinding;
import com.app.goaheadapp.models.AddSuccessfullyResponse;
import com.app.goaheadapp.models.CartResponse;


public class CartFragment extends Fragment {


    private CartViewModel viewModel;
    private CartAdapter adapter;

    public CartFragment() {
        // Required empty public constructor
    }

    FragmentCartBinding binding;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_cart, container, false);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setTransfer(new HomeViewModel(getContext()));

        viewModel = new ViewModelProvider(getActivity()).get(CartViewModel.class);
        binding.setMymodel(viewModel);
        binding.resc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        viewModel.getCart(getContext());
        viewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<CartResponse>() {
            @Override
            public void onChanged(CartResponse cartResponse) {
                int sum = cartResponse.getItems().getTotal_price();
                adapter = new CartAdapter(getContext(), cartResponse.getItems().getCart(), totalInterface,sum);
                binding.resc.setAdapter(adapter);
                setUpRecyclerView();
                binding.price.setText(cartResponse.getItems().getTotal_price() + "");
                binding.shopping.setText(sum + "");
            }
        });
    }

    TotalInterface totalInterface = new TotalInterface() {
        @Override
        public void onPriceChange(int Value) {
            binding.shopping.setText(Value + "");
        }
    };

    private void setUpRecyclerView() {
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(binding.resc);
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = ((AppCompatActivity) getActivity()).findViewById(R.id.toolbar_main);
        AppCompatTextView textView = view.findViewById(R.id.page_title);
        AppCompatImageView clear = view.findViewById(R.id.clear);
        AppCompatImageView cart = view.findViewById(R.id.cart);
        AppCompatImageView back = view.findViewById(R.id.back);
        textView.setText("Cart");
        cart.setVisibility(View.GONE);
        clear.setVisibility(View.VISIBLE);
        back.setVisibility(View.VISIBLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController((Activity) getActivity(), R.id.nav_host_fragment);
                navController.popBackStack();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.clearAll(getContext());
                viewModel.clearMutableLiveData.observe(getViewLifecycleOwner(), new Observer<AddSuccessfullyResponse>() {
                    @Override
                    public void onChanged(AddSuccessfullyResponse addSuccessfullyResponse) {
                        if (addSuccessfullyResponse.isStatus())
                            Toast.makeText(getContext(), ""+addSuccessfullyResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}