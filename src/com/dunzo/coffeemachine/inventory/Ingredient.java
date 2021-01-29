package com.dunzo.coffeemachine.inventory;

public class Ingredient {

    int ingredientId;
    String ingredientName;
    double availableQuantity;

    public double getAvailableQuantity() {
        return this.availableQuantity;
    }

    public void refillIngredient(double quantity) {
        this.availableQuantity +=  quantity;
    }

    public void useIngredient(double qty) {
        this.availableQuantity -= qty;

    }

    public int getIngredientId() {
        return ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

   public Ingredient(String ingredientName,double availableQuantity){
        this.ingredientName = ingredientName;
        this.availableQuantity = availableQuantity;
    }
}
