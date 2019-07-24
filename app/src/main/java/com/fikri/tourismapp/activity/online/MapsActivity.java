package com.fikri.tourismapp.activity.online;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.fikri.tourismapp.R;
import com.fikri.tourismapp.model.TourismOnline;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public static final String POINTER="pointer";
    private MarkerOptions options = new MarkerOptions();
    private ArrayList<LatLng> markerList = new ArrayList();
    private GoogleMap mMap;
    private static final float INITIAL_ZOOM = 12f;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private String[] title;
    private List<TourismOnline> tourismOnlines;
    private double[] lat;
    private double[] lng;

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
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        TourismOnline tourismOnline = getIntent().getParcelableExtra(POINTER);

        if(tourismOnline != null) {
            double latitude = Double.parseDouble(tourismOnline.getLatitude());
            double longitude = Double.parseDouble(tourismOnline.getLongitude());

            LatLng pointer = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(pointer).title(tourismOnline.getTitle()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointer, INITIAL_ZOOM));
        }else{
            title = getResources().getStringArray(R.array.title);
            // Add a marker in Sydney and move the camera
            LatLng arau = new LatLng(-0.96398, 100.36034);
            mMap.addMarker(new MarkerOptions().position(arau).title("Arau"));

            markerList.add(new LatLng(-0.96594,100.35094));
            markerList.add(new LatLng(-0.93992,100.46493));
            markerList.add(new LatLng(-0.82724,100.39404));
            markerList.add(new LatLng(-0.92436,100.36265));
            markerList.add(new LatLng(-0.87005,100.38263));
            markerList.add(new LatLng(-0.96506,100.35890));
            markerList.add(new LatLng(-0.95684,100.35604));
            markerList.add(new LatLng(-0.99322,100.36365));
            markerList.add(new LatLng(-0.92890, 100.35000));

            int i = 1;
            for (LatLng point : markerList) {
                options.position(point);
                options.title(title[i]);
                options.snippet(title[i]);
                googleMap.addMarker(options);
                i++;
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(arau, INITIAL_ZOOM));
        }



        enableCurrentLocation();
        setPointOfInterest(mMap);
        setMapLongClick(mMap);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }

    public void enableCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
    }

    public void setPointOfInterest(final GoogleMap map) {
        mMap = map;
        mMap.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
            @Override
            public void onPoiClick(PointOfInterest pointOfInterest) {
                Marker poiMarker = mMap.addMarker(new MarkerOptions()
                        .position(pointOfInterest.latLng).title(pointOfInterest.name)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                poiMarker.showInfoWindow();
            }
        });
    }

    public void setMapLongClick(final GoogleMap mapLongClick) {
        mapLongClick.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                String mapSnippet = String.format(Locale.getDefault(), getString(R.string.long_snippet),
                        (latLng.latitude), latLng.longitude);

                mapLongClick.addMarker(new MarkerOptions().position(latLng).title(getString(R.string.drop_pin)).
                        snippet(mapSnippet).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            }
        });


    }

}
