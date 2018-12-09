package com.canteen.app.activity.menu.list;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.canteen.app.AdapterContainer;
import com.canteen.app.MainActivity;
import com.canteen.app.R;
import com.canteen.app.activity.cart.OrderCartActivity;
import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.handlers.AllMenusRequestHandler;
import com.canteen.app.api.models.requests.AllMenusRequestBody;
import com.canteen.app.api.models.responses.AllMenusResponse;


public class MenuListsActivity extends AppCompatActivity implements AdapterContainer {

    private RecyclerView menusRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

        this.menusRecyclerView = findViewById(R.id.menusRecyclerView);
        this.menusRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        this.menusRecyclerView.setLayoutManager(layoutManager);

        AllMenusRequestBody requestBody = new AllMenusRequestBody();
        HttpRequestData<AllMenusRequestBody> requestData = HttpRequestData
                .<AllMenusRequestBody>builder()
                .method(HttpRequestMethods.GET)
                .requestBody(requestBody)
                .build();
        new AllMenusRequestHandlerImpl(this).execute(requestData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                startActivity(new Intent(this, OrderCartActivity.class));
                return true;
            case R.id.action_logout:
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
                sp.edit().remove("token").apply();
                finish();
                startActivity(new Intent(this, MainActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        this.menusRecyclerView.setAdapter(adapter);
    }


    private static class AllMenusRequestHandlerImpl extends AllMenusRequestHandler {

        private final AdapterContainer adapterContainer;

        AllMenusRequestHandlerImpl(AdapterContainer adapterContainer) {
            this.adapterContainer = adapterContainer;
        }

        @Override
        protected void onPostExecute(AllMenusResponse result) {
            // TODO handle error response
            MenuListsAdapter adapter = new MenuListsAdapter(result.getData().getMenus());
            this.adapterContainer.setAdapter(adapter);
        }
    }
}

