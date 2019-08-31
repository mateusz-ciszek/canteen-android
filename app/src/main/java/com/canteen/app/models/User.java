package com.canteen.app.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class User implements Serializable {

    @SerializedName("_id")
    private String id;

    private String email;

    private String firstName;

    private String lastName;
}
