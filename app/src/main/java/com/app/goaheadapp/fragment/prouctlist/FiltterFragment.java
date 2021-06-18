package com.app.goaheadapp.fragment.prouctlist;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RatingBar;

import com.app.goaheadapp.R;
import com.app.goaheadapp.adapters.SubCatListAdapter;
import com.app.goaheadapp.databinding.FragmentFiltterBinding;
import com.app.goaheadapp.models.SearchResponse;

import java.util.HashMap;


public class FiltterFragment extends Fragment {

    FragmentFiltterBinding binding;
    View view;
    ProductViewModel viewModel;
    SubCatListAdapter adapter;
    HashMap<String, String> map;
    String name;

    public FiltterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_filtter, container, false);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        map = new HashMap<String, String>();

        viewModel = new ViewModelProvider(getActivity()).get(ProductViewModel.class);
        binding.setMymodel(viewModel);

        binding.resc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        Bundle bundle = getArguments();
        name = bundle.getString("data");
        putData();

        binding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpWindow(view);
            }
        });
    }

    private void popUpWindow(View view) {
        View mView = getLayoutInflater().inflate(R.layout.dialog_filtter, null);
//        final View mView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_filtter, null, false);
//        final PopupWindow popUp = new PopupWindow(mView, LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT, false);
//        int width = (int) (getActivity().getWindowManager().getDefaultDisplay().getWidth() * 0.35);
        final PopupWindow popUp = new PopupWindow(mView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popUp.setTouchable(true);
//        popUp.setFocusable(true);
        popUp.setOutsideTouchable(true);
        popUp.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));


        RadioGroup distance = mView.findViewById(R.id.distance);
        RadioGroup add_date = mView.findViewById(R.id.add_date);
        AppCompatButton search = mView.findViewById(R.id.search);
        AppCompatCheckBox elect = mView.findViewById(R.id.elect);
        AppCompatCheckBox upon = mView.findViewById(R.id.upon);
        RatingBar ratingBar = mView.findViewById(R.id.ratingBar);


        distance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.closest:
                        map.put("distance", "1");
                        break;
                    case R.id.furthest:
                        map.put("distance", "0");
                        break;
                }
            }
        });

        add_date.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.oldest:
                        map.put("adding_date", "0");
                        break;
                    case R.id.latest:
                        map.put("adding_date", "1");
                        break;
                }
            }
        });
        upon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    map.put("payment_methods", "0");
                }
            }
        });

        elect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    map.put("payment_methods", "1");
                }
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rate, boolean b) {
                map.put("rate", "" + rate);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putData();
                popUp.dismiss();
            }
        });

        //Solution
        popUp.showAsDropDown(view);
    }

    private void putData() {
        map.put("name", name);
        viewModel.search(map, getActivity());
        viewModel.searchMutableLiveData.observe(getViewLifecycleOwner(), new Observer<SearchResponse>() {
            @Override
            public void onChanged(SearchResponse searchResponse) {
                if (searchResponse.isStatus()) {
                    adapter = new SubCatListAdapter(getActivity(), searchResponse.getItems(), "0");
                    binding.resc.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}