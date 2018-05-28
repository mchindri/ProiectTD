package com.example.mihai.td_app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

import static com.google.android.gms.common.ConnectionResult.SERVICE_MISSING;
import static com.google.android.gms.common.ConnectionResult.SUCCESS;
import static com.google.android.gms.common.GooglePlayServicesUtil.getErrorDialog;

public class MapsActivity extends FragmentActivity implements
        OnMarkerClickListener,
        OnMapReadyCallback {

    private GoogleMap mMap;
    private WeakHashMap mMarkers = new WeakHashMap<Marker, String>();
    private ArrayList<Station> stations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        initializeMarkers();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46.770039, 23.594263), 16.0f));

        MyInfoAdapter myAdapter = new MyInfoAdapter(this, stations);
        mMap.setInfoWindowAdapter(myAdapter);

    }

    private void initializeMarkers()
    {
        stations = MainActivity.db.getAllStations();
        if (stations == null)
        {
            Toast.makeText(this, "No stations avalible", Toast.LENGTH_SHORT).show();
            return;
        }
        for(Station s : stations)
        {
            Marker m = mMap.addMarker(new MarkerOptions()
                    .position(s.getCoords())
                    .title(s.getName()));
            mMarkers.put(m, s.getName());
            mMap.setOnMarkerClickListener(this);
        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        //MyInfo info = new MyInfo((String) mMarkers.get(marker));
        //marker.setSnippet(info.toString());

        //marker.showInfoWindow();
        //Toast.makeText(this, "Info: " + info.toString(), Toast.LENGTH_LONG).show();
        return false;
    }
}
