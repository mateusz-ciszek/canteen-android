package com.example.microtemp.microblog;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.microtemp.microblog.api.HttpRequestData;
import com.example.microtemp.microblog.api.HttpRequestMethods;
import com.example.microtemp.microblog.api.handlers.AllMenusRequestHandler;
import com.example.microtemp.microblog.api.models.requests.AllMenusRequestBody;
import com.example.microtemp.microblog.api.models.responses.AllMenusResponse;


public class MenuActivity extends AppCompatActivity {

    private RecyclerView menusRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.menusRecyclerView = findViewById(R.id.menusRecyclerView);

        AllMenusRequestBody requestBody = new AllMenusRequestBody();
        HttpRequestData<AllMenusRequestBody> requestData = HttpRequestData
                .<AllMenusRequestBody>builder()
                .method(HttpRequestMethods.GET)
                .requestBody(requestBody)
                .build();
        new AllMenusRequestHandlerImpl().execute(requestData);
    }


    private static class AllMenusRequestHandlerImpl extends AllMenusRequestHandler {

        @Override
        protected void onPostExecute(AllMenusResponse result) {
            System.out.println(result);
        }
    }
}
