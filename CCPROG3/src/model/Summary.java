package model;

import java.util.ArrayList;

/**
 * This is responsible for keeping track of the vending machine's initial inventory and all the purchases made,
 * counting the items that have been sold and how much money has been made in total.
 */
public class Summary {

	private ArrayList<Item> initialInventory = new ArrayList<>();
	private Inventory inventory;
	private ArrayList<Item> sales = new ArrayList<>();


	/**
	 * Creates an instance of a summary. Usually done only once when a vending machine is ready to be made public.
	 * @param inventory The inventory of a vending machine that the Summary should be taking track of.
	 */
	public Summary(Inventory inventory){
		this.inventory = inventory;
	}


	/**
	 * A setter for the initial inventory so that information regarding the inventory that a vending machine started with would not be lost.
	 * @param inStock An ArrayList of Items that represents the first ever stock of a vending machine.
	 */
	public void setInitialInventory(ArrayList<Item> inStock) {
		this.initialInventory = new ArrayList<>();
		this.initialInventory.addAll(inStock);
	}

	/**
	 * Takes track of which items have been sold.
	 * @param dispensedItems Items that have been sold to customers.
	 */
	public void recordSales(ArrayList<Item> dispensedItems){
		this.sales.addAll(dispensedItems);
	}

	/**
	 * Computes how much money has been made by the vending machine.
	 * @return The total money earned (in Philippine Peso).
	 */
	public double computeTotalEarnings(){
		double total = 0;
		for (Item item : sales) {
			total += item.getPrice();
		}
		return total;
	}

	/**
	 * Computes how many of a certain item, identified by its name, has been sold.
	 * @param itemName The name of the item in question.
	 * @return The number of times and item has been sold.
	 */
	public int getQuantitySold(String itemName){
		int count = 0;

		for (Item soldItems : sales) {
			if (itemName.equals(soldItems.getName())) {
				count++;
			}
		}
		return count;
	}


	/**
	 * Gets the names of all items that have been sold.
	 * @return An ArrayList of String containing all the names of items that have been sold without duplicates.
	 */
	public ArrayList<String> getUniqueItemNamesInSales(){
		ArrayList<String> uniqueNames = new ArrayList<>();
		for (Item item: this.sales){
			String nameOfItem = item.getName();

				uniqueNames.add(nameOfItem);

		}
		return uniqueNames;
	}

	/**
	 * Gets the names of all items in the initial inventory.
	 * @return An ArrayList of String containing all the names of items in the initial inventory without duplicates.
	 */
	public ArrayList<String> getUniqueItemsIniInventory() {
		ArrayList<String> unique = new ArrayList<String>();

		for (Item item : this.initialInventory) {
			String nameOfItem = item.getName();
			if (!unique.contains(nameOfItem)) {
				unique.add(nameOfItem);
			}
		}
		return unique;
	}

	/**
	 * Counts how many of a given item, identified by its name, was in the initial inventory.
	 * @param itemName The name of the item in queston.
	 * @return How many of that item was in the initial inventory.
	 */
	public int countItemInInitialInventory(String itemName) {
		int count = 0;
		for (var ini : this.initialInventory) {
			if (itemName.equals(ini.getName())) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Finds the first instance of an item in the initial inventory given its name
	 * @param itemName The name of the item to be searched for
	 * @return the first instance of the item in the initial inventory with the given name.
	 */
	public Item findFirst(String itemName) { //If you want to buy the item, you use this method to find that first item that you want

		for (int i = 0; i < this.initialInventory.size(); i++) {
			if (itemName.equals(this.initialInventory.get(i).getName())) {
				return initialInventory.get(i);
			}
		}
		throw new IllegalArgumentException("The vending machine does not have that item.");
	}


}
