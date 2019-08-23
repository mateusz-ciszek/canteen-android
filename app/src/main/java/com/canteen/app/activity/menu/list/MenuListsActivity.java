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
import android.view.View;
import android.widget.TextView;

import com.canteen.app.MainActivity;
import com.canteen.app.R;
import com.canteen.app.activity.cart.OrderCartActivity;
import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.handlers.AllMenusRequestHandler;
import com.canteen.app.api.models.requests.AllMenusRequestBody;
import com.canteen.app.api.models.responses.AllMenusResponse;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MenuListsActivity extends AppCompatActivity {

    @BindView(R.id.menus_recycler_view)
    RecyclerView menusRecyclerView;

    @BindView(R.id.error_text_view)
    TextView errorTextView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        ButterKnife.bind(this);

        initRecyclerView();

        AllMenusRequestBody requestBody = new AllMenusRequestBody();
        HttpRequestData<AllMenusRequestBody> requestData = HttpRequestData.<AllMenusRequestBody>builder()
                .method(HttpRequestMethods.GET)
                .requestBody(requestBody)
                .build();

        AllMenusResponse response;
        try {
            response = new AllMenusRequestHandlerImpl().execute(requestData).get();
        } catch (ExecutionException | InterruptedException e) {
            // TODO Log out
            setError(getString(R.string.something_wrong));
            return;
        }

        if (response.isSuccessful()) {
            if (response.getData().getMenus().size() == 0) {
                setError(getString(R.string.menu_list_no_menu_available));
            }
            MenuListsAdapter adapter = new MenuListsAdapter(response.getData().getMenus());
            menusRecyclerView.setAdapter(adapter);
        } else {
            // TODO Log out
            setError(getString(R.string.something_wrong));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
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

    private void initRecyclerView() {
        menusRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        menusRecyclerView.setLayoutManager(layoutManager);
    }

    private void setError(final String message) {
        menusRecyclerView.setVisibility(View.GONE);
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(message);
    }

    private static class AllMenusRequestHandlerImpl extends AllMenusRequestHandler {
        @Override
        protected void onPostExecute(final AllMenusResponse result) { }
    }
}

