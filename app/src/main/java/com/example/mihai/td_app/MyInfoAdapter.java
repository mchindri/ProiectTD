package com.example.mihai.td_app;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

class MyInfoAdapter implements InfoWindowAdapter {

    private ArrayList<Station> stations;
    private Context context;

    public MyInfoAdapter(Context context, ArrayList stations)
    {
        this.context = context;
        this.stations = stations;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        int total;
        int reserved = 0;
        int busy = 0;
        int available = 0;

        stations = refreshStations();
        if (stations != null)
        {
            for(Station s : stations)
            {
                if (s.getName().equals(marker.getTitle()))
                {
                    total = s.getAvailable();
                    reserved = s.getReserved();
                    busy = s.getOccupied();
                    available = total - reserved - busy;
                }
            }
        }
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.info_window, null);

        TextView tvTitle = view.findViewById(R.id.Title);
        TextView tvAvailable = view.findViewById(R.id.AvailableNb);
        TextView tvReserved = view.findViewById(R.id.ReservedNb);
        TextView tvBusy = view.findViewById(R.id.BusyNb);

        tvTitle.setText(marker.getTitle());
        tvAvailable.setText(String.valueOf(available));
        tvReserved.setText(String.valueOf(reserved));
        tvBusy.setText(String.valueOf(busy));

        //InfoWindowData infoWindowData = (InfoWindowData) marker.getTag();

        //int imageId = context.getResources().getIdentifier(infoWindowData.getImage().toLowerCase(),
               // "drawable", context.getPackageName());
        //img.setImageResource(imageId);


        return view;
    }

    private ArrayList<Station> refreshStations() {
        ArrayList<Station> newStations;
        newStations = MainActivity.db.getAllStations();
        if (newStations == null)
            return stations;
        return newStations;
    }
}
