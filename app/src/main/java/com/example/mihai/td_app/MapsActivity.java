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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        /*
        GoogleApiAvailability obj = GoogleApiAvailability.getInstance();
        int val = obj.isGooglePlayServicesAvailable(this);
        if (val != SUCCESS) {
            String msg = "Error on service availability";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        }
        */
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

        initializeMarkers();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(46.770039, 23.594263), 16.0f));

        MyInfoAdapter myAdapter = new MyInfoAdapter(this);
        mMap.setInfoWindowAdapter(myAdapter);

        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void initializeMarkers()
    {
        HashMap coords = new HashMap<String, LatLng>();
        coords.put("Teatru", new LatLng(46.769233, 23.597368));
        coords.put("Eroiilor", new LatLng(46.770039, 23.594263));
        coords.put("Avram Iancu", new LatLng(46.771523, 23.596130));

        Iterator itr = coords.entrySet().iterator();
        while(itr.hasNext())
        {
            Map.Entry<String, LatLng> entry = (Map.Entry<String, LatLng>) itr.next();
            Marker m = mMap.addMarker(new MarkerOptions()
                    .position(entry.getValue())
                    .title(entry.getKey()));
            mMarkers.put(m, entry.getKey());
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
