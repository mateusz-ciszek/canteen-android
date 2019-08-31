package com.canteen.app.service;

import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.handlers.DeleteFoodRequestHandler;
import com.canteen.app.api.models.requests.DeleteFoodRequestBody;
import com.canteen.app.api.models.responses.EmptyResponse;
import com.canteen.app.models.Food;

public class FoodService {
    private static final FoodService ourInstance = new FoodService();

    public static FoodService getInstance() {
        return ourInstance;
    }

    private FoodService() { }

    public boolean deleteMenu(Food food, DeleteFoodRequestHandlerImpl.ResponseHandler handler) {
        DeleteFoodRequestBody requestBody = new DeleteFoodRequestBody(food.getId());
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
