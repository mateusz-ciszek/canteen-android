package com.canteen.app.api;



public interface ServiceCallback {
    public void serviceSucces(String url);
    public void serviceFailure(Exception e);
}
