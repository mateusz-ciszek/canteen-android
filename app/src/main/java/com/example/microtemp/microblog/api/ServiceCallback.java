package com.example.microtemp.microblog.api;



public interface ServiceCallback {
    public void serviceSucces(String url);
    public void serviceFailure(Exception e);
}
