package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {
    private static String EQ_REQUEST_URL;
    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils(String url) {
    }

    /**
     * Return a list of {@link EarthquakeObject} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<EarthquakeObject> extractEarthquakes(String url) {
        EQ_REQUEST_URL = url;
        String jsonRequest = null;
        try{
            jsonRequest = makeHttpRequest();
        } catch (IOException e){
            Log.e("QueryUtils", "Problem making http request", e);
        }

        Log.v("jsonRequest", jsonRequest);

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<EarthquakeObject> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject eq = new JSONObject(jsonRequest);
            JSONArray features = eq.getJSONArray("features");

            for(int i=0; i<features.length(); i++){
                JSONObject obj = features.getJSONObject(i);
                JSONObject properties = obj.getJSONObject("properties");
                Double mag = Double.parseDouble(properties.getString("mag"));
                String place = properties.getString("place");
                String time = properties.getString("time");

                int comma = place.indexOf(",");
                String cityToDisplay = "";
                for(int c=comma; c>0; c--){
                    if (place.charAt(c) == ' '){
                        cityToDisplay = place.substring(c+1,comma);
                        break;
                    }
                }
                String countryToDisplay = place.substring(comma + 2);
                Log.v("country", countryToDisplay);
                Log.v("place",cityToDisplay);

                long timeValue = Long.valueOf(time);
                Date date = new Date(timeValue);

                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM, DD, yyyy");
                String dateToDisplay = dateFormat.format(date);
                earthquakes.add(new EarthquakeObject(mag, cityToDisplay,countryToDisplay,dateToDisplay));
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

    public static String makeHttpRequest() throws IOException{
        String jsonResponse= "";

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try{
            URL url = new URL(EQ_REQUEST_URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}