package com.example.dell.arsenaldelhi;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by DELL on 14-01-2016.
 */
public class FixtureAsyncTask extends AsyncTask<String, Void, fixture[]>{
FixtureAsyncTaskInterface listener;
    @Override
    protected fixture[] doInBackground(String... params) {
        String urlString = params[0];
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream data = urlConnection.getInputStream();
            Scanner s = new Scanner(data);
            StringBuffer output = new StringBuffer();
            while (s.hasNext()) {
                output.append(s.nextLine());
            }
            Log.i("output", output.toString());
            s.close();
            urlConnection.disconnect();
            return parseJson(output.toString());
        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(fixture[] fixtures) {
        if(listener!=null)
            listener.FixtureTaskOnComplete(fixtures);
    }

    private fixture[] parseJson(String jsonString) {
        try{
            JSONObject object=new JSONObject(jsonString);
            JSONArray matches=object.getJSONArray("fixtures");
            fixture[] output=new fixture[matches.length()];
            for(int i=0;i<matches.length();i++){
                JSONObject match=matches.getJSONObject(i);
                fixture f=new fixture();
                f.date=match.getString("date");
                f.status=match.getString("status");
                f.matchday=match.getInt("matchday");
                f.homeTeam=match.getString("homeTeamName");
                f.awayTeam=match.getString("awayTeamName");
                JSONObject results = match.getJSONObject("result");
                if(f.status.equals("FINISHED")) {
                    f.goalsHome = results.getInt("goalsHomeTeam");
                    f.goalsAway = results.getInt("goalsAwayTeam");
                    f.futuregoalsHome=null;
                    f.futuregoalsAway=null;
                }
                else{
                   f.futuregoalsAway=null;
                    f.futuregoalsHome=null;
                    f.goalsAway=0;
                    f.goalsHome=0;
                }
                output[i] = f;
            }
            return  output;
        } catch (JSONException e) {
            return null;
        }
    }
}
