package javaswing;

import model.SpecialVendingMachineService;

import javax.swing.*;
import java.awt.event.*;

public class BuySpecialCoffeeView extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox beansbox;
    private JComboBox addon1;
    private JComboBox addon3;
    private JComboBox addon2;
    private JComboBox roastbox;
    private JComboBox sugarbox;




    private SpecialVendingMachineView v;
    private SpecialVendingMachineService vendingMachineService;

    public BuySpecialCoffeeView(SpecialVendingMachineView view) {
        this.v = view;
        this.vendingMachineService = view.getSpecialVendingMachineService();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(800,300);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        setVisible(true);
    }

    private void onOK() {

        String beans;
        String roast;
        String sugarLevel;
        String misc1;
        String misc2;
        String misc3;

        beans = (String) beansbox.getSelectedItem();
        roast = (String) roastbox.getSelectedItem();

        v.setRoast(roast);
        vendingMachineService.addSpecialItemToCart(beans, 1);
        if (!((String) sugarbox.getSelectedItem()).equals("0% Sugar")) {
            sugarLevel = (String) sugarbox.getSelectedItem();
            vendingMachineService.addSpecialItemToCart(sugarLevel, 1);
        }

        misc1 = (String) addon1.getSelectedItem();
        misc2 = (String) addon2.getSelectedItem();
        misc3 = (String) addon3.getSelectedItem();



        if (!misc1.isEmpty()){
            vendingMachineService.addSpecialItemToCart(misc1, 1);
        }
        if (!misc2.isEmpty()){
            vendingMachineService.addSpecialItemToCart(misc2, 1);
        }
        if (!misc3.isEmpty()){
            vendingMachineService.addSpecialItemToCart(misc3, 1);
        }

        dispose();

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
