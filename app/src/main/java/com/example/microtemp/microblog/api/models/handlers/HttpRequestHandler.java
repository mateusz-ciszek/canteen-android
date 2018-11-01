package com.example.microtemp.microblog.api.models.handlers;

import android.os.AsyncTask;
import android.util.Log;

import com.example.microtemp.microblog.api.HttpRequestData;
import com.example.microtemp.microblog.api.HttpRequestMethods;
import com.example.microtemp.microblog.api.models.requests.RequestBody;
import com.example.microtemp.microblog.api.models.responses.Response;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

public abstract class HttpRequestHandler<T extends RequestBody, U extends Response>
        extends AsyncTask<HttpRequestData<T>, Void, U> {

    private static String LOG_TAG = "LoginActivity";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected U doInBackground(HttpRequestData... arg) {
        HttpRequestData requestData = arg[0];
        String URL = requestData.getUrl();
        HttpRequestMethods method = requestData.getMethod();
        Gson gson = new Gson();
        String data = gson.toJson(requestData.getRequestBody());

        Log.d(LOG_TAG, "URL: " + URL);
        RequestResult requestResult = makeRequest(URL, data, method);
        String res;

        U response = null;
        try {
            response = (U) ((Class) ((ParameterizedType)this.getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments()[1]).newInstance();

            if (requestResult.getInputStream() != null) {
                res = convertStreamToString(requestResult.getInputStream());
                response.populate(requestResult.getHttpStatusCode(), res);
            } else {
                response.populate(400, "Something went wrong");
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }


        return response;
    }

    @Override
    protected abstract void onPostExecute(U result);

    private RequestResult makeRequest(String ServerURL, String data, HttpRequestMethods method) {

        InputStream inputStream = null;
        int response = 0;
        try {
            URL url = new URL(ServerURL);
            HttpURLConnection cc = (HttpURLConnection)
                    url.openConnection();
            cc.setConnectTimeout(5000);
            cc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            cc.setRequestMethod(method.name());
            cc.setDoInput(true);

            if (isSendingData(method)) {
                cc.setDoOutput(true);
                cc.connect();
                DataOutputStream dos = new DataOutputStream(cc.getOutputStream());
                dos.writeBytes(data);
                dos.flush();
                dos.close();
            }

            response = cc.getResponseCode();
            inputStream = isSuccessResponse(response) ? cc.getInputStream() : cc.getErrorStream();

        } catch (Exception e) {
            Log.e(LOG_TAG, "Error in GetData", e);
        }
        return new RequestResult(inputStream, response);
    }

    private static boolean isSendingData(HttpRequestMethods method) {
        return Arrays.asList(
                HttpRequestMethods.POST
                // TODO Dodanie kolejnych metod HTTP
        ).contains(method);
    }

    private static boolean isSuccessResponse(int response) {
        return Arrays.asList(
                HttpURLConnection.HTTP_OK,
                HttpURLConnection.HTTP_CREATED,
                HttpURLConnection.HTTP_ACCEPTED
        ).contains(response);
    }

    private static String convertStreamToString(InputStream stream) {
        InputStreamReader isr = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder response = new StringBuilder();

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error in ConvertStreamToString", e);
        } finally {
            try {
                stream.close();
            } catch (Exception e) {
                Log.e(LOG_TAG, "Error in ConvertStreamToString", e);
            }
        }
        return response.toString();
    }

    @Getter
    @AllArgsConstructor
    private class RequestResult {
        private InputStream inputStream;
        private int httpStatusCode;
    }
}
