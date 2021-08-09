package com.app.goaheadapp.fragment.setting;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.goaheadapp.BaseActivity;
import com.app.goaheadapp.R;
import com.app.goaheadapp.Utils.AppShareMethods;
import com.app.goaheadapp.databinding.FragmentShareAppBinding;
import com.app.goaheadapp.models.AboutUs;
import com.app.goaheadapp.models.SetiingResponse;

public class ShareAppFragment extends Fragment {


    FragmentShareAppBinding binding;
    private SettingViewModel viewModel;
    AboutUs aboutUs;

    public ShareAppFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_share_app, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(SettingViewModel.class);
        binding.setMymodel(viewModel);
        loadData();

        binding.face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url2 = aboutUs.getFacebook();// = ToolUtils.getLinkSocial(lookUpBean, typeSocial);
                if (url2 != null && !url2.isEmpty()) {
                    AppShareMethods.newFacebookIntent(getActivity(), getActivity().getPackageManager(), url2);
                }
                //toSocialMedia(aboutUs.getFacebook());
            }
        });
        binding.insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url2 = aboutUs.getInstagram();// = ToolUtils.getLinkSocial(lookUpBean, typeSocial);
                if (url2 != null && !url2.isEmpty()) {
                    AppShareMethods.newInstagramIntent(getActivity(), getActivity().getPackageManager(), url2);
                }
                //toSocialMedia(aboutUs.getInstagram());
            }
        });
        binding.tweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url2 = aboutUs.getInstagram();// = ToolUtils.getLinkSocial(lookUpBean, typeSocial);
                if (url2 != null && !url2.isEmpty()) {
                    AppShareMethods.newTwitterIntent(getActivity(), getActivity().getPackageManager(), url2);
                }
                //toSocialMedia(aboutUs.getTwitter());
            }
        });
        binding.shareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppShareMethods.copyAppLink(getActivity(), aboutUs.getPlay_store_url());
            }
        });
        binding.reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void loadData() {
        if (BaseActivity.isConnected(getContext())) {
            binding.content.setVisibility(View.VISIBLE);
            binding.reload.setVisibility(View.GONE);
            getData();
        } else {
            binding.content.setVisibility(View.GONE);
            binding.reload.setVisibility(View.VISIBLE);
        }
    }


    private void getData() {
        viewModel.getAbout(getActivity());
        viewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<SetiingResponse>() {
            @Override
            public void onChanged(SetiingResponse setiingResponse) {
                if (setiingResponse.isStatus()) {
                    aboutUs = setiingResponse.getItems();
                    binding.setItem(aboutUs);
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
        textView.setText(R.string.share);
        cart.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}