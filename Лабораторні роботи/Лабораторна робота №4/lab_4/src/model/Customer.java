package model;

import enums.CustomerStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private String gender;
    private LocalDate registrationDate;
    private boolean active;
    private double cumulativeDiscount; // %
    private CustomerStatus status;

    // Зберігаємо логи активності — доступні inner class
    private final List<String> activityLog = new ArrayList<>();

    public Customer(String firstName, String lastName, String middleName,
                    String phone, String email, LocalDate birthDate, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.registrationDate = LocalDate.now();
        this.active = true;
        this.cumulativeDiscount = 0.0;
        this.status = CustomerStatus.REGULAR;
    }

    // ============================================================
    // INNER CLASS (нестатичний внутрішній клас)
    // Має прямий доступ до полів зовнішнього класу Customer
    // ============================================================
    public class ActivityTracker {
        private int loginCount = 0;
        private LocalDateTime lastActivity;

        public void recordLogin() {
            loginCount++;
            lastActivity = LocalDateTime.now();
            // Прямий доступ до поля зовнішнього класу:
            activityLog.add("[" + lastActivity + "] Логін #" + loginCount
                    + " клієнта: " + firstName + " " + lastName);
            updateStatusByActivity();
        }

        private void updateStatusByActivity() {
            // Доступ до поля зовнішнього класу Customer.this.status
            if (loginCount >= 5 && Customer.this.status == CustomerStatus.REGULAR) {
                Customer.this.status = CustomerStatus.LOYAL;
                activityLog.add("Статус підвищено до LOYAL після " + loginCount + " входів.");
            }
        }

        public void printActivityLog() {
            System.out.println("=== Журнал активності: " + firstName + " " + lastName + " ===");
            activityLog.forEach(System.out::println);
            System.out.println("Всього входів: " + loginCount);
        }

        public int getLoginCount() { return loginCount; }
        public LocalDateTime getLastActivity() { return lastActivity; }
    }
    // ============================================================

    // Фабричний метод для отримання tracker — зовнішній код створює через Customer
    public ActivityTracker createActivityTracker() {
        return new ActivityTracker();
    }

    // Getters / Setters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getMiddleName() { return middleName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getGender() { return gender; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public boolean isActive() { return active; }
    public double getCumulativeDiscount() { return cumulativeDiscount; }
    public CustomerStatus getStatus() { return status; }

    public void setCumulativeDiscount(double cumulativeDiscount) {
        this.cumulativeDiscount = cumulativeDiscount;
    }
    public void setStatus(CustomerStatus status) { this.status = status; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return String.format("Customer{%s %s, email='%s', status=%s, discount=%.1f%%}",
                firstName, lastName, email, status.getDisplayName(), cumulativeDiscount);
    }
}