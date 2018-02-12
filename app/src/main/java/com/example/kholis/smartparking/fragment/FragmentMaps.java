package com.example.kholis.smartparking.fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
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
import android.widget.Toast;

import com.example.kholis.smartparking.R;
import com.example.kholis.smartparking.adapter.PlaceAutoComplateAdapter;
import com.example.kholis.smartparking.helper.ApiUtils;
import com.example.kholis.smartparking.helper.BaseApiService;
import com.example.kholis.smartparking.helper.DirectionsParser;
import com.example.kholis.smartparking.helper.SharedPrefManager;
import com.example.kholis.smartparking.model.DataTempat;
import com.example.kholis.smartparking.model.ListTempatParkir;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMaps extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {
    private static final int MY_LOCATION_REQUEST_CODE = 500;
    View view;
    GoogleApiClient mGoogleApiClient;
    GeoDataClient mGeoDataClient;
    GoogleMap mGMap;
    ArrayList<LatLng> listPoint;
    LatLng current, destination, temp;
    SupportMapFragment supportMapFragment;
    PlaceDetectionClient mPlaceDetectionClient;
    FusedLocationProviderClient mFusedLocationProvider;
    PlaceAutocompleteFragment mPlaceAutocomplateFragmentCurr, mPlaceAutocomplateFragmentDess;

    private LocationRequest locationRequest;
    private long UPDATE_INTERVAL = 60 * 100;
    private long FASTEST_INTERVAL = 2000;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static LatLng jalan;

    BaseApiService mBaseApiService;
    SharedPrefManager mSharedPrefManager;
    private List<DataTempat> mListMarker = new ArrayList<>();

    @BindView(R.id.getParkLoct)
    Button getParkLoct;
//    @BindView(R.id.inTmpParkir)
//    AutoCompleteTextView inTmpParkir;
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

        mSharedPrefManager = new SharedPrefManager(getContext());

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
        listPoint = new ArrayList<>();
        startLocationUpdate();
        return view;
    }

    void getDataTempatParkir(){
        String token = mSharedPrefManager.getSpToken();
        mBaseApiService = ApiUtils.getAPIService();
        mBaseApiService.getTempatParkir(token).enqueue(new Callback<ListTempatParkir>() {
            @Override
            public void onResponse(Call<ListTempatParkir> call, Response<ListTempatParkir> response) {
                mListMarker = response.body().getmData();
                initMarker(mListMarker);
            }

            @Override
            public void onFailure(Call<ListTempatParkir> call, Throwable t) {

            }
        });
    }

    private void initMarker(List<DataTempat> listData){
        for(int i=0; i<mListMarker.size(); i++){
            LatLng locationPark = new LatLng(Double.parseDouble(mListMarker.get(i).getLatitude()),Double.parseDouble(mListMarker.get(i).getLongtitude()));

            mGMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                    .position(locationPark).title(mListMarker.get(i).getNamaTempat()));

            LatLng latLng = new LatLng(Double.parseDouble(mListMarker.get(0).getLatitude()), Double.parseDouble(mListMarker.get(0).getLongtitude()));

        }
    }

    protected void getBarcode(){
        Location dest = new Location("");
        dest.setLatitude(destination.latitude);
        dest.setLongitude(destination.longitude);

        Location otw = new Location("");
        otw.setLatitude(jalan.latitude);
        otw.setLongitude(jalan.longitude);

        float distance = dest.distanceTo(otw);
        if (distance<100){
            //request to get barcode
        }
    }

    protected void startLocationUpdate() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();


        SettingsClient settingsClient = LocationServices.getSettingsClient(getContext());
        settingsClient.checkLocationSettings(locationSettingsRequest);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
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
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
            }
        },Looper.myLooper());
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
        jalan = new LatLng(location.getLatitude(),location.getLongitude());
        getBarcode();
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
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_LOCATION_REQUEST_CODE);
            return;
        }
        mGMap.setMyLocationEnabled(true);
        getDataTempatParkir();


        mGMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if (listPoint.size()==2){
                    listPoint.clear();
                    mGMap.clear();
                }

                listPoint.add(latLng);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);

                if (listPoint.size()==1){
                    Snackbar.make(view, "Tekan dimana lokasi anda", Snackbar.LENGTH_SHORT);
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    current = listPoint.get(0);
                }else{
                    Snackbar.make(view, "Tekan area tujuan parkir anda", Snackbar.LENGTH_SHORT);
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                    destination = listPoint.get(1);
                }
                mGMap.addMarker(markerOptions);

                if (listPoint.size()==2){
                    //Create the URL to get request from first marker to second marker
                    String url = getRequestUrl(listPoint.get(0), listPoint.get(1));
                    TaskRequestDirection taskRequestDirections = new TaskRequestDirection();
                    taskRequestDirections.execute(url);
                }
            }
        });

        mPlaceAutocomplateFragmentCurr = (PlaceAutocompleteFragment) getActivity().getFragmentManager().findFragmentById(R.id.place_auto_complate_fragmentCurr);
        mPlaceAutocomplateFragmentCurr.setHint("Lokasi anda");
        mPlaceAutocomplateFragmentCurr.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                temp = place.getLatLng();
                mGMap.moveCamera(CameraUpdateFactory.newLatLngZoom(temp, 16));
            }

            @Override
            public void onError(Status status) {
                Snackbar.make(view, "Error connection", Snackbar.LENGTH_SHORT);
            }
        });

