package com.app.goaheadapp.activitys;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.app.goaheadapp.R;
import com.app.goaheadapp.activitys.savedaddresses.SavedAddressViewModel;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddAddressActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
//    private static int AUTOCOMPLETE_REQUEST_CODE = 1;

    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final float DEFAULT_ZOOM = 8f;
    private boolean mLocationPermissionGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Marker marker;
    /**
     * madina monoara and one
     */
    private AppCompatTextView mTheAddress;
    private ListView mListView;
    AppCompatEditText searchView;
    com.app.goaheadapp.models.Address myAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initView();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        mapFragment.getMapAsync(this);

        getLocationPermission();

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 0) {
                    mListView.setVisibility(View.GONE);
                } else {
                    mListView.setVisibility(View.VISIBLE);
                    List<Address> addresses = getLocationFromAddress(AddAddressActivity.this, s);
                    putToAdapter(addresses);
                }
            }

            private void putToAdapter(List<Address> addresses) {
                List<String> address = new ArrayList<>();
                for (Address address1 : addresses) {
                    address.add(address1.getCountryName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddAddressActivity.this, android.R.layout.simple_list_item_1, address);
                mListView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LatLng latLng = getLocationFromAddress(AddAddressActivity.this, String.valueOf(mListView.getItemAtPosition(i)));

                if (marker != null) { //if marker exists (not null or whatever)
                    marker.setPosition(latLng);
                } else {
                    marker = mMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("place")
                            .draggable(true));
                }
                moveCamera(new LatLng(latLng.latitude, latLng.longitude), DEFAULT_ZOOM);
                mListView.setVisibility(View.GONE);
            }
        });

//        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int keyCode, KeyEvent keyEvent) {
//
//                if (keyCode == EditorInfo.IME_ACTION_DONE ||
//                        keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
//                                keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
//                    // Perform action on key press
//
//                    LatLng latLng = getLocationFromAddress(AddAddressActivity.this, searchView.getText().toString());
//
//                    if (marker != null) { //if marker exists (not null or whatever)
//                        marker.setPosition(latLng);
//                    } else {
//                        marker = mMap.addMarker(new MarkerOptions()
//                                .position(latLng)
//                                .title(searchView.getText().toString())
//                                .draggable(true));
//                    }
//                    moveCamera(new LatLng(latLng.latitude, latLng.longitude), DEFAULT_ZOOM);
//                    return true;
//                }
//                return false;
//
//            }
//        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mLocationPermissionGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true); //to get blue marker with GPS icon
            mMap.getUiSettings().setMyLocationButtonEnabled(false); //to hide GPS icon

            // Assem
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    if (marker != null) { //if marker exists (not null or whatever)
                        marker.setPosition(latLng);
                    } else {
                        marker = mMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title("place")
                                .draggable(true));
                    }
                    String address = getMyCountry(latLng.latitude, latLng.longitude);
                    myAddress.setLan(latLng.longitude+"");
                    myAddress.setLat(latLng.latitude+"");
                    myAddress.setAddress(address);

                    mTheAddress.setText(address);
                    Toast.makeText(AddAddressActivity.this, "" + address, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void initMAP() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(AddAddressActivity.this);
    }

    private void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocationPermissionGranted) {
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Location current_location = (Location) task.getResult();

                            try {
                                moveCamera(new LatLng(current_location.getLatitude(), current_location.getLongitude()), DEFAULT_ZOOM);

                            } catch (Exception e) {
                                onBackPressed();
                                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                Toast.makeText(AddAddressActivity.this, "الرجاء قم بإعطاء الصلاحيات للموقع", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(AddAddressActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Log.e("rrrrrr", "ffff");

            }
        } catch (SecurityException e) {
            Log.e("error", e.getMessage());
        }
    }

    private void moveCamera(LatLng latLng, float zoom) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMAP();
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);

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

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        // May throw an IOException
        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return p1;
    }

    public List<Address> getLocationFromAddress(Context context, CharSequence strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address = new ArrayList<>();

        // May throw an IOException
        try {
            address = coder.getFromLocationName(String.valueOf(strAddress), 8);
            if (address == null) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;
    }

    public String getMyCountry(double latitude, double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            if (addresses == null || addresses.isEmpty()) {
                return "Unknown Address";
            }
            if (addresses.get(0) == null) {
                return "Unknown Address";
            }
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

            if (address != null) {
                return address;
            }
            if (city != null) {
                return city;
            }
            if (state != null) {
                return state;
            }
            if (country != null) {
                return country;
            }
            return "Unknown Address";
        } catch (IOException e) {
            return "Unknown Address";
        }
    }

    private void initView() {
        mTheAddress = (AppCompatTextView) findViewById(R.id.the_address);
        mListView = (ListView) findViewById(R.id.list_view);
        searchView = findViewById(R.id.search);
        myAddress = new com.app.goaheadapp.models.Address();
    }

    public void ClickSave(View view){
        if (!myAddress.getAddress().isEmpty()){
            SavedAddressViewModel viewModel = new ViewModelProvider(this).get(SavedAddressViewModel.class);
            viewModel.addAddress(myAddress ,this);
        }
    }
}

//    String apiKey = getResources().getString(R.string.google_maps_key);
//
///**
// * Initialize Places. For simplicity, the API key is hard-coded. In a production
// * environment we recommend using a secure mechanism to manage API keys.
// */
//        if (!Places.isInitialized()) {
//                Places.initialize(getApplicationContext(), apiKey);
//                }
//
//                PlacesClient placesClient = Places.createClient(this);
//
//                searchView.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);
//
//        // Start the autocomplete intent.
//        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields)
//        .setTypeFilter(TypeFilter.GEOCODE)
//        .build(AddAddressActivity.this);
//        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
//        }
//        });
//
//@Override
//protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
//        if (resultCode == RESULT_OK) {
//        Place place = Autocomplete.getPlaceFromIntent(data);
//        Log.e("TAG", "Place: " + place.getName() + ", " + place.getId());
//        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
//        // TODO: Handle the error.
//        Status status = Autocomplete.getStatusFromIntent(data);
//        Log.e("TAG", status.getStatusMessage());
//        } else if (resultCode == RESULT_CANCELED) {
//        // The user canceled the operation.
//        }
//        return;
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//        }