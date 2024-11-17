package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This is where items to be used by the vending machine are stored.
 * This is also where operations on items, such as dispensing, are done.
 */
public class Inventory {
    private ArrayList<Item> inStock = new ArrayList<>();


    private static final int MAX_PER_SLOT = 20;

    /**
     * This adds a certain amount of a given item to the inventory.
     * @param item the item to be added
     * @param quantity how much of the item to be added
     */
    public void addItem(Item item, int quantity){
        if (quantity <= 0){
            throw new IllegalArgumentException("Invalid number of items!");
        }

        if (item.getCalories() < 0){
            System.err.println("Invalid calories! ");
            throw new IllegalArgumentException("Invalid calories!");
        }

        if (item.getPrice() < 0){
            System.err.println("Invalid price! ");
            throw new IllegalArgumentException("Invalid price!");
        }
        for (int i = 0; i < quantity; i++){
            if (this.getQuantity(item.getName()) >= MAX_PER_SLOT) {
                break;
            }
            this.inStock.add(item);
        }
    }

    /**
     * Returns the ArrayList that stores the items currently in the inventory.
     * @return the ArrayList that stores the items currently in the inventory
     */
    public ArrayList<Item> getInStock(){ return inStock; }

    /**
     * Given a String, this method finds the first instance of an item in the current inventory stock whose name matches the String.
     * @param itemName the name of the item to be retrieved.
     * @return The first instance of an item in the inventory stock whose name matches the given String.
     */
    public Item findFirst(String itemName) { //If you want to buy the item, you use this method to find that first item that you want

        for (int i = 0; i < inStock.size(); i++) {
            if (itemName.equals(inStock.get(i).getName())) {
                return inStock.get(i);
            }
        }
        throw new IllegalArgumentException("The vending machine does not have that item.");
    }


    /**
     * Returns the price of an item in the inventory whose name matches a String.
     * @param itemName the name of the item
     * @return The price of such aforementioned item.
     */
    public double getPrice(String itemName){
        return this.findFirst(itemName).getPrice();
    }

    /**
     * Returns the amount of calories of an item in the inventory whose name matches a String.
     * @param itemName name of the item
     * @return The amount of calories contained in the aforementioned item.
     */
    public double getCalories(String itemName){
        return this.findFirst(itemName).getCalories();
    }

    /**
     * Returns how many of a certain item, identified by its name via a String, currently exists within the inventory's stock.
     * @param itemName name of the item
     * @return The number of a certain item in the current stock.
     */
    public int getQuantity(String itemName){
        int quantity = 0;
        for (int i = 0; i < inStock.size(); i++){
            if (itemName.equals(inStock.get(i).getName())){
                quantity += 1;
            }
        }
        return quantity;
    }

    /**
     * Counts all the products that we have in our vending machine.
     * @return The number of unique items currently in stock.
     */
    public int getUniqueItemCount(){
        return getUniqueItemNames().size();
    }

    /**
     * Gets the list of all the names (without duplicates) of all items in stock.
     * @return an ArrayList of Strings containing the list of names of each item in stock (no duplicates).
     */
    public ArrayList<String> getUniqueItemNames(){
        ArrayList<String> uniqueNames = new ArrayList<>();
        for (Item item: this.inStock){
            String nameOfItem = item.getName();
            if (!uniqueNames.contains(nameOfItem)){
                uniqueNames.add(nameOfItem);
            }
        }
        return uniqueNames;
    }

    /**
     * Dispenses an item from the stock.
     * @param name name of the item
     */
    public void dispenseItem(String name){
        this.inStock.remove(findFirst(name));
    }
}
