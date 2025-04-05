package model;

public class OrderItem {
    private int productId;
    private int quantity;
    private double unitPrice;

    public OrderItem(int productId, int quantity, double unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Quantity: " + quantity + ", Unit Price: " + unitPrice;
    }
}

