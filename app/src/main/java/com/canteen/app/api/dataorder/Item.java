
package com.canteen.app.api.dataorder;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("additions")
    @Expose
    private List<Addition> additions = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("food")
    @Expose
    private Food food;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Addition> getAdditions() {
        return additions;
    }

    public void setAdditions(List<Addition> additions) {
        this.additions = additions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
