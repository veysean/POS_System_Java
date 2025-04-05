package model;

import java.util.List;

public class Order {
    private int orderId;
    private int invoiceId;
    private List<OrderItem> items;
    private double totalAmount;

    public Order(int orderId, List<OrderItem> items, double totalAmount) {
        this.orderId = orderId;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}