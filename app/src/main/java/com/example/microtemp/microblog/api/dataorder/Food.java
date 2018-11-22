
package com.example.microtemp.microblog.api.dataorder;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Food {

    @SerializedName("additions")
    @Expose
    private List<Addition_> additions = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public List<Addition_> getAdditions() {
        return additions;
    }

    public void setAdditions(List<Addition_> additions) {
        this.additions = additions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
