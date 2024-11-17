package model;

/**
 * A model.SpecialVendingMachineService's difference to a RegularVendingMachine is that it has a customizability feature.
 * It includes cash register, summary, and inventory, if it is operational, and cart (list of items), as components of a model.SpecialVendingMachineService, in order.
 */
public class SpecialVendingMachineService extends RegularVendingMachineService {

    /**
     * Adds item to the cart
     * @param name of the item
     * @param quantity of the item
     */
    @Override
    public void addToCart(String name, int quantity) {

        Item item = super.inventory.findFirst(name);

        if (!item.isSellable()){
            throw new IllegalArgumentException("Not sellable. ");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Invalid quantity");
        }
        if (quantity > super.inventory.getQuantity(name)){
            throw new IllegalArgumentException("Not enough quantity for the requested item");
        }
        for (int i = 0; i < quantity; i++){
            super.cart.add(item);
        }
    }

    /**
     * Adds special item to cart
     * @param name of the item
     * @param quantity of the item
     */
    public void addSpecialItemToCart(String name, int quantity) {

        Item item = super.inventory.findFirst(name);

        if (quantity > super.inventory.getQuantity(name)){
            throw new IllegalArgumentException("Not enough quantity for the requested item");
        }
        for (int i = 0; i < quantity; i++){
            super.cart.add(item);
        }
    }
}

