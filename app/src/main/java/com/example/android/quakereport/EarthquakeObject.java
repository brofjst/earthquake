package com.example.android.quakereport;

public class EarthquakeObject {
    private double mMagniteude;
    private String mCity,mCountry, mDate;

    public EarthquakeObject(double magnitude, String city,String country, String date) {
        mMagniteude = magnitude;
        mCity = city;
        mCountry = country;
        mDate = date;
    }

    public double getMagniteude() {
        return mMagniteude;
    }

    public String getCity() {
        return mCity;
    }

    public String getCountry(){ return mCountry; }

    public String getmDate() {
        return mDate;
    }

    @Override
    public String toString() {
        return "Earthquake{" +
                "magnitude='" + mMagniteude + '\'' +
                ", city='" + mCity + '\'' +
                ", country='" + mCountry + '\'' +
                ", date=" + mDate +
                '}';
    }
}
