package model;

// Позиція замовлення — не існує без Order (Композиція)
public class OrderItem {
    private Product product;
    private int quantity;
    private double priceAtOrder; // ціна на момент замовлення

    public OrderItem(Product product, int quantity, double priceAtOrder) {
        this.product = product;
        this.quantity = quantity;
        this.priceAtOrder = priceAtOrder;
    }

    public double getSubtotal() {
        return priceAtOrder * quantity;
    }

    // Getters
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getPriceAtOrder() { return priceAtOrder; }

    @Override
    public String toString() {
        return String.format("OrderItem{product='%s', qty=%d, price=%.2f, subtotal=%.2f}",
                product.getName(), quantity, priceAtOrder, getSubtotal());
    }
}