package com.dunzo.coffeemachine;

import com.dunzo.coffeemachine.beverages.*;
import com.dunzo.coffeemachine.inventory.*;

import java.util.*;

public class CoffeeMachine {
    List<Beverage> allBeverages;
    int noOfOutlets;
    boolean[] freeOutlets;
    private static final int DURATION = 5000;


    public String startServingBeverage(Beverage beverage, Inventory inventory) throws Exception {
        if(beverage == null){
            throw new Exception("No Such Beverage Found");
        }
        int emptyOutlet = getEmptyOutlet();
        if(emptyOutlet>-1 && emptyOutlet<noOfOutlets){
            beverageBeingServed(emptyOutlet);
            return beverage.serveBeverage(beverage.getBeverageName(),inventory);
        }
        return null;
    }

    public void beverageBeingServed(int emptyOutlet){
        new Thread(() -> {
            freeOutlets[emptyOutlet] = false;
            try {
                Thread.sleep(DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            freeOutlets[emptyOutlet] = true;

        }).start();
    }

    public int getEmptyOutlet(){
        for(int i = 0; i<noOfOutlets; i++){
            if(freeOutlets[i])
                return i;
        }
        return -1;
    }

    public void setAllBeverages(List<Beverage> allBeverages) {
        this.allBeverages = allBeverages;
    }

    public void addNewBeverages(Beverage beverage) {
        this.allBeverages.add(beverage);
    }

    public int getNoOfOutlets() {
        return noOfOutlets;
    }

    public void setNoOfOutlets(int noOfOutlets) {
        this.noOfOutlets = noOfOutlets;
        freeOutlets = new boolean[noOfOutlets];
        for(int i = 0; i<noOfOutlets; i++){
            freeOutlets[i] = true;
        }
    }
}
