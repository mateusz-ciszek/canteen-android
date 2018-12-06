package com.canteen.app.api;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Request {
    private ServiceCallback callback;
    private Exception error;

    public Request(ServiceCallback callback) {
        this.callback = callback;
    }

    @SuppressLint("StaticFieldLeak")
    public void uruchom(String l) {
        String location = l;
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                StringBuilder result = null;
                String whereUrl = strings[0];
                String line;
                URL url;
                URLConnection conn;
                BufferedReader in = null;

                try {
                    url = new URL(whereUrl);
                    conn = url.openConnection();
                    in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    result = new StringBuilder();
                    while ((line = in.readLine()) != null) {
                        result.append(line);
                    }
                    //	callback.serviceSucces(result.toString());

                } catch (MalformedURLException ex) {
                    System.err.println(ex);
                } catch (IOException ex) {
                    System.err.println(ex);
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException ex) {
                            System.err.println(ex);
                        }
                    }
                }

                return result != null ? result.toString() : null;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s == null && error != null) {
                    callback.serviceFailure(error);
                    return;
                }
                try {


                    callback.serviceSucces(s);


                } catch (Exception e) {

                }
            }
        }.execute((location));
    }
}

