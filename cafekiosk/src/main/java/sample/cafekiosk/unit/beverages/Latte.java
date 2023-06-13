package sample.cafekiosk.unit.beverages;

import sample.cafekiosk.unit.beverages.Beverage;

public class Latte implements Beverage {

    @Override
    public String getName() {
        return "라떼";
    }

    @Override
    public int getPrice() {
        return 4500;
    }
}
