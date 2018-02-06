package com.example.kholis.smartparking.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.kholis.smartparking.R;
import com.example.kholis.smartparking.adapter.PlaceAutoComplateAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMaps extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {
    private static final int MY_LOCATION_REQUEST_CODE = 1;
    View view;
    GoogleApiClient mGoogleApiClient;
    GeoDataClient mGeoDataClient;
    GoogleMap mGMap;
    LatLng current, destination;
    SupportMapFragment supportMapFragment;
    PlaceDetectionClient mPlaceDetectionClient;
    FusedLocationProviderClient mFusedLocationProvider;
    PlaceAutocompleteFragment mPlaceAutocomplateFragmentCurr, mPlaceAutocomplateFragmentDess;

    @BindView(R.id.getParkLoct)
    Button getParkLoct;
    @BindView(R.id.inTmpParkir)
    AutoCompleteTextView inTmpParkir;
    @BindView(R.id.curLoct)
    AutoCompleteTextView curLoct;


    public FragmentMaps() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_maps, container, false);

        ButterKnife.bind(getActivity(), view);

        mGeoDataClient = Places.getGeoDataClient(getContext(), null);

        mPlaceDetectionClient = Places.getPlaceDetectionClient(getContext(), null);

        mFusedLocationProvider = LocationServices.getFusedLocationProviderClient(getContext());

        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(Places.GEO_DATA_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        MapsInitializer.initialize(getContext());
        mGoogleApiClient.connect();

        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapsFrag);
        supportMapFragment.getMapAsync(this);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public boolean onMyLocationButtonClick() {
        Snackbar.make(view, "Get your current location", Snackbar.LENGTH_SHORT);
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Snackbar.make(view, "Current location:\n" + location, Snackbar.LENGTH_SHORT);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mGMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
        }

        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMyLocationButtonClickListener(this);
        googleMap.setOnMyLocationClickListener(this);

        mPlaceAutocomplateFragmentCurr = (PlaceAutocompleteFragment) getActivity().getFragmentManager().findFragmentById(R.id.place_auto_complate_fragmentCurr);
        mPlaceAutocomplateFragmentCurr.setHint("Lokasi anda");
        mPlaceAutocomplateFragmentCurr.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                current = place.getLatLng();

            }

            @Override
            public void onError(Status status) {
                Snackbar.make(view, "Error connection", Snackbar.LENGTH_SHORT);
            }
        });

        mPlaceAutocomplateFragmentDess = (PlaceAutocompleteFragment) getActivity().getFragmentManager().findFragmentById(R.id.place_auto_complate_fragmentDess);
        mPlaceAutocomplateFragmentDess.setHint("Tujuan anda");
        mPlaceAutocomplateFragmentDess.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                destination = place.getLatLng();
            }

            @Override
            public void onError(Status status) {
                Snackbar.make(view, "Error connection", Snackbar.LENGTH_SHORT);

            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPlaceAutocomplateFragmentCurr.onActivityResult(requestCode, resultCode, data);
        mPlaceAutocomplateFragmentDess.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.getParkLoct)
    public void getParkLoct(View view){
        Bundle args = new Bundle();
        args.putParcelable("current", current);
        args.putParcelable("destination", destination);
        Intent i = new Intent(getActivity(), PilihMobilFragment.class);
        i.putExtra("bundle", args);
        startActivity(i);
    }
}
