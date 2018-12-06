package com.example.microtemp.microblog.service;

import com.example.microtemp.microblog.api.HttpRequestData;
import com.example.microtemp.microblog.api.HttpRequestMethods;
import com.example.microtemp.microblog.api.handlers.DeleteFoodRequestHandler;
import com.example.microtemp.microblog.api.models.requests.DeleteFoodRequestBody;
import com.example.microtemp.microblog.api.models.responses.EmptyResponse;
import com.example.microtemp.microblog.models.Food;

public class FoodService {
    private static final FoodService ourInstance = new FoodService();

    public static FoodService getInstance() {
        return ourInstance;
    }

    private FoodService() { }

    public boolean deleteMenu(Food food, DeleteFoodRequestHandlerImpl.ResponseHandler handler) {
        DeleteFoodRequestBody requestBody = new DeleteFoodRequestBody(food.get_id());
        HttpRequestData requestData = HttpRequestData.builder()
                .requestBody(requestBody)
                .method(HttpRequestMethods.DELETE)
                .build();

        new DeleteFoodRequestHandlerImpl(handler).execute(requestData);
        return false;
    }

    public static class DeleteFoodRequestHandlerImpl extends DeleteFoodRequestHandler {
        ResponseHandler handler;

        DeleteFoodRequestHandlerImpl(ResponseHandler handler) {
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
