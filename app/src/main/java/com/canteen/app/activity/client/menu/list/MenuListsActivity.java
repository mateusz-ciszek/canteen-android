package com.canteen.app.activity.client.menu.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.canteen.app.R;
import com.canteen.app.activity.client.common.ActivityWithMainOptionMenu;
import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.handlers.AllMenusRequestHandler;
import com.canteen.app.api.models.requests.AllMenusRequestBody;
import com.canteen.app.api.models.responses.AllMenusResponse;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MenuListsActivity extends ActivityWithMainOptionMenu {

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
