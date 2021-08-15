package com.app.goaheadapp.fragment.choosesdriver;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.goaheadapp.BaseActivity;
import com.app.goaheadapp.OrderViewModel;
import com.app.goaheadapp.R;
import com.app.goaheadapp.Utils.MyProgressDialog;
import com.app.goaheadapp.databinding.FragmentChooseDriverBinding;
import com.app.goaheadapp.fragment.chat.ChatViewModel;
import com.app.goaheadapp.fragment.drivertrak.DriverViewModel;
import com.app.goaheadapp.models.AddSuccessfullyResponse;
import com.app.goaheadapp.models.DrivierResponse;
import com.app.goaheadapp.models.User;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChooseDriverFragment extends Fragment implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {
    FusedLocationProviderClient mFusedLocationClient;

    private ChooseDriverViewModel viewModel;
    FragmentChooseDriverBinding binding;
    int PERMISSION_ID = 44;
    private static final int REQUEST_LOCATION = 11;
    List<User> drivers = new ArrayList<>();
    GoogleMap googleMapBusey, googleMapOff, googleMapOn, map;
    LocationManager locationManager;
    Map<Marker, User> theMap;
    String orderId = "";
    String driverId;


    SupportMapFragment mapFragment;
    private float DEFAULT_ZOOM = 5f;

    public ChooseDriverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ChooseDriverViewModel.class);
        theMap = new HashMap<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_choose_driver, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        binding.itemDriverProfile.setTransfer(new OrderViewModel(getContext()));

        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        binding.setTransfer(new OrderViewModel(getContext()));

        binding.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.sendOrderToDriver(getContext(), orderId, driverId);
                viewModel.postsMutableLiveData1.observe(getViewLifecycleOwner(), new Observer<AddSuccessfullyResponse>() {
                    @Override
                    public void onChanged(AddSuccessfullyResponse addSuccessfullyResponse) {
                        if (addSuccessfullyResponse.isStatus()) {
                            doneDialog();
                        } else
                            Toast.makeText(getContext(), "" + addSuccessfullyResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
        binding.reload.setOnClickListener(v -> {
            loadData();
        });
    }

    public void doneDialog() {
        final Dialog dialog = new Dialog(getContext(), R.style.mydialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_ok);


        AppCompatTextView message = dialog.findViewById(R.id.message);

        message.setText(getActivity().getResources().getString(R.string.ask_driver_done));

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void setDriver(List<User> drivers) {
        if (map != null) {
            map.clear();
        }

        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getLan() != 0.0 &&
                    drivers.get(i).getLat() != 0.0) {

                LatLng latLng = new LatLng(drivers.get(i).getLat(), drivers.get(i).getLan());

                Marker m = map.addMarker(new MarkerOptions().position(latLng).title("off").icon(
                        bitmapDescriptorFromVector(getActivity(), R.drawable.ic_local_shipping_24px)
                ));

                theMap.put(m, drivers.get(i));

            }
        }
        if (drivers.size() != 0) {
            LatLng latLng = new LatLng(drivers.get(0).getLat(), drivers.get(0).getLan());
            moveCamera(new LatLng(latLng.latitude, latLng.longitude), DEFAULT_ZOOM);
        }
    }

    private void moveCamera(LatLng latLng, float zoom) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }


    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = ((AppCompatActivity) getActivity()).findViewById(R.id.toolbar_main);
        AppCompatTextView textView = view.findViewById(R.id.page_title);
        AppCompatImageView clear = view.findViewById(R.id.clear);
        AppCompatImageView cart = view.findViewById(R.id.cart);
        AppCompatImageView back = view.findViewById(R.id.back);
        textView.setText(R.string.ch_driver);
        cart.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();
//        if (mapFragment != null) {
//            mapFragment.getMapAsync(this);
//        }
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
//        Toast.makeText(getContext(), "ddddddddddddddd", Toast.LENGTH_SHORT).show();

        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                User yourCustomObjectInstance = theMap.get(marker);
                driverId = String.valueOf(yourCustomObjectInstance.getId());
                binding.profile.setVisibility(View.VISIBLE);
                binding.setUser(yourCustomObjectInstance);
//                Log.e("fffffffffff",yourCustomObjectInstance.getId()+" dd");
                return null;
            }

            @Nullable
            @Override
            public View getInfoContents(@NonNull Marker marker) {
                return null;
            }
        });
        return false;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        map = googleMap;
        Toast.makeText(getContext(), "ddddddddddddddd " + drivers.size(), Toast.LENGTH_SHORT).show();
        map.setOnMarkerClickListener(this);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        loadData();
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
        viewModel.getDrivers(getContext());
        viewModel.postsMutableLiveData.removeObservers(getViewLifecycleOwner());
        viewModel.postsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<DrivierResponse>() {
            @Override
            public void onChanged(DrivierResponse drivierResponse) {
                if (drivierResponse.isStatus()) {
                    drivers = drivierResponse.getItems();
                    binding.profile.setVisibility(View.GONE);
                    setDriver(drivers);
                }
            }
        });
    }

}