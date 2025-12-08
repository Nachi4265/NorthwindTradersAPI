package com.pluralsight.NorthwindTradersAPI.Models;

public class Product {

    private int productID;
    private String productName;
    private int categoryID;
    private double unitPrice;

    public Product(int productID, String productName, int categoryID, double unitPrice) {
        this.productID = productID;
        this.productName = productName;
        this.categoryID = categoryID;
        this.unitPrice = unitPrice;
    }

    public Product() {
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", categoryID=" + categoryID +
                ", unitPrice=" + unitPrice +
                '}';
    }

}
