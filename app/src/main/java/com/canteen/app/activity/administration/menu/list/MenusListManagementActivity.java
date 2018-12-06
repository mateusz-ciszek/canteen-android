package com.canteen.app.activity.administration.menu.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.canteen.app.App;
import com.example.microtemp.microblog.R;
import com.canteen.app.activity.cart.OrderCartActivity;
import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.handlers.AllMenusRequestHandler;
import com.canteen.app.api.models.requests.AllMenusRequestBody;
import com.canteen.app.api.models.responses.AllMenusResponse;

import java.util.concurrent.ExecutionException;

public class MenusListManagementActivity extends AppCompatActivity {

    private MenuView.ItemView addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus_list_management);
        addButton = findViewById(R.id.addMenu);


        RecyclerView menusListRecyclerView = findViewById(R.id.menusListRecyclerView);

        AllMenusResponse response = makeRequest();
        if (response == null) return; // TODO Co zrobić jeśli nie uda się pobrać menu?

        menusListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MenusListManagementAdapter adapter
                = new MenusListManagementAdapter(response.getData().getMenus());
        menusListRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list_menu, menu);
        return true;
    }

    private AllMenusResponse makeRequest() {
        AllMenusRequestBody requestBody = new AllMenusRequestBody();
        HttpRequestData<AllMenusRequestBody> requestData = HttpRequestData.<AllMenusRequestBody>builder()
                .requestBody(requestBody)
                .method(HttpRequestMethods.GET)
                .build();

        AllMenusResponse response = null;
        try {
            response = new AllMenusRequestHandlerImpl().execute(requestData).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Something went wrong", Toast.LENGTH_SHORT)
                    .show();
            finish();
        }
        return response;
    }

    public void addMenus(MenuItem item) {
       Intent intent = new Intent(App.getContext(), AddMenuActivity.class);
        App.getContext().startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                startActivity(new Intent(this, OrderCartActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    static class AllMenusRequestHandlerImpl extends AllMenusRequestHandler {
        @Override
        protected void onPostExecute(AllMenusResponse result) { }
    }
}
