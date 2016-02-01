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
 * Created by DELL on 24-01-2016.
 */
public class TableAsyncTask  extends AsyncTask<String,Void,Tables[]>{
    TableAsyncTaskInterface listener;
    @Override
    protected Tables[] doInBackground(String... params) {
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
    protected void onPostExecute(Tables[] table) {
        if(listener!=null)
            listener.TableTaskOnComplete(table);
    }


    private Tables[] parseJson(String jsonString) {
        try{
            JSONObject object=new JSONObject(jsonString);
            JSONArray standings=object.getJSONArray("standing");
            Tables[] output=new Tables[standings.length()];
            for (int i=0;i<standings.length();i++){
                JSONObject stand=standings.getJSONObject(i);
                Tables t=new Tables();
                t.pos=stand.getInt("position");
                t.team=stand.getString("teamName");
                t.played=stand.getInt("playedGames");
                t.won=stand.getInt("wins");
                t.lost=stand.getInt("losses");
                t.draw=stand.getInt("draws");
                t.diff=stand.getInt("goalDifference");
                t.points=stand.getInt("points");
                output[i]=t;
            }
            return  output;
        } catch (JSONException e) {
            return  null;
        }
    }
}
