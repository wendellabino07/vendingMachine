package javaswing;

import model.Denomination;
import model.Item;
import model.RegularVendingMachineService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

public class RegularVendingMachineView extends JFrame {
    private JPanel vendingMachinePanel;
    protected JButton payAndCheckoutButton;
    protected JButton clearCartButton;
    protected JButton addToCartButton;
    private JButton addQuantityButton;
    private JButton collectAllMoneyButton;
    protected JButton openToPublicButton;
    protected JButton addProductButton;
    protected JButton prepopulateProductsButton;
    private JButton addCashButton;
    private JButton prepopulateCashRegisterButton;
    private JTable inventoryTable;
    private JScrollPane inventoryScrollPane;
    protected JButton viewCartButton;
    protected JButton buyCustomCoffeeButton;
    private JButton printSummaryOfTransactionsButton;
    protected RegularVendingMachineService vendingMachineService;

    public RegularVendingMachineService getVendingMachineService() {
        return vendingMachineService;
    }


    public RegularVendingMachineView(MainFrame frame){
//        UIManager.put("OptionPane.minimumSize", new Dimension(300, 200));
        this.initService();
        this.initTable();

        setContentPane(vendingMachinePanel);
        setTitle("Regular Vending Machine");
        setSize(1000, 600);
        setVisible(true);


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(true);
            }
        });


        // Vending Features

        this.addToCartButton();

        this.payAndCheckoutButton();

        this.clearCartButton();

        this.viewCartButton();

        // Maintenance Features
         // Manage Items

            // Prepopulate Products
        this.prepopulateProductsButton();

            // Add Product Manually
        this.addProductButton();

            // Add Quantity
        this.addQuantityButton();

         // Manage Money

            // Prepopulate Cash Register
        this.prepopulateCashRegisterButton();

            // Add Cash Manually
        this.addCashButton();

            // Collect All Money
        this.collectAllMoneyButton();

         // Others
            // Print Summary of Transactions
        this.printSummaryOfTransactions();


            // Open to Public
        this.openToPublicButton();


    }

    protected void initService(){
        this.vendingMachineService = new RegularVendingMachineService();
    }

    // Vending Features


    protected void addToCartButton(){
        addToCartButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                buyCustomCoffeeButton.setEnabled(false);
                new AddToCartView(RegularVendingMachineView.this);
            }
        });
    }




    protected void payAndCheckoutButton(){

        payAndCheckoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PayAndCheckoutView(RegularVendingMachineView.this);
                drawInventory();

            }
        });
    }

    protected void viewCartButton() {
        viewCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = "Cart:";
                double totalP = 0;
                double totalCal = 0;
                for (var c : vendingMachineService.getCart()) {
                    msg += "\n" + c.getName();
                    totalP += c.getPrice();
                    totalCal += c.getCalories();
                }

                msg += "\n\nTotal Price: " + totalP;
                msg += "\nTotal Calories: " + totalCal;


                JOptionPane.showMessageDialog(RegularVendingMachineView.this, msg, "", JOptionPane.INFORMATION_MESSAGE);

            }
        });
    }


    protected void clearCartButton(){
        clearCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCartButton.setEnabled(true);
                if (RegularVendingMachineView.this instanceof SpecialVendingMachineView){
                    buyCustomCoffeeButton.setEnabled(true);
                }
                vendingMachineService.clearCart();
                JOptionPane.showMessageDialog(RegularVendingMachineView.this, "Cart cleared! ", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

    // Maintenance Features

        // Manage Items
    protected void prepopulateProductsButton(){
        prepopulateProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item1 = new Item("Coke", 20.00, 139.0);
                Item item2 = new Item("Pepsi", 20.00, 139.0);
                Item item3 = new Item("St. Miguel Beer", 40.00, 43.5);
                Item item4 = new Item("St. Joseph Beer", 45.00, 43.0);
                Item item5 = new Item("Cheetos", 69.00, 160.0);
                Item item6 = new Item("Cheddar Pringles", 80.00, 140.0);
                Item item7 = new Item("Bottled Water", 15.00, 0.0);
                Item item8 = new Item("Coffee", 100.00, 207);
                Item item10 = new Item("Dark Chocolate", 30.00, 100.0);
                Item item11 = new Item("White Chocolate", 30.00, 250.0);
                Item item12 = new Item("Vanilla Ice Cream", 50.00, 250.0);


                vendingMachineService.getInventory().addItem(item1, 10);
                vendingMachineService.getInventory().addItem(item2, 10);
                vendingMachineService.getInventory().addItem(item3, 10);
                vendingMachineService.getInventory().addItem(item4, 10);
                vendingMachineService.getInventory().addItem(item5, 10);
                vendingMachineService.getInventory().addItem(item6, 10);
                vendingMachineService.getInventory().addItem(item7, 10);
                vendingMachineService.getInventory().addItem(item8, 10);
                vendingMachineService.getInventory().addItem(item10, 10);
                vendingMachineService.getInventory().addItem(item11, 10);
                vendingMachineService.getInventory().addItem(item12, 10);

                JOptionPane.showMessageDialog(RegularVendingMachineView.this, "Success! ", "", JOptionPane.INFORMATION_MESSAGE);
                drawInventory();
            }
        });
    }

    protected void addProductButton(){
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddProductView(RegularVendingMachineView.this);

            }
        });
    }
    protected void addQuantityButton(){
        addQuantityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddQuantityView(RegularVendingMachineView.this);
            }
        });
    }
   protected void prepopulateCashRegisterButton(){
       prepopulateCashRegisterButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               int addition = 15;
               for (Denomination de : Denomination.values()){
                   vendingMachineService.getCashRegister().addCash(de, addition);
               }

               JOptionPane.showMessageDialog(RegularVendingMachineView.this, "Success! ", "", JOptionPane.INFORMATION_MESSAGE);
           }
       });
   }
   protected void addCashButton(){
       addCashButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               new AddCashView(vendingMachineService);
           }
       });
   }
   protected void collectAllMoneyButton(){
       collectAllMoneyButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               double totalMoney = vendingMachineService.getCashRegister().collectAllCash();
               JOptionPane.showMessageDialog(RegularVendingMachineView.this, "Total Money Collected: " + totalMoney, "", JOptionPane.INFORMATION_MESSAGE);

           }
       });
   }

    // TODO Remus 4.

   protected void printSummaryOfTransactions(){
       printSummaryOfTransactionsButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               new TransactionsSummaryView(RegularVendingMachineView.this);
           }
       });
   }
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
                   buyCustomCoffeeButton.setEnabled(false);


                   JOptionPane.showMessageDialog(RegularVendingMachineView.this, "Machine is now operational. ", "", JOptionPane.INFORMATION_MESSAGE);


               } catch (Exception d){
                   JOptionPane.showMessageDialog(RegularVendingMachineView.this, "Machine is not yet operational. ", "Error", JOptionPane.ERROR_MESSAGE);
               }


           }
       });
   }

   public void initTable(){
       Vector<String> headers = new Vector<>();
       headers.add("Name");
       headers.add("Price");
       headers.add("Calories");
       headers.add("Quantity");

       DefaultTableModel tableContents = new DefaultTableModel(headers, 0);
       this.inventoryTable.setModel(tableContents);

       this.inventoryScrollPane.setViewportView(this.inventoryTable);
   }

   public void drawInventory(){
       ArrayList<String> uniqueItemNames = this.vendingMachineService.getInventory().getUniqueItemNames();

       DefaultTableModel tableContents = (DefaultTableModel) this.inventoryTable.getModel();
       tableContents.setRowCount(0);

       for (String itemName : uniqueItemNames){
           double price = this.vendingMachineService.getInventory().getPrice(itemName);
           double calories = this.vendingMachineService.getInventory().getCalories(itemName);
           int quantity = this.vendingMachineService.getInventory().getQuantity(itemName);

           tableContents.addRow(new Object[] {
                   itemName, price, calories, quantity
           });
       }

       inventoryTable.repaint();
   }

}
