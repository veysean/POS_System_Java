package controller;

import model.CashierDAO;
import model.Order;
import model.OrderItem;
import model.Product;
import view.CashierPage;
import view.InvoicePage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CashierController {
    private CashierDAO cashier;
    private CashierPage cashierPage;

    public CashierController(CashierDAO cashier, CashierPage cashierPage) {
        this.cashier = cashier;
        this.cashierPage = cashierPage;

        this.cashierPage.addAddToOrderListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int productId = cashierPage.getSelectedProductId();
                    int quantity = cashierPage.getQuantity();
                    double unitPrice = cashier.getProductById(productId).getPrice();
                    if (productId != -1 && quantity > 0) {
                        OrderItem item = new OrderItem(productId, quantity, unitPrice);
                        cashierPage.addOrderItem(item);
                    } else {
                        JOptionPane.showMessageDialog(cashierPage, "Invalid product or quantity.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(cashierPage, "Please enter a valid quantity.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.cashierPage.addProcessOrderListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<OrderItem> items = cashierPage.getOrderItems();
                double totalAmount = calculateTotalAmount(items);
                Order order = new Order(0, items, totalAmount);
                if (cashier.processOrder(order)) {
                    JOptionPane.showMessageDialog(cashierPage, "Order processed successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    cashierPage.clearOrder();
                    cashier.saveInvoice(order); // Save the invoice to the database
                    new InvoicePage(order, cashier); // Display the invoice
                } else {
                    JOptionPane.showMessageDialog(cashierPage, "Failed to process order.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.cashierPage.addBackButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cashierPage.dispose(); // Close the admin page
            }
        });

        refreshProductTable();

    }

    private void refreshProductTable() {
        List<Product> products = cashier.getAllProducts();
        DefaultTableModel model = new DefaultTableModel(
                new String[] { "Product ID", "Product Name", "Price", "Description", "Category" }, 0);
        for (Product product : products) {
            model.addRow(new Object[] { product.getProductId(), product.getName(), product.getPrice(),
                    product.getDescription(), product.getCategory().getCategoryName() });
        }
        cashierPage.setProductTableModel(model);
    }

    private double calculateTotalAmount(List<OrderItem> items) {
        double totalAmount = 0;
        for (OrderItem item : items) {
            Product product = cashier.getProductById(item.getProductId());
            totalAmount += product.getPrice() * item.getQuantity();
        }
        return totalAmount;
    }
}
