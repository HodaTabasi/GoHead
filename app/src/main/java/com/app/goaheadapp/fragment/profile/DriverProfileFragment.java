package com.app.goaheadapp.fragment.profile;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.goaheadapp.HomeViewModel;
import com.app.goaheadapp.R;
import com.app.goaheadapp.databinding.FragmentDriverProfileBinding;
import com.app.goaheadapp.models.SignUpResponse;
import com.app.goaheadapp.models.UpdateImageResponse;

import java.io.File;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class DriverProfileFragment extends Fragment {

    private File mSaveBit;
    MultipartBody.Part body;
    RequestBody requestFile;

    public DriverProfileFragment() {
        // Required empty public constructor
    }

    FragmentDriverProfileBinding binding;
    ProfileViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_driver_profile, container, false);
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setTransfer(new HomeViewModel(getContext()));

        viewModel = new ViewModelProvider(getActivity()).get(ProfileViewModel.class);
        binding.setMymodel(viewModel);
        binding.langSwitch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1){
                    Toast.makeText(getContext(), "ar", Toast.LENGTH_SHORT).show();
                    Locale locale = new Locale("ar");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
                    restartActivity();
                }else if (i == 2){
                    Toast.makeText(getContext(), "en", Toast.LENGTH_SHORT).show();
                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
                    restartActivity();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        getData();
        binding.chooesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();

// MultipartBody.Part is used to send also the actual file name
            }
        });

        viewModel.updateImageMutableLiveData.removeObservers(getViewLifecycleOwner());
        viewModel.updateImageMutableLiveData.observe(getViewLifecycleOwner(), new Observer<UpdateImageResponse>() {
            @Override
            public void onChanged(UpdateImageResponse updateImageResponse) {
                binding.profileImage.setImageURI(Uri.parse(mSaveBit.getAbsolutePath()));
                binding.progressbar.setVisibility(View.GONE);
            }
        });
    }

    private void getData() {
        viewModel.getProfile(getActivity());
        viewModel.postsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<SignUpResponse>() {
            @Override
            public void onChanged(SignUpResponse signUpResponse) {
                binding.setItem(signUpResponse.getItems());
            }
        });
    }


    private void restartActivity() {
        Intent intent = getActivity().getIntent();
        getActivity().finish();
        startActivity(intent);
    }

    public void showDialog() {
        final Dialog dialog = new Dialog(getContext(), R.style.mydialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dialog_image_select_type);

        AppCompatTextView gallery = dialog.findViewById(R.id.gallery);
        AppCompatTextView camera = dialog.findViewById(R.id.camera);

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                EasyImage.openGallery(DriverProfileFragment.this, 1);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                EasyImage.openCamera(DriverProfileFragment.this, 0);
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
                Log.d("error handling", "onImagePickerError");
            }

            @Override
            public void onImagesPicked(final List<File> imagesFiles, EasyImage.ImageSource source, int type) {
                //Handle the images
//                localMemoryObject.setImage(imagesFiles.get(0).getAbsolutePath());

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSaveBit = imagesFiles.get(0); // Your image file
                        String filePath = mSaveBit.getPath();

                        binding.progressbar.setVisibility(View.VISIBLE);

                        requestFile =
                                RequestBody.create(MediaType.parse("file/*"), mSaveBit);
                        body =
                                MultipartBody.Part.createFormData("image", mSaveBit.getName(), requestFile);

                        viewModel.updateProfileImage(getContext(),body);
                    }
                }, 70);
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
        textView.setText("Profile");
        cart.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);
    }
}