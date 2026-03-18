package model;

import enums.TicketType;
import java.time.LocalDateTime;

// АСОЦІАЦІЯ: Staff обробляє SupportTicket (слабкий зв'язок — обидва існують незалежно)
public class SupportTicket {
    private Customer customer;       // Хто відкрив тікет
    private Staff assignedManager;   // АСОЦІАЦІЯ — менеджер може бути переназначений
    private TicketType type;
    private String description;
    private String status;
    private int rating;              // Оцінка клієнта після вирішення (1–5)
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;

    public SupportTicket(Customer customer, TicketType type, String description) {
        this.customer = customer;
        this.type = type;
        this.description = description;
        this.status = "OPEN";
        this.createdAt = LocalDateTime.now();
    }

    // АСОЦІАЦІЯ — Staff може бути призначений/змінений
    public void assignManager(Staff manager) {
        this.assignedManager = manager;
        this.status = "IN_PROGRESS";
    }

    public void resolve(int rating) {
        this.rating = rating;
        this.status = "RESOLVED";
        this.resolvedAt = LocalDateTime.now();
    }

    // Getters
    public Customer getCustomer() { return customer; }
    public Staff getAssignedManager() { return assignedManager; }
    public TicketType getType() { return type; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public int getRating() { return rating; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getResolvedAt() { return resolvedAt; }

    @Override
    public String toString() {
        String manager = assignedManager != null
                ? assignedManager.getFirstName() + " " + assignedManager.getLastName()
                : "не призначено";
        return String.format("SupportTicket{type=%s, status='%s', customer='%s %s', manager='%s'}",
                type.getDisplayName(), status,
                customer.getFirstName(), customer.getLastName(), manager);
    }
}