package javaswing;

import model.Denomination;
import model.RegularVendingMachineService;

import javax.swing.*;
import java.awt.event.*;

public class AddCashView extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner quantity;
    private JComboBox denomination;
    private JLabel denominationLabel;
    private JLabel quantityLabel;

    private RegularVendingMachineService regularVendingMachineService;

    public AddCashView(RegularVendingMachineService regularVendingMachineService) {
        this.regularVendingMachineService = regularVendingMachineService;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(500, 150);

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
        try {
            Denomination billToBeInserted = null;
            double denominationValue = Double.parseDouble(denomination.getSelectedItem().toString());

            billToBeInserted = Denomination.of(denominationValue);
            int productQuantity = (int) quantity.getValue();
            regularVendingMachineService.getCashRegister().addCash(billToBeInserted, productQuantity);
            JOptionPane.showMessageDialog(this, "Success! ", "", JOptionPane.INFORMATION_MESSAGE);

            dispose();

        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Please provide valid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
