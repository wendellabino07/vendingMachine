

package model;
import java.util.ArrayList;
import java.util.Collections;

	/**
	 * A CashRegister represents the cash register that is inside the VendingMachineService.
	 * The difference is that this CashRegister is inside the VendingMachineService.
	 * for which it is responsible for all the transactions, namely adding cash, receiving payments,
	 * dispensing change, and so on. It includes the list(cashList) of the cash(Denomination) in the CashRegister,
	 * here all the denominations are stored.
	 */
public class CashRegister {
	private ArrayList<Denomination> cashList = new ArrayList<>();

	/**
	 * adds denomination/s into the CashRegister, with the given Denomination type, and the quantity of the
	 * Denomination type
	 * @param type Denomination type for the cash register.
	 * @param copies quantity of the denomination type.
	 */
	public void addCash(Denomination type, int copies){
		if (copies <= 0){
			System.err.println("Invalid quantity! ");
			throw new IllegalArgumentException("Invalid quantity! ");
		}

		for (int i = 0 ; i < copies; i++){
			cashList.add(type);
		}
	}

	/**
	 * collects all cash from the cash register.
	 */
	public double collectAllCash() {
		double totalCash = 0;
		for (Denomination cash: cashList){
			totalCash += cash.getValue();
		}
		this.cashList = new ArrayList<>();
		return totalCash;
	}

	/**
	 * the user transacts with the cash register of the vending machine.
	 * @param payment denominations of the user to be inserted in the vending machine.
	 * @param priceOfItem the price of the item to be bought.
	 * @return the list of change of the user in denominations.
	 */
	public ArrayList<Denomination> transact(ArrayList<Denomination> payment, double priceOfItem){
		double totalPayment = 0;

		for (Denomination cash: payment){
			totalPayment += cash.getValue();
		}
		// if yung pinambayad ko mas maliit sa babayaran, di dapat pwede
		if (totalPayment < priceOfItem){
			throw new IllegalArgumentException("You are broke");
		}
		double totalChange = totalPayment - priceOfItem;
		ArrayList<Denomination> changeList = this.getChange(totalChange, payment);
		return changeList;
	}

	/**
	 * as the user transacts with the cash register of the vending machine, the method transact.
	 * uses the getChange as a helper method where it returns the change of the user.
	 * @param totalChange the total change of the user.
	 * @param payment the payment of the user in denominations.
	 * @return the list of change of the user in denominations.
	 */
	public ArrayList<Denomination> getChange(double totalChange, ArrayList<Denomination> payment){
		ArrayList<Denomination> temporaryCash = new ArrayList<>();
		temporaryCash.addAll(cashList);
		temporaryCash.addAll(payment);
		Collections.sort(temporaryCash);
		ArrayList<Denomination> changeList = new ArrayList<>();

		for (Denomination cash: temporaryCash){
			if (cash.getValue() <= totalChange){
				changeList.add(cash);
				totalChange -= cash.getValue();
			}
		}
		if (totalChange > 0){
			throw new IllegalArgumentException("Not enough change in the vending machine.");
		}
		cashList = temporaryCash;

		for (Denomination change: changeList){
			cashList.remove(change);
		}
		return changeList;
	}
}