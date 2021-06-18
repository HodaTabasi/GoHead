package com.app.goaheadapp.fragment.drivertrak;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.goaheadapp.R;
import com.app.goaheadapp.Utils.FragmentsUtil;
import com.app.goaheadapp.Utils.Permissions;
import com.app.goaheadapp.adapters.OrderTrakingStoreAdapter;
import com.app.goaheadapp.databinding.FragmentOrderDriverTrakingBinding;
import com.app.goaheadapp.fragment.ratedriver.ListDriverRateFragment;
import com.app.goaheadapp.interfaces.GetOrderDetails;
import com.app.goaheadapp.models.AddSuccessfullyResponse;
import com.app.goaheadapp.models.Order;
import com.app.goaheadapp.models.OrderResponse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DirectionsResult;

import java.util.ArrayList;
import java.util.List;


public class OrderDriverTrakingFragment extends Fragment implements OnMapReadyCallback, GetOrderDetails {

    FragmentOrderDriverTrakingBinding binding;
    private DriverViewModel viewModel;

    private GoogleMap map;
    SupportMapFragment mapFragment;
    private static final float DEFAULT_ZOOM = 8f;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private boolean mLocationPermissionGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private GeoApiContext mGeoApiContext;
    private Marker marker;
    private LatLng mUserPosition;


    OrderTrakingStoreAdapter adapter;
    List<Order> orders;
    String id = "1";
    Permissions permissions;
    private Order order;

    public OrderDriverTrakingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orders = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(DriverViewModel.class);
        adapter = new OrderTrakingStoreAdapter(getContext(), orders, this);
        permissions = new Permissions();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_driver_traking, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setMymodel(viewModel);
        binding.resc.setAdapter(adapter);

        getLocationPermission();

        if (!permissions.isCallPhoneOk(getContext()))
            permissions.requestCallPhone(getActivity());

        getData(id);

        viewModel.postsMutableLiveData.removeObservers(getViewLifecycleOwner());
        viewModel.postsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<OrderResponse>() {
            @Override
            public void onChanged(OrderResponse orderResponse) {
                adapter.addMore(orderResponse.getItems());
                if (!orderResponse.getItems().isEmpty())
                    putData(orderResponse.getItems().get(0));
            }
        });

        viewModel.postsMutableLiveData1.removeObservers(getViewLifecycleOwner());
        viewModel.postsMutableLiveData1.observe(getViewLifecycleOwner(), new Observer<AddSuccessfullyResponse>() {
            @Override
            public void onChanged(AddSuccessfullyResponse addSuccessfullyResponse) {
                if (addSuccessfullyResponse.isStatus()) {
                    Toast.makeText(getContext(), "" + addSuccessfullyResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.resc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        binding.resent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.resent.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shap_tab_select));
                binding.resent.setTextColor(Color.WHITE);

                binding.cancel.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shap_tab_defult));
                binding.cancel.setTextColor(Color.BLACK);

                binding.itemResentDriverOrder.layoutResent.setVisibility(View.VISIBLE);
                binding.itemOrderDriverOld.layoutOld.setVisibility(View.GONE);
                binding.received.setVisibility(View.VISIBLE);
                adapter.clear();
                id = "1";
                getData(id);


            }
        });

        binding.cancel.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                binding.cancel.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shap_tab_defult_blue));
                binding.cancel.setTextColor(Color.WHITE);

                binding.resent.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shap_tab_select_white));
                binding.resent.setTextColor(Color.BLACK);

                binding.itemResentDriverOrder.layoutResent.setVisibility(View.GONE);
                binding.itemOrderDriverOld.layoutOld.setVisibility(View.VISIBLE);
                binding.received.setVisibility(View.GONE);
                adapter.clear();
                id = "0";
                getData(id);
            }
        });

        binding.itemResentDriverOrder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call();
            }
        });

        binding.itemOrderDriverOld.rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentsUtil.replaceFragment(getActivity(), R.id.my_container, new ListDriverRateFragment(),true);
            }
        });

        binding.itemResentDriverOrder.sendNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getContext(), R.style.mydialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_send_note);

                ConstraintLayout constraintLayout = dialog.findViewById(R.id.item);
                constraintLayout.setVisibility(View.GONE);

                final AppCompatEditText note = dialog.findViewById(R.id.note);
                final AppCompatTextView call = dialog.findViewById(R.id.call);

                AppCompatButton close = dialog.findViewById(R.id.cancel);
                AppCompatButton ok = dialog.findViewById(R.id.send);

                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        call();
                    }
                });

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewModel.sendMessage(getActivity(), String.valueOf(order.getUser_id()), String.valueOf(order.getId()), note.getText().toString());
                        dialog.dismiss();
                    }
                });

                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