//        mPlaceAutocomplateFragmentDess = (PlaceAutocompleteFragment) getActivity().getFragmentManager().findFragmentById(R.id.place_auto_complate_fragmentDess);
//        mPlaceAutocomplateFragmentDess.setHint("Tujuan anda");
//        mPlaceAutocomplateFragmentDess.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                destination = place.getLatLng();
//            }
//
//            @Override
//            public void onError(Status status) {
//                Snackbar.make(view, "Error connection", Snackbar.LENGTH_SHORT);
//
//            }
//        });

    }

    private String getRequestUrl(LatLng current, LatLng destination){
        //Value of origin
        String str_org = "origin=" + current.latitude +","+current.longitude;
        //Value of destination
        String str_dest = "destination=" + destination.latitude+","+destination.longitude;
        //Set value enable the sensor
        String sensor = "sensor=false";
        //Mode for find direction
        String mode = "mode=driving";
        //Build the full param
        String param = str_org +"&" + str_dest + "&" +sensor+"&" +mode;
        //Output format
        String output = "json";
        //Create url to request
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + param;
        return url;
    }

    private String requestDirection(String reqUrl) throws IOException{
        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while((line = bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }

            responseString = stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                inputStream.close();
            }
            httpURLConnection.disconnect();
        }
        return responseString;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_LOCATION_REQUEST_CODE:
                if (grantResults.length> 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    mGMap.setMyLocationEnabled(true);
                }
                break;
        }
    }

    public class TaskRequestDirection extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String responseString = "";
            try {
                responseString = requestDirection(strings[0]);
            }catch (IOException e){
                e.printStackTrace();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            TaskParser taskParser = new TaskParser();
            taskParser.execute(s);
        }
    }

    public class TaskParser extends AsyncTask<String, Void, List<List<HashMap<String, String>>>>{

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
            JSONObject jsonObject = null;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jsonObject = new JSONObject(strings[0]);
                DirectionsParser directionsParser = new DirectionsParser();
                routes = directionsParser.parse(jsonObject);
            }catch (JSONException e){
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
            ArrayList points = null;

            PolylineOptions polylineOptions = null;

            for (List<HashMap<String, String>> path : lists) {
                points = new ArrayList();
                polylineOptions = new PolylineOptions();

                for (HashMap<String, String> point : path) {
                    double lat = Double.parseDouble(point.get("lat"));
                    double lon = Double.parseDouble(point.get("lon"));

                    points.add(new LatLng(lat,lon));
                }

                polylineOptions.addAll(points);
                polylineOptions.width(15);
                polylineOptions.color(Color.BLUE);
                polylineOptions.geodesic(true);
            }

            if (polylineOptions!=null) {
                mGMap.addPolyline(polylineOptions);
            } else {
                Toast.makeText(getActivity(), "Direction not found!", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        mPlaceAutocomplateFragmentCurr.onActivityResult(requestCode, resultCode, data);
//        mPlaceAutocomplateFragmentDess.onActivityResult(requestCode, resultCode, data);
//    }
//
//    @OnClick(R.id.getParkLoct)
//    public void getParkLoct(View view){
//        Bundle args = new Bundle();
//        args.putParcelable("current", current);
//        args.putParcelable("destination", destination);
//        Intent i = new Intent(getActivity(), PilihMobilFragment.class);
//        i.putExtra("bundle", args);
//        startActivity(i);
//    }
}
