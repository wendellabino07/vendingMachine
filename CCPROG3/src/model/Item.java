package model;


/**
 * This represents an item that would be sold in a vending machine.
 * An item, of course, has a name, a price (in Philippine Peso), and
 * the amount of calories it has (assuming it is a food item).
 */
public class Item {
    private String name;
    private double price;
    private double calories;

    private boolean isSellable;




    /**
     * Creates an instance of an Item
     * @param name The name of the item
     * @param price How much the item costs
     * @param calories How much calories the item contains
     */
    public Item(String name, double price, double calories) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.isSellable = true;
    }

    /**
     * Creates an instance of a Special Item
     * @param name The name of the item
     * @param price How much the item costs
     * @param calories How much calories the item contains
     * @param isSellable If special
     */
    public Item(String name, double price, double calories, boolean isSellable) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.isSellable = isSellable;

    }

    /**
     * Returns the name of the item
     * @return The name of the item instance
     */
    public String getName(){return name;}

    /**
     * Returns the price of the item
     * @return The price of the item instance
     */
    public double getPrice(){return price;}

    /**
     * Returns the calorie count of the item
     * @return The calorie count of the item instance
     */
    public double getCalories() { return calories; }

    /**
     * Returns isSpecial truth value
     * @return isSpecial
     */
    public boolean isSellable() {
        return isSellable;
    }

}
