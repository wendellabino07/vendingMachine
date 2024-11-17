package javaswing;

import model.Item;
import model.RegularVendingMachineService;

import javax.swing.*;
import java.awt.event.*;

public class AddToCartView extends JDialog {
    private JPanel addToCartPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JSpinner spinner1;

    private RegularVendingMachineService vendingMachineService;


    public AddToCartView(RegularVendingMachineView view) {
        this.vendingMachineService = view.getVendingMachineService();

        setContentPane(addToCartPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500,150);


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
        addToCartPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setVisible(true);
    }

    private void onOK() {
        try {
            String query = textField1.getText();
            int quantity = (int) spinner1.getValue();
            this.vendingMachineService.addToCart(query, quantity);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please provide valid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
