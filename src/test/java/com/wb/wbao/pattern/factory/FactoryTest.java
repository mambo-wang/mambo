package com.wb.wbao.pattern.factory;

public class FactoryTest {


    public static void main(String[] args) {
        PizzaStore store = new DezhouPizzaStore();

        store.orderPizza("dezhou");
    }
}
