package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<EarthquakeObject> {

    public EarthquakeAdapter(@NonNull Context context, ArrayList<EarthquakeObject> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;

        if (listView == null)
            listView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_activity, parent, false);

        EarthquakeObject item = getItem(position);

        TextView magnitudeString = (TextView) listView.findViewById(R.id.magnitude_text_view);
        magnitudeString.setText(String.valueOf(item.getMagniteude()));

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeString.getBackground();
        int magnitude = (int) Math.round(item.getMagniteude());
        Log.v("numero", String.valueOf(magnitude));

        switch (magnitude){
            case 1:
                int magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude1);
                magnitudeCircle.setColor(magnitude1Color);
                break;
            case 2:
                int magnitude2Color = ContextCompat.getColor(getContext(), R.color.magnitude1);
                magnitudeCircle.setColor(magnitude2Color);
                break;
            case 3:
                int magnitude3Color = ContextCompat.getColor(getContext(), R.color.magnitude3);
                magnitudeCircle.setColor(magnitude3Color);
                break;
            case 4:
                int magnitude4Color = ContextCompat.getColor(getContext(), R.color.magnitude4);
                magnitudeCircle.setColor(magnitude4Color);
                break;
            case 5:
                int magnitude5Color = ContextCompat.getColor(getContext(), R.color.magnitude5);
                magnitudeCircle.setColor(magnitude5Color);
                break;
            case 6:
                int magnitude6Color = ContextCompat.getColor(getContext(), R.color.magnitude6);
                magnitudeCircle.setColor(magnitude6Color);
                break;
            case 7:
                int magnitude7Color = ContextCompat.getColor(getContext(), R.color.magnitude7);
                magnitudeCircle.setColor(magnitude7Color);
                break;
            case 8:
                int magnitude8Color = ContextCompat.getColor(getContext(), R.color.magnitude8);
                magnitudeCircle.setColor(magnitude8Color);
                break;
            case 9:
                int magnitude9Color = ContextCompat.getColor(getContext(), R.color.magnitude9);
                magnitudeCircle.setColor(magnitude9Color);
                break;
            default:
                 int magnitude10Color = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                 magnitudeCircle.setColor(magnitude10Color);
        }



        TextView cityString = (TextView) listView.findViewById(R.id.city_text_view);
        cityString.setText(item.getCity());

        TextView countryString = (TextView) listView.findViewById(R.id.country_text_view);
        countryString.setText(item.getCountry());

        TextView dateString = (TextView) listView.findViewById(R.id.date_text_view);
        dateString.setText(item.getmDate());



        return listView;
    }
}
