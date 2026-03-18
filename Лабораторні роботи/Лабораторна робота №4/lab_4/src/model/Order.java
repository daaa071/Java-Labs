package model;

import enums.OrderStatus;
import enums.PaymentMethod;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Customer customer;           // АГРЕГАЦІЯ — Customer існує незалежно
    private LocalDateTime orderDate;
    private OrderStatus status;
    private double discount;
    private double tax;
    private PaymentMethod paymentMethod;
    private String trackingNumber;
    private String comment;
    private String cancellationReason;
    private LocalDateTime shippedAt;
    private LocalDateTime receivedAt;

    // КОМПОЗИЦІЯ — OrderItem не існує без Order
    private final List<OrderItem> items = new ArrayList<>();

    // Приватний конструктор — об'єкт створюється лише через Builder
    private Order() {}

    // ============================================================
    // STATIC NESTED CLASS — Builder патерн
    // Не має доступу до non-static полів Order через this,
    // але будує екземпляр Order поетапно
    // ============================================================
    public static class Builder {
        private Customer customer;
        private double discount = 0.0;
        private double tax = 0.20;
        private PaymentMethod paymentMethod = PaymentMethod.CARD;
        private String comment = "";

        public Builder(Customer customer) {
            this.customer = customer;
        }

        public Builder discount(double discount) {
            this.discount = discount;
            return this;
        }

        public Builder tax(double tax) {
            this.tax = tax;
            return this;
        }

        public Builder paymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.customer = this.customer;
            order.discount = this.discount;
            order.tax = this.tax;
            order.paymentMethod = this.paymentMethod;
            order.comment = this.comment;
            order.orderDate = LocalDateTime.now();
            order.status = OrderStatus.NEW;
            return order;
        }
    }
    // ============================================================

    public void addItem(Product product, int quantity) {
        double price = product.getBasePrice() * (1 - discount);
        items.add(new OrderItem(product, quantity, price));
    }

    public double getTotalAmount() {
        return items.stream()
                .mapToDouble(OrderItem::getSubtotal)
                .sum() * (1 + tax);
    }

    public void setStatus(OrderStatus status) { this.status = status; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    public void setShippedAt(LocalDateTime shippedAt) { this.shippedAt = shippedAt; }
    public void setReceivedAt(LocalDateTime receivedAt) { this.receivedAt = receivedAt; }
    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
        this.status = OrderStatus.CANCELLED;
    }

    // Getters
    public Customer getCustomer() { return customer; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public OrderStatus getStatus() { return status; }
    public double getDiscount() { return discount; }
    public double getTax() { return tax; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public String getTrackingNumber() { return trackingNumber; }
    public String getComment() { return comment; }
    public String getCancellationReason() { return cancellationReason; }
    public List<OrderItem> getItems() { return items; }

    @Override
    public String toString() {
        return String.format("Order{customer='%s %s', status=%s, total=%.2f UAH, items=%d}",
                customer.getFirstName(), customer.getLastName(),
                status.getDisplayName(), getTotalAmount(), items.size());
    }
}