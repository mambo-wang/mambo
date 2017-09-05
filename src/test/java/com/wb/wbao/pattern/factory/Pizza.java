package com.wb.wbao.pattern.factory;

import java.util.ArrayList;

public abstract class Pizza {

    String name;
    String dough;
    String sauce;
    ArrayList topping = new ArrayList();

    void prepare(){
        System.out.println("preparing " + name);
        System.out.println("Tossing dough" + dough);
        System.out.println("Adding sauce" + sauce);
        System.out.println("Adding topping");
        topping.forEach(System.out::println);
    }

    void bake() {
        System.out.println("bake for 25 minutes at 350");
    }

    void cut() {
        System.out.println("cutting the pizza into diagonal slices");
    }

    void box() {
        System.out.println("place pizza in offcial pizzaStore box");
    }

    public String getName(){
        return name;
    }
}
