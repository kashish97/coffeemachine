package com.dunzo.coffeemachine.beverages;

import com.dunzo.coffeemachine.inventory.*;

import java.util.*;

public class Beverage {

    String beverageName;
    Map<Ingredient, Double> ingQtyMap;

    public String getBeverageName() {
        return beverageName;
    }

    public String serveBeverage(String beverageName,Inventory inventory) throws Exception {

        for (Map.Entry<Ingredient, Double> entry : ingQtyMap.entrySet()){
            Ingredient ingredient = entry.getKey();
            if(!inventory.getIngredients().contains(ingredient))
                return beverageName+" cannot be prepared because "+ingredient.getIngredientName()+" is not available";

            if(ingredient.getAvailableQuantity()<entry.getValue()){
                return beverageName+" cannot be prepared because "+ingredient.getIngredientName()+" is not sufficient";
            }

            ingredient.useIngredient(entry.getValue());
        }
        return beverageName+" is prepared";
    }

    public Beverage(String beverageName,Map<Ingredient,Double> ingredientQuantity){
        this.beverageName = beverageName;
        this.ingQtyMap = ingredientQuantity;
    }
}
