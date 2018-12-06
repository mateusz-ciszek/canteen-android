package com.canteen.app.service;

import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.handlers.DeleteMenuRequestHandler;
import com.canteen.app.api.models.requests.DeleteMenuRequestBody;
import com.canteen.app.api.models.responses.EmptyResponse;
import com.canteen.app.models.Menu;

public class MenuService {
    private static final MenuService ourInstance = new MenuService();

    public static MenuService getInstance() {
        return ourInstance;
    }

    private MenuService() { }

    public boolean deleteMenu(Menu menu, DeleteMenuRequestHandlerImpl.ResponseHandler handler) {
        DeleteMenuRequestBody requestBody = new DeleteMenuRequestBody(menu.get_id());
        HttpRequestData requestData = HttpRequestData.builder()
                .requestBody(requestBody)
                .method(HttpRequestMethods.DELETE)
                .build();

        new DeleteMenuRequestHandlerImpl(handler).execute(requestData);
        return false;
    }

    public static class DeleteMenuRequestHandlerImpl extends DeleteMenuRequestHandler {
        ResponseHandler handler;

        DeleteMenuRequestHandlerImpl(ResponseHandler handler) {
            this.handler = handler;
        }

        @Override
        protected void onPostExecute(EmptyResponse result) {
            System.out.println(result);
            handler.handle(result.getHttpStatusCode() == 200);
        }

        public interface ResponseHandler<T> {
            void handle(T response);
        }
    }
}
