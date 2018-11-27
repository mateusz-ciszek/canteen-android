package com.example.microtemp.microblog.service;

import com.example.microtemp.microblog.api.HttpRequestData;
import com.example.microtemp.microblog.api.HttpRequestMethods;
import com.example.microtemp.microblog.api.handlers.DeleteMenuRequestHandler;
import com.example.microtemp.microblog.api.models.requests.DeleteMenuRequestBody;
import com.example.microtemp.microblog.api.models.responses.EmptyResponse;
import com.example.microtemp.microblog.models.Menu;

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
