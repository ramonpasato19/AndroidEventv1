package com.example.asus.androideventv1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, AdapterView.OnItemSelectedListener {

    private GoogleMap mMap;


    private int mMapTypes[] = {
            GoogleMap.MAP_TYPE_NONE,
            GoogleMap.MAP_TYPE_NORMAL,
            GoogleMap.MAP_TYPE_SATELLITE,
            GoogleMap.MAP_TYPE_HYBRID,
            GoogleMap.MAP_TYPE_TERRAIN
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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
        LatLng guambra = new LatLng(-2.897668, -78.997687);
        mMap.addMarker(new MarkerOptions().position(guambra).title("Sede Guambra Raymi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(guambra));
        CameraPosition cameraPosition = CameraPosition.builder()
                .target(guambra)
                .zoom(13)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//gestos
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setTiltGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);


        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Marcadores
        mMap.addMarker(new MarkerOptions().position(new LatLng(-2.781443, -78.759034)).title("carnaval de Paute").snippet("orilla del rio").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-2.907077, -79.006624)).title("Carrera Warmi").snippet("Evento Atletismo FFC WARMI").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-2.897539, -79.026395)).title("Carnaval feria Libre").snippet("Evento comerciantes del mercado Feria Libre").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-3.013749, -79.034701)).title("Festival del Mote Pata").snippet("Evento en Parroquia Tarqui").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
       // mMap.addMarker(new MarkerOptions().position(new LatLng(-2.566487, -78.937841)).title("Taita Carnaval").snippet("Evento en Cañar").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).icon(BitmapDescriptorFactory.fromBitmap(bmp)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-2.566487, -78.937841)).title("Taita Carnaval").snippet("Evento en Cañar").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        LatLng latLng = new LatLng(-2.897094, -79.004419);
        MarkerOptions markerOptions =
                new MarkerOptions()
                        .position(latLng)
                        .title("Carnaval Cuencano")
                        .snippet("Evento de Comp@dre del Carnaval Azuayo");
        Marker marker = googleMap.addMarker(markerOptions);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mMap.setMapType(mMapTypes[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
