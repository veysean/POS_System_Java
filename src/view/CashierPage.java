package view;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Enumeration;
import model.OrderItem;

public class CashierPage extends JFrame {
    private JTable productTable;
    private JButton addButton;
    private JButton processOrderButton;
    private JButton backButton;
    private JTextField quantityField;
    private DefaultListModel<OrderItem> orderListModel;
    private JList<OrderItem> orderList;
    private JPanel panel;

    public CashierPage() {
        setTitle("Cashier Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        add(panel);
        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        productTable = new JTable();
        JScrollPane productScrollPane = new JScrollPane(productTable);
        productScrollPane.setBounds(10, 20, 760, 200);
        panel.add(productScrollPane);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(10, 230, 80, 25);
        panel.add(quantityLabel);

        quantityField = new JTextField(20);
        quantityField.setBounds(100, 230, 165, 25);
        panel.add(quantityField);

        addButton = new JButton("Add to Order");
        addButton.setBounds(10, 260, 120, 25);
        panel.add(addButton);

        orderListModel = new DefaultListModel<>();
        orderList = new JList<>(orderListModel);
        JScrollPane orderScrollPane = new JScrollPane(orderList);
        orderScrollPane.setBounds(10, 300, 760, 200);
        panel.add(orderScrollPane);

        processOrderButton = new JButton("Process Order");
        processOrderButton.setBounds(10, 510, 120, 25);
        panel.add(processOrderButton);

        backButton = new JButton("← Back");
        backButton.setBounds(140, 510, 120, 25);
        panel.add(backButton);
    }

    public int getSelectedProductId() {
        int row = productTable.getSelectedRow();
        if (row != -1) {
            return (int) productTable.getValueAt(row, 0);
        }
        return -1;
    }

    public int getQuantity() {
        return Integer.parseInt(quantityField.getText());
    }

    public void addAddToOrderListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addProcessOrderListener(ActionListener listener) {
        processOrderButton.addActionListener(listener);
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void setProductTableModel(TableModel model) {
        productTable.setModel(model);
    }

    public void addOrderItem(OrderItem item) {
        orderListModel.addElement(item);
    }

    public List<OrderItem> getOrderItems() {
        List<OrderItem> items = new ArrayList<>();
        Enumeration<OrderItem> enumeration = orderListModel.elements();
        while (enumeration.hasMoreElements()) {
            items.add(enumeration.nextElement());
        }
        return items;
    }

    public void clearOrder() {
        orderListModel.clear();
    }

    public JPanel getPanel() {
        return panel;
    }
}