//                Bundle bundle = new Bundle();
//                bundle.putParcelable("object",order);
//                bundle.putInt("flag",1);
//                ChatFragment fragment = new ChatFragment();
//                fragment.setArguments(bundle);
//                FragmentsUtil.replaceFragment(getActivity(), R.id.my_container, fragment,true);
            }
        });
    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);

        intent.setData(Uri.parse("tel:" + binding.itemResentDriverOrder.driverNumber));
        startActivity(intent);
    }

    private void putData(Order order) {
        if (id.equals("1")) {
            binding.itemResentDriverOrder.setOrder(order);
        } else {
            binding.itemOrderDriverOld.setOrder(order);
        }
        marker = map.addMarker(new MarkerOptions()
                .position(new LatLng(order.getDriver().getLat(), order.getDriver().getLan()))
                .title("تفاصيل الطلب")
                .draggable(true));

        moveCamera(new LatLng(order.getDriver().getLat(), order.getDriver().getLan()), 5);
    }

    private void initMAP() {
//        myContext.getChildFragmentManager();
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (mGeoApiContext == null) {
            mGeoApiContext = new GeoApiContext.Builder()
                    .apiKey(getString(R.string.google_maps_key))
                    .build();
        }
    }

    private void getData(String id) {
        viewModel.getOrder(id, getActivity());
    }

    private void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        try {
            final Task location = mFusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Location current_location = (Location) task.getResult();
                        if (current_location != null)
                            Log.e("fffffffff", current_location.getLatitude() + " : " + current_location.getLongitude() + " ffff ");
                        else
                            Log.e("fffffffff", " fffffdpkhd");
                        try {
                            mUserPosition = new LatLng(current_location.getLatitude(), current_location.getLongitude());

                            Log.e("fffffffff", mUserPosition.latitude + " : " + mUserPosition.longitude + " ffff ");

                            marker = map.addMarker(new MarkerOptions()
                                    .position(mUserPosition)
                                    .title("place")
                                    .draggable(true));

                            moveCamera(mUserPosition, DEFAULT_ZOOM);

                        } catch (Exception e) {
                        }
                    } else {
                        Toast.makeText(getContext(), "unable to get current location", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } catch (SecurityException e) {
            Log.e("error", e.getMessage());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = ((AppCompatActivity) getActivity()).findViewById(R.id.toolbar_main);
        AppCompatTextView textView = view.findViewById(R.id.page_title);
        AppCompatImageView clear = view.findViewById(R.id.clear);
        AppCompatImageView cart = view.findViewById(R.id.cart);
        AppCompatImageView back = view.findViewById(R.id.back);
        textView.setText("Orders");
        cart.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
    }

    private void moveCamera(LatLng latLng, float zoom) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        if (mLocationPermissionGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            map.setMyLocationEnabled(false); //to get blue marker with GPS icon
            map.getUiSettings().setMyLocationButtonEnabled(false); //to hide GPS icon

            // Assem
//            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                @Override
//                public void onMapClick(LatLng latLng) {
//                    if (marker != null) { //if marker exists (not null or whatever)
//                        marker.setPosition(latLng);
//                        calculateDirections(marker);
//                    } else {
//                        marker = map.addMarker(new MarkerOptions()
//                                .position(latLng)
//                                .title("place")
//                                .draggable(true));
//
//                        mUserPosition = latLng;
//                    }
//                }
//            });
        }
    }

    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMAP();
            } else {
                ActivityCompat.requestPermissions(getActivity(), permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(getActivity(), permissions, LOCATION_PERMISSION_REQUEST_CODE);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionGranted = true;
                    //initialize our map
                    initMAP();
                }
            }

        }
    }

    private void calculateDirections(Marker marker) {
        Log.d("TAG", "calculateDirections: calculating directions.");

        com.google.maps.model.LatLng destination = new com.google.maps.model.LatLng(
                marker.getPosition().latitude,
                marker.getPosition().longitude
        );
        DirectionsApiRequest directions = new DirectionsApiRequest(mGeoApiContext);

        directions.alternatives(true);
        directions.origin(
                new com.google.maps.model.LatLng(
                        mUserPosition.latitude,
                        mUserPosition.longitude
                )
        );
        Log.d("TAG", "calculateDirections: destination: " + destination.toString());
        directions.destination(destination).setCallback(new PendingResult.Callback<DirectionsResult>() {
            @Override
            public void onResult(DirectionsResult result) {
                Log.d("TAG", "onResult: routes: " + result.routes[0].toString());
                Log.d("TAG", "onResult: geocodedWayPoints: " + result.geocodedWaypoints[0].toString());
            }

            @Override
            public void onFailure(Throwable e) {
                Log.e("TAG", "onFailure: " + e.getMessage());

            }
        });
    }

    @Override
    public void getData(Order order) {
        this.order = order;
        putData(order);
    }
}