package com.wb.wbao.pattern.factory;

import java.util.Objects;

public class DezhouPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        if(Objects.equals(type, "dezhou")){
            return new DezhouPizza();
        }else {
            return new HangzhouPizza();
        }
    }
}
