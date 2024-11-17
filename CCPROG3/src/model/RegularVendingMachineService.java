package model;

import java.util.ArrayList;

 /**
 * A model.VendingMachineService represents the service for a VendingMachine where it covers all the functionalities of a VendingMachine.
 * It includes cash register, summary, and inventory, if it is operational, and cart (list of items), as components of a model.VendingMachineService, in order.
 */
public class RegularVendingMachineService {

	protected CashRegister cashRegister;
	protected Summary summary;
	protected Inventory inventory;
	protected boolean isOperational = false;
	protected ArrayList<Item> cart = new ArrayList<>();

	/**
	 * creates a model.VendingMachineService object by also creating inventory, cashRegister, summary, and cart, in order.
	 */

	public RegularVendingMachineService() {
		this.inventory = new Inventory();
		this.cashRegister = new CashRegister();
		this.summary = new Summary(this.inventory);
		this.cart = new ArrayList<>();
	}

	/**
	 * begins the service if the conditions are met: Minimum slots, minimum quantity per item.
	 * the "if (!this.isOperational)" ensures that it can only begin once
	 * then it sets the isOperational to true.
	 */
	public void begin(){

		if (this.inventory.getUniqueItemCount() <= 8){
			throw new IllegalArgumentException("The minimum slots is not satisfied.");
		}

		for (String itemName: this.inventory.getUniqueItemNames()){
			if (this.inventory.getQuantity(itemName) < 10){
				throw new IllegalArgumentException("The minimum quantity per item requirement is not satisfied.");
			}
		}
		
		if (!this.isOperational) {
			this.summary.setInitialInventory(this.inventory.getInStock());
		} 
		
		this.isOperational = true;
	}

	/**
	 * returns whether the service is operational or not.
	 * @return whether the service is operational or not.
	 */
	public boolean getIsOperational(){ return this.isOperational; }

	/**
	 * returns the inventory of the vending machine.
	 * @return the inventory of the bending machine.
	 */
	public Inventory getInventory(){ return this.inventory; }

	/**
	 * returns the cash register of the vending machine.
	 * @return the cash register of the vending machine.
	 */
	public CashRegister getCashRegister(){ return this.cashRegister; }

	/**
	 * returns the summary of the vending machine.
	 * @return the summary of the vending machine.
	 */
	public Summary getSummary(){ return this.summary; }

	/**
	 * adds item to the cart.
	 * @param name of the item.
	 * @param quantity of the item.
	 */
	public void addToCart(String name, int quantity) {

            Item item = this.inventory.findFirst(name);

			if (quantity <= 0) {
				throw new IllegalArgumentException("Invalid quantity");
			}
            if (quantity > this.inventory.getQuantity(name)){
                throw new IllegalArgumentException("Not enough quantity for the requested item");
            }
            for (int i = 0; i < quantity; i++){
                this.cart.add(item);
        }
    }
	/**
	 * returns the list of items in the cart.
	 * @return the list of items in the cart.
	 */
	public ArrayList<Item> getCart(){ return this.cart; }

	/**
	 * clears the cart.
	 */
	public void clearCart(){
		this.cart = new ArrayList<>();
	}

	/**
	 * returns the value of the total cost in the cart.
	 * @return the value of the total cost in the cart.
	 */
	public double getTotalCostInCart(){
		double total = 0;
		for (int i = 0; i < this.cart.size(); i++){
			total += this.cart.get(i).getPrice();
		}
		return total;
	}


	/**
	 * returns the change (list of denominations) after paying for the cart.
	 * @param denominations of the user.
	 * @return the change (list of denominations) after paying for the cart.
	 */
	public ArrayList<Denomination> payForCart(ArrayList<Denomination> denominations) {
		ArrayList<Item> cart1 = this.cart;
		double priceOfCart = this.getTotalCostInCart();
		ArrayList<Denomination> changeList = this.cashRegister.transact(denominations, priceOfCart);

		for (int i = 0; i < this.cart.size(); i++){
			this.getInventory().dispenseItem(this.cart.get(i).getName());
		}

		this.summary.recordSales(cart1);

		return changeList;
	}


}


