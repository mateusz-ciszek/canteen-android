
package com.canteen.app.api.dataorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Addition {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("foodAddition")
    @Expose
    private FoodAddition foodAddition;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FoodAddition getFoodAddition() {
        return foodAddition;
    }

    public void setFoodAddition(FoodAddition foodAddition) {
        this.foodAddition = foodAddition;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
