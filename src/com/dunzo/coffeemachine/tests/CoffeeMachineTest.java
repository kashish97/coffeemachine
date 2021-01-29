package com.dunzo.coffeemachine.tests;

import com.dunzo.coffeemachine.beverages.*;
import com.dunzo.coffeemachine.inventory.*;
import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeMachineTest {

    Inventory inventory = new Inventory();

    public void fillInventory() {
        Ingredient ingredient = new Ingredient("water", 100);
        Ingredient ingredient2 = new Ingredient("sugar", 100);
        Ingredient ingredient3 = new Ingredient("milk", 70);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);
        inventory.setIngredients(ingredients);
    }

    @Test
    void startServingBeverage() {

        fillInventory();
        Map<Ingredient, Double> map = new HashMap<>();
        if (inventory != null) {
            map.put(inventory.getIngredients().get(0), 200.00);
            map.put(inventory.getIngredients().get(1), 50.00);
        }
        Beverage beverage = new Beverage("juice",map);

        try {
            String beverageBool = beverage.serveBeverage(beverage.getBeverageName(), inventory);
            Assert.assertEquals("juice cannot be prepared because water is not sufficient", beverageBool);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}