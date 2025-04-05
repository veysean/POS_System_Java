package view;

import model.CashierDAO;
import model.Order;
import model.OrderItem;
import model.Product;

import javax.swing.*;
import java.util.List;

public class InvoicePage extends JFrame {
    private JTextPane invoicePane;
    private CashierDAO cashierDAO;

    public InvoicePage(Order order, CashierDAO cashierDAO) {
        this.cashierDAO = cashierDAO;
        setTitle("Invoice");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        invoicePane = new JTextPane();
        invoicePane.setContentType("text/html");
        invoicePane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(invoicePane);
        add(scrollPane);

        displayInvoice(order);
        this.setVisible(true);
    }

    private void displayInvoice(Order order) {
        StringBuilder invoiceText = new StringBuilder();
        invoiceText.append("<html><body>");
        invoiceText.append("<h1>Invoice</h1>");
        invoiceText.append("<p>Invoice ID: ").append(order.getInvoiceId()).append("</p>");
        invoiceText.append("<p>Order ID: ").append(order.getOrderId()).append("</p>");
        invoiceText.append("<h2>Items:</h2>");
        invoiceText.append("<table border='1' cellpadding='5' cellspacing='0'>");
        invoiceText.append(
                "<tr><th>Product ID</th><th>Product Name</th><th>Quantity</th><th>Unit Price</th><th>Total Price</th></tr>");

        List<OrderItem> items = order.getItems();
        for (OrderItem item : items) {
            Product product = cashierDAO.getProductById(item.getProductId());
            double unitPrice = product.getPrice();
            double totalPrice = unitPrice * item.getQuantity();
            invoiceText.append("<tr>")
                    .append("<td>").append(product.getProductId()).append("</td>")
                    .append("<td>").append(product.getName()).append("</td>")
                    .append("<td>").append(item.getQuantity()).append("</td>")
                    .append("<td>").append(unitPrice).append("</td>")
                    .append("<td>").append(totalPrice).append("</td>")
                    .append("</tr>");
        }

        invoiceText.append("</table>");
        invoiceText.append("<h2>Total Amount: $").append(order.getTotalAmount()).append("</h2>");
        invoiceText.append("</body></html>");
        invoicePane.setText(invoiceText.toString());
    }
}