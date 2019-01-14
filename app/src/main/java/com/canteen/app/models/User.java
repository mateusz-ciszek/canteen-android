package com.canteen.app.models;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class User implements Serializable {
    private String _id;
    private String email;
    private String firstName;
    private String lastName;
}
