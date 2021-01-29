package com.dunzo.coffeemachine.jsonparser;

import com.dunzo.coffeemachine.*;
import com.dunzo.coffeemachine.beverages.*;
import com.dunzo.coffeemachine.inventory.*;
import com.google.gson.*;
import org.json.simple.*;

import java.io.*;
import java.util.*;

public class JsonParser {



    public static String parseJson(String fileLocation, Inventory inventory) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        List<Ingredient> ingredients = new ArrayList<>();
        List<Beverage> beverageList = new ArrayList<>();

        try {

            Object obj = new org.json.simple.parser.JSONParser().parse(new FileReader(fileLocation));
            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            Map machine = (Map) jo.get("machine");
            Iterator<Map.Entry> itr3 = machine.entrySet().iterator();

            Map outlets = (Map) machine.get("outlets");
            double outlet = (long) outlets.get("count_n");
            coffeeMachine.setNoOfOutlets((int) outlet);

            Map Inventory = (Map) machine.get("total_items_quantity");
            Iterator<Map.Entry> items = Inventory.entrySet().iterator();

            while (items.hasNext()) {
                Map.Entry pair = items.next();
                String name = (String) pair.getKey();
                double quantity = ((Number) pair.getValue()).doubleValue();
                Ingredient ing = new Ingredient(name, quantity);
                ingredients.add(ing);

            }

            inventory.setIngredients(ingredients);
            JSONObject object = (JSONObject) machine.get("beverages");
            LinkedHashMap beverages = new Gson().fromJson(String.valueOf(object), LinkedHashMap.class);
            Iterator<Map.Entry> beverageMap = beverages.entrySet().iterator();

            while (beverageMap.hasNext()) {

                boolean canBePrepared = true;
                Map.Entry pair = beverageMap.next();
                String drink = (String) pair.getKey();
                Map<Ingredient, Double> ingredientMap = new HashMap<>();
                Map ingred = (Map) beverages.get(drink);
                Iterator<Map.Entry> itr = ingred.entrySet().iterator();
                while (itr.hasNext()) {
                    Map.Entry pair1 = itr.next();
                    String name = (String) pair1.getKey();
                    double quantity = ((Number) pair1.getValue()).doubleValue();
                    Optional<Ingredient> ing = inventory.getIngredients().stream().filter(i -> i.getIngredientName().equalsIgnoreCase(name)).findAny();
                    if (ing.isPresent()) {
                        ingredientMap.put(ing.get(), quantity);
                    }
                    else{
                        canBePrepared = false;
                        System.out.println(drink+" cannot be prepared because "+name+" is not available");
                    }
                }

                if(canBePrepared) {
                    Beverage beverage = new Beverage(drink, ingredientMap);
                    beverageList.add(beverage);
                    System.out.println(coffeeMachine.startServingBeverage(beverage, inventory));
                    System.out.println("Do you want to refill any ingredient(y/n)");
                    Scanner s = new Scanner(System.in);
                    String out = s.next();
                    if(out.equalsIgnoreCase("y")){
                        System.out.println("Enter Ingredient Name");
                        String ingName = s.next();
                        if (inventory == null || inventory.getIngredients() == null || inventory.getIngredients().size() == 0) {
                            System.out.println("Kindly Upload input File First");
                        } else {
                            Ingredient ing = inventory.getIngredients().stream().filter(i -> i.getIngredientName().equalsIgnoreCase(ingName)).findAny().orElse(null);
                            if (ing != null) {
                                System.out.println("Enter Quantity(in double)");
                                double qty = Double.parseDouble(s.next());
                                ing.refillIngredient(qty);
                            } else {
                                System.out.println("No Such Ingredient Found");
                            }
                        }
                    }

                }
            }

            coffeeMachine.setAllBeverages(beverageList);

        } catch (NullPointerException e) {
            return"Json Keys/Format is incorrect...\nExiting ...";
        } catch (FileNotFoundException e) {
            System.out.println("File not Found. Try again...");
            return "File not Found. Try again...";
        } catch (IOException e) {
            return ("IO exception. Please try again !!!");
        } catch (org.json.simple.parser.ParseException e) {
            return ("Cannot Parse Json Input, invalid Json provided");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "File read successfully";

    }
}
