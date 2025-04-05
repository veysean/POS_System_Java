package model;

public class ProductCategory {
    private int categoryId;
    private String categoryName;
    
    public ProductCategory(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
    
    public int getCategoryId() { 
        return categoryId; 
    }
    public void setCategoryId(int categoryId) { 
        this.categoryId = categoryId; 
    }
    public String getCategoryName() { 
        return categoryName; 
    }
    public void setCategoryName(String categoryName) { 
        this.categoryName = categoryName; 
    }

    @Override
    public String toString() {
        return categoryName;
    }

}
