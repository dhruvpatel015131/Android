package com.example.googlemaps;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemaps.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ratnagiri = new LatLng(16.992500, 73.294197);
        mMap.addMarker(new MarkerOptions().position(ratnagiri).title(getString(R.string.marker_in_ratnagiri)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ratnagiri));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ratnagiri,12));
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng mumbai = new LatLng(19.0760, 72.8777);
        mMap.addMarker(new MarkerOptions().position(mumbai).title(getString(R.string.marker_in_mumbai)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mumbai));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mumbai,12));
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    public void onZoom(View view) {
        if(view.getId() ==R.id.button){
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        if(view.getId() ==R.id.button2){
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }


}