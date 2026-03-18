package model;

import java.time.LocalDate;

public class Product {
    private String name;
    private String sku;
    private double basePrice;
    private String category;
    private String manufacturer;
    private int warrantyMonths;
    private boolean active;
    private LocalDate addedDate;

    public Product(String name, String sku, double basePrice,
                   String category, String manufacturer, int warrantyMonths) {
        this.name = name;
        this.sku = sku;
        this.basePrice = basePrice;
        this.category = category;
        this.manufacturer = manufacturer;
        this.warrantyMonths = warrantyMonths;
        this.active = true;
        this.addedDate = LocalDate.now();
    }

    // Getters
    public String getName() { return name; }
    public String getSku() { return sku; }
    public double getBasePrice() { return basePrice; }
    public String getCategory() { return category; }
    public String getManufacturer() { return manufacturer; }
    public int getWarrantyMonths() { return warrantyMonths; }
    public boolean isActive() { return active; }
    public LocalDate getAddedDate() { return addedDate; }

    @Override
    public String toString() {
        return String.format("Product{name='%s', sku='%s', price=%.2f, manufacturer='%s'}",
                name, sku, basePrice, manufacturer);
    }
}