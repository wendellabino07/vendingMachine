package javaswing;

import model.Item;
import model.SpecialVendingMachineService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpecialVendingMachineView extends RegularVendingMachineView{

    private String coffeeRoast = "";
    private boolean isBuyingCoffee = false;

    public SpecialVendingMachineView(MainFrame frame) {
        super(frame);
        setTitle("Special Vending Machine");

        super.addProductButton.setEnabled(false);

        buyCustomCoffeeButton.setEnabled(false);

        this.buyCustomCoffeeButton();
    }

    public boolean isBuyingCoffeeStatus() {
        return this.isBuyingCoffee;
    }

    public void setBuyingCoffeeStatus(boolean status) {
        this.isBuyingCoffee = status;
    }
    public String getRoast() {
        return this.coffeeRoast;
    }

    public void setRoast(String roast) {
        this.coffeeRoast = roast;
    }

    public SpecialVendingMachineService getSpecialVendingMachineService() {
        //if (this.vendingMachineService instanceof SpecialRegularVendingMachineService) {
            return (SpecialVendingMachineService) this.vendingMachineService;
        //} else {
        //    return null;
       // }

    }

    @Override
    protected void initService(){
        this.vendingMachineService = new SpecialVendingMachineService();
    }

    @Override
    protected void prepopulateProductsButton() {
        super.prepopulateProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Regular items
                Item item1 = new Item("Kopiko", 20.00, 139.0);
                Item item2 = new Item("Great Taste Coffee", 20.00, 139.0);
                Item item3 = new Item("Remus Coffee", 40.00, 43.5);
                Item item4 = new Item("Wendell Coffee", 40.00, 43.0);
                Item item5 = new Item("St. Miguel Coffee", 69.00, 160.0);


                // Special items

                // Beans
                Item item9 = new Item("Arabica", 30.00,33.0,false);
                Item item10 = new Item("Robusta", 30.00,33.0,false);
                Item item11 = new Item("Excelsa", 30.00,33.0,false);
                Item item12 = new Item("Liberica", 30.00, 35.0, false);



                // Sugar
                Item s100 = new Item("100% Sugar", 20d, 20d, false);
                Item s50 = new Item("50% Sugar", 10d, 10d, false);
                Item s25 = new Item("25% Sugar", 5d, 5d, false);
                //Item s0 = new Item("0% Sugar", 0, 0, false, true);

                // Misc
                Item itemMisc1 = new Item("Creamer", 5.00, 5.0, false);
                Item itemMisc2 = new Item("Dark Chocolate", 50.00, 100.0, true);
                Item itemMisc3 = new Item("White Chocolate", 50.00, 250.0, true);
                Item itemMisc4 = new Item("Chocolate Syrup", 20.00, 279.0, false);
                Item itemMisc5 = new Item("Vanilla Ice Cream", 20.00, 20.0, true);
                Item itemMisc6 = new Item("Caramel", 10.00, 10.0, false);
                Item itemMisc7 = new Item("Whipped Cream", 5.00, 5.0, false);
                Item itemMisc8 = new Item("Sprinkles", 5.00, 5.0, false);


                vendingMachineService.getInventory().addItem(item1, 10);
                vendingMachineService.getInventory().addItem(item2, 10);
                vendingMachineService.getInventory().addItem(item3, 10);
                vendingMachineService.getInventory().addItem(item4, 10);
                vendingMachineService.getInventory().addItem(item5, 10);


                vendingMachineService.getInventory().addItem(item9, 10);
                vendingMachineService.getInventory().addItem(item10, 10);
                vendingMachineService.getInventory().addItem(item11, 10);
                vendingMachineService.getInventory().addItem(item12, 10);



                vendingMachineService.getInventory().addItem(s100, 10);
                vendingMachineService.getInventory().addItem(s50, 10);
                vendingMachineService.getInventory().addItem(s25, 10);
                //vendingMachineService.getInventory().addItem(s0, 10);

                vendingMachineService.getInventory().addItem(itemMisc1, 10);
                vendingMachineService.getInventory().addItem(itemMisc2, 10);
                vendingMachineService.getInventory().addItem(itemMisc3, 10);
                vendingMachineService.getInventory().addItem(itemMisc4, 10);
                vendingMachineService.getInventory().addItem(itemMisc5, 10);
                vendingMachineService.getInventory().addItem(itemMisc6, 10);
                vendingMachineService.getInventory().addItem(itemMisc7, 10);
                vendingMachineService.getInventory().addItem(itemMisc8, 10);


                JOptionPane.showMessageDialog(SpecialVendingMachineView.this, "Success! ", "", JOptionPane.INFORMATION_MESSAGE);
                drawInventory();
            }
        });
    }

    @Override
    protected void openToPublicButton(){
        openToPublicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    vendingMachineService.begin();
                    addToCartButton.setEnabled(true);
                    payAndCheckoutButton.setEnabled(true);
                    viewCartButton.setEnabled(true);
                    clearCartButton.setEnabled(true);
                    buyCustomCoffeeButton.setEnabled(true);
                    JOptionPane.showMessageDialog(SpecialVendingMachineView.this, "Machine is now operational. ", "", JOptionPane.INFORMATION_MESSAGE);


                } catch (Exception d){
                    JOptionPane.showMessageDialog(SpecialVendingMachineView.this, "Machine is not yet operational. ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


    }

    protected void buyCustomCoffeeButton() {
        buyCustomCoffeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isBuyingCoffee = true;
                addToCartButton.setEnabled(false);
                new BuySpecialCoffeeView(SpecialVendingMachineView.this);
                buyCustomCoffeeButton.setEnabled(false);
            }
        });
    }

    @Override
    protected void payAndCheckoutButton(){

        payAndCheckoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PayAndCheckoutView(SpecialVendingMachineView.this);
                buyCustomCoffeeButton.setEnabled(true);
                addToCartButton.setEnabled(true);
                drawInventory();

            }
        });
    }
}
