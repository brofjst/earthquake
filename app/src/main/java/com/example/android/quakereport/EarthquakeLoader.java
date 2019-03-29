package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<EarthquakeObject>> {
    private String mUrl;

    public EarthquakeLoader (Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    public ArrayList<EarthquakeObject> loadInBackground() {
        return QueryUtils.extractEarthquakes(mUrl);
    }
}
