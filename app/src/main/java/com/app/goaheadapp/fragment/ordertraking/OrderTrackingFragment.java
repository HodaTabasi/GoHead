package com.app.goaheadapp.fragment.ordertraking;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
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
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.app.goaheadapp.Utils.Permissions;
import com.app.goaheadapp.adapters.OrderTrakingStoreAdapter;
import com.app.goaheadapp.databinding.FragmentOrderTrackingBinding;
import com.app.goaheadapp.dialog.ProductDetails;
import com.app.goaheadapp.interfaces.GetOrderDetails;
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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OrderTrackingFragment extends Fragment implements OnMapReadyCallback, GetOrderDetails {

    FragmentOrderTrackingBinding binding;
    OrderTrackingViewModel viewModel;

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
    private ArrayList<Order> orders;
    String id = "0";
    Permissions permissions;
    private Order order;

    public OrderTrackingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orders = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(OrderTrackingViewModel.class);
        adapter = new OrderTrakingStoreAdapter(getContext(), orders, this);
        permissions = new Permissions();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_order_tracking, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.setMymodel(viewModel);
        binding.itemOrderResent.setTransfer(new OrderViewModel(getContext()));
        binding.itemOrderOld.setTransfer(new OrderViewModel(getContext()));

        binding.resc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.resc.setAdapter(adapter);

        getLocationPermission();
        if (!permissions.isCallPhoneOk(getContext()))
            permissions.requestCallPhone(getActivity());

        LoadData();
//        getData(id);

        viewModel.postsMutableLiveData.removeObservers(getViewLifecycleOwner());
        viewModel.postsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<OrderResponse>() {
            @Override
            public void onChanged(OrderResponse orderResponse) {
                adapter.addMore(orderResponse.getItems());
                if (!orderResponse.getItems().isEmpty()) {
                    binding.cons.setVisibility(View.VISIBLE);
                    binding.resc.setVisibility(View.VISIBLE);
                    putData(orderResponse.getItems().get(0));
                } else {
                    binding.cons.setVisibility(View.GONE);
                    binding.resc.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
        });


        binding.resent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.resent.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shap_tab_select));
                binding.resent.setTextColor(Color.WHITE);

                binding.cancel.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shap_tab_defult));
                binding.cancel.setTextColor(Color.BLACK);

                binding.end.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_midile_tab_selector));
                binding.end.setTextColor(Color.BLACK);
                id = "0";
//                getData(id);

                LoadData();

                binding.itemOrderResent.resents.setVisibility(View.VISIBLE);
                binding.itemOrderResent.secondPart.setVisibility(View.VISIBLE);
                binding.itemOrderOld.olders.setVisibility(View.GONE);
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

                binding.end.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_midile_tab_selector));
                binding.end.setTextColor(Color.BLACK);

                id = "2";
//                getData(id);
                LoadData();
                binding.itemOrderResent.resents.setVisibility(View.VISIBLE);
                binding.itemOrderResent.secondPart.setVisibility(View.GONE);
                binding.itemOrderOld.olders.setVisibility(View.GONE);
            }
        });

        binding.end.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                binding.resent.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shap_tab_select_white));
                binding.resent.setTextColor(Color.BLACK);

                binding.cancel.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shap_tab_defult));
                binding.cancel.setTextColor(Color.BLACK);

                binding.end.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                binding.end.setTextColor(Color.WHITE);

                id = "1";
//                getData(id);
                LoadData();
                binding.itemOrderOld.olders.setVisibility(View.VISIBLE);
                binding.itemOrderResent.resents.setVisibility(View.GONE);
            }
        });

        binding.itemOrderResent.call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!permissions.isCallPhoneOk(getContext()))
                    permissions.requestCallPhone(getActivity());
                else
                    call(order.getMobile());
            }
        });

        binding.itemOrderResent.call1Drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!permissions.isCallPhoneOk(getContext()))
                    permissions.requestCallPhone(getActivity());
                else
                    call(binding.itemOrderResent.driverNumber.getText().toString());
            }
        });

        binding.itemOrderOld.call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!permissions.isCallPhoneOk(getContext()))
                    permissions.requestCallPhone(getActivity());
                else
                    call(order.getMobile());
            }
        });

        binding.itemOrderOld.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!permissions.isCallPhoneOk(getContext()))
                    permissions.requestCallPhone(getActivity());
                else
                    call(binding.itemOrderResent.driverNumber.getText().toString());
            }
        });
        binding.reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadData();
            }
        });
    }

    public void LoadData() {
        if (BaseActivity.isConnected(getContext())) {
            binding.content.setVisibility(View.VISIBLE);
            binding.reload.setVisibility(View.GONE);
            getData(id);
        } else {
            binding.content.setVisibility(View.GONE);
            binding.reload.setVisibility(View.VISIBLE);
        }
    }

    private void call(String mobile) {
        Intent intent = new Intent(Intent.ACTION_CALL);

        intent.setData(Uri.parse("tel:" + mobile));
        startActivity(intent);
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
        textView.setText(R.string.my_orders);
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
            map.setMyLocationEnabled(true); //to get blue marker with GPS icon
            map.getUiSettings().setMyLocationButtonEnabled(false); //to hide GPS icon

//            // Assem
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

    private void putData(Order order) {
        if (id.equals("0")) {
            binding.itemOrderResent.setOrder(order);
        } else if (id.equals("1")) {
            binding.itemOrderOld.setOrder(order);
        } else if (id.equals("2")) {
            binding.itemOrderResent.setOrder(order);
        }
        if (order.getLat() != null && order.getLan() != null) {
            marker = map.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(order.getLat()), Double.parseDouble(order.getLan())))
                    .title("تفاصيل الطلب")
                    .draggable(true));

            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull @NotNull Marker marker) {

                    ProductDetails customDialog = new ProductDetails(getContext(), R.style.mydialog, order);
                    customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    customDialog.show();
                    Window window = customDialog.getWindow();
                    window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                    return false;
                }
            });

            moveCamera(new LatLng(Double.parseDouble(order.getLat()), Double.parseDouble(order.getLan())), 5);
        }

    }
}