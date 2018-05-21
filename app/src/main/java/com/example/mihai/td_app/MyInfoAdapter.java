package com.example.mihai.td_app;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

class MyInfoAdapter implements InfoWindowAdapter {

    int total;
    int avalible;
    int reserved;
    int busy;

    private Context context;

    public MyInfoAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {


        total = 10;
        reserved = 2;
        busy = 5;
        avalible = total - reserved - busy;

        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.info_window, null);

        TextView tvTitle = view.findViewById(R.id.Title);
        TextView tvAvailable = view.findViewById(R.id.AvailableNb);
        TextView tvReserved = view.findViewById(R.id.ReservedNb);
        TextView tvBusy = view.findViewById(R.id.BusyNb);

        tvTitle.setText(marker.getTitle());
        tvAvailable.setText(String.valueOf(avalible));
        tvReserved.setText(String.valueOf(reserved));
        tvBusy.setText(String.valueOf(busy));

        //InfoWindowData infoWindowData = (InfoWindowData) marker.getTag();

        //int imageId = context.getResources().getIdentifier(infoWindowData.getImage().toLowerCase(),
               // "drawable", context.getPackageName());
        //img.setImageResource(imageId);


        return view;
    }
}
