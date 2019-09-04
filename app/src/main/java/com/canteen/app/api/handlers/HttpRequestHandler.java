package com.canteen.app.api.handlers;

import android.os.AsyncTask;
import android.util.Log;

import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.models.requests.RequestBody;
import com.canteen.app.api.models.responses.Response;
import com.canteen.app.component.DaggerAppComponent;
import com.canteen.app.service.auth.AuthService;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

public abstract class HttpRequestHandler<T extends RequestBody, U extends Response>
        extends AsyncTask<HttpRequestData<T>, Void, U> {

    private static String LOG_TAG = "LoginActivity";

    private AuthService authService = DaggerAppComponent.create().getAuthService();

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
        RequestResult requestResult = null;
        try {
            requestResult = makeRequest(URL, data, method);
        } catch (ConnectException e) {
            // No Internet
            requestResult = new RequestResult(null, 400);
        } catch (IOException e) {
            Log.e(LOG_TAG, "doInBackground", e);
        }
        String res;

        U response = null;
        try {
            response = (U) ((Class) ((ParameterizedType) this.getClass().getSuperclass()
                    .getGenericSuperclass()).getActualTypeArguments()[1]).newInstance();

            if (requestResult != null && requestResult.getInputStream() != null) {
                res = convertStreamToString(requestResult.getInputStream());
                response.populate(requestResult.getHttpStatusCode(), res);
            } else {
                response.populate(400, null);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }


        return response;
    }

    @Override
    protected abstract void onPostExecute(U result);

    private RequestResult makeRequest(String ServerURL, String data, HttpRequestMethods method) throws IOException {

        URL url = new URL(ServerURL);
        HttpURLConnection cc = (HttpURLConnection)
                url.openConnection();
        cc.setConnectTimeout(5000);
        cc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        cc.setRequestProperty("Authorization", "Bearer " + getToken());
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

        int response = cc.getResponseCode();
        InputStream inputStream = isSuccessResponse(response) ? cc.getInputStream() : cc.getErrorStream();
        return new RequestResult(inputStream, response);
    }

    private static boolean isSendingData(HttpRequestMethods method) {
        return Arrays.asList(
                HttpRequestMethods.POST,
                HttpRequestMethods.PATCH
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

    private String getToken() {
        return authService.getToken().orElse("");
    }

    @Getter
    @AllArgsConstructor
    private class RequestResult {
        private InputStream inputStream;
        private int httpStatusCode;
    }
}
