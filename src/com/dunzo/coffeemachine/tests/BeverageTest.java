package com.dunzo.coffeemachine.tests;

import com.dunzo.coffeemachine.beverages.*;
import com.dunzo.coffeemachine.inventory.*;
import org.junit.*;
import org.junit.jupiter.api.Test;


import java.util.*;


class BeverageTest {

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

    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }


    @org.junit.jupiter.api.Test
    void serveBeverageNegative() {
        fillInventory();
        Map<Ingredient, Double> map = new HashMap<>();
        if (inventory != null) {
            map.put(inventory.getIngredients().get(0), 50.00);
            map.put(inventory.getIngredients().get(1), 200.00);
        }


        Beverage beverage = new Beverage("boiled_water", map);
        try {
            String beverageBool = beverage.serveBeverage(beverage.getBeverageName(), inventory);
            Assert.assertEquals("boiled_water cannot be prepared because sugar is not sufficient", beverageBool);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void serveBeveragePositive() {
        fillInventory();
        Map<Ingredient, Double> map = new HashMap<>();
        if (inventory != null) {
            map.put(inventory.getIngredients().get(0), 50.00);
            map.put(inventory.getIngredients().get(1), 50.00);
        }


        Beverage beverage = new Beverage("boiled_water", map);
        try {
            String beverageBool = beverage.serveBeverage(beverage.getBeverageName(), inventory);
            Assert.assertEquals("boiled_water is prepared", beverageBool);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void notAvailableTest() {
        fillInventory();
        Map<Ingredient, Double> map = new HashMap<>();

        Ingredient ingredient = new Ingredient("potato", 20);

        map.put(ingredient, 30.00);

        Beverage beverage = new Beverage("boiled_water", map);
        try {
            String beverageBool = beverage.serveBeverage(beverage.getBeverageName(), inventory);
            Assert.assertEquals("boiled_water cannot be prepared because potato is not available", beverageBool);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}