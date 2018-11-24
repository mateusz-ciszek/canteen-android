package com.example.microtemp.microblog.api.models.responses.body;

import com.example.microtemp.microblog.models.Menu;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AllMenusResponseBody implements BaseResponseBody {
    private List<Menu> menus;

    @Override
    public String getMessage() {
        return null;
    }
}
