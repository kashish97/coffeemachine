package com.dunzo.coffeemachine;

import com.dunzo.coffeemachine.inventory.*;
import com.dunzo.coffeemachine.jsonparser.*;

import java.util.*;

public class Main {

    public static Inventory inventory;

    public static void main(String[] args) {

        inventory = new Inventory();
        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to do? (Enter Integer)\n 1.Upload Input JSON 2. Refill Ingredient");
        int input = sc.nextInt();
        switch (input) {
            case 1 -> {
                System.out.println("Please Enter the test file location");
                String file_location = sc.next();
                System.out.println(JsonParser.parseJson(file_location, inventory));
            }
            case 2 -> {
                System.out.println("Enter Ingredient Name");
                String ingName = sc.next();
                if (inventory == null || inventory.getIngredients() == null || inventory.getIngredients().size() == 0) {
                    System.out.println("Kindly Upload input File First");
                } else {
                    Ingredient ing = inventory.getIngredients().stream().filter(i -> i.getIngredientName().equalsIgnoreCase(ingName)).findAny().orElse(null);
                    if (ing != null) {
                        System.out.println("Enter Quantity(in double)");
                        double qty = Double.parseDouble(sc.next());
                        ing.refillIngredient(qty);
                    } else {
                        System.out.println("No Such Ingredient Found");
                    }
                }
            }
        }
    }
}
