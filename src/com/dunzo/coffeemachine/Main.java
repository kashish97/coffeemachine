package com.dunzo.coffeemachine;

import com.dunzo.coffeemachine.inventory.*;
import com.dunzo.coffeemachine.jsonparser.*;

import java.util.*;

public class Main {

    public static Inventory inventory;

    public static void main(String[] args) {

        inventory = new Inventory();
        Scanner sc = new Scanner(System.in);

        System.out.println("Please Enter the test file location");
        String file_location = sc.next();
        System.out.println(JsonParser.parseJson(file_location, inventory));

        }
    }

