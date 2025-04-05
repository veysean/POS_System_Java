package model;

public class Product {
    private int productId;
    private String productName;
    private double price;
    private String description;
    private ProductCategory category;

    public Product(int productId, String productName, double price, String description, ProductCategory category) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return productName;
    }

    public void setName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price
                + ", description=" + description + ", category=" + category + "]";
    }
}
