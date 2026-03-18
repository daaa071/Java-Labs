import enums.*;
import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║     МАГАЗИН ЕЛЕКТРОНІКИ — ДЕМОНСТРАЦІЯ   ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        // ── 1. Створення клієнта ──────────────────────────────────────────
        Customer customer = new Customer(
                "Олена", "Коваленко", "Іванівна",
                "+380501234567", "o.kovalenko@email.com",
                LocalDate.of(1995, 3, 15), "Ж"
        );
        customer.setCumulativeDiscount(5.0);
        System.out.println("Клієнт: " + customer);

        // ── 2. INNER CLASS — ActivityTracker ──────────────────────────────
        // Inner class існує в контексті конкретного екземпляра Customer
        System.out.println("\n--- Inner class: ActivityTracker ---");
        Customer.ActivityTracker tracker = customer.createActivityTracker();
        tracker.recordLogin();
        tracker.recordLogin();
        tracker.recordLogin();
        tracker.recordLogin();
        tracker.recordLogin(); // 5-й вхід — статус підвищується до LOYAL
        tracker.printActivityLog();
        System.out.println("Статус після активності: " + customer.getStatus().getDisplayName());

        // ── 3. Товари ─────────────────────────────────────────────────────
        System.out.println("\n--- Товари ---");
        Product laptop = new Product(
                "Ноутбук Dell XPS 15", "DELL-XPS15-001",
                45000.00, "Ноутбуки", "Dell", 24
        );
        Product mouse = new Product(
                "Мишка Logitech MX Master 3", "LOG-MX3-002",
                2500.00, "Периферія", "Logitech", 12
        );
        System.out.println(laptop);
        System.out.println(mouse);

        // ── 4. STATIC NESTED CLASS — Order.Builder ────────────────────────
        // Builder є static nested — не потребує екземпляра Order для створення
        System.out.println("\n--- Static nested class: Order.Builder ---");
        Order order = new Order.Builder(customer)   // агрегація: customer живе окремо
                .discount(0.05)
                .tax(0.20)
                .paymentMethod(PaymentMethod.CARD)
                .comment("Термінове замовлення, прошу відправити сьогодні")
                .build();

        // КОМПОЗИЦІЯ — OrderItem'и створюються всередині Order і без нього не існують
        order.addItem(laptop, 1);
        order.addItem(mouse, 2);

        System.out.println(order);
        System.out.println("Позиції замовлення (Композиція):");
        order.getItems().forEach(item -> System.out.println("  " + item));

        // ── 5. Оновлення статусу замовлення ──────────────────────────────
        System.out.println("\n--- Обробка замовлення ---");
        order.setStatus(OrderStatus.PROCESSING);
        System.out.println("Статус: " + order.getStatus().getDisplayName());

        order.setStatus(OrderStatus.SHIPPED);
        order.setTrackingNumber("1234567890123456");
        order.setShippedAt(LocalDateTime.now());
        System.out.println("Статус: " + order.getStatus().getDisplayName()
                + " | ТТН: " + order.getTrackingNumber());

        order.setStatus(OrderStatus.COMPLETED);
        order.setReceivedAt(LocalDateTime.now());
        System.out.println("Статус: " + order.getStatus().getDisplayName());

        // ── 6. Персонал + АСОЦІАЦІЯ зі SupportTicket ─────────────────────
        System.out.println("\n--- Асоціація: Staff ↔ SupportTicket ---");
        Staff manager = new Staff(
                "Михайло", "Бондаренко",
                "+380671112233", "m.bondarenko@store.com",
                Position.MANAGER
        );
        System.out.println(manager);
        System.out.println("Посада: " + manager.getPosition().getTitle()
                + " | Обов'язки: " + manager.getPosition().getDuties());

        SupportTicket ticket = new SupportTicket(
                customer, TicketType.COMPLAINT,
                "Ноутбук має подряпину на кришці після доставки"
        );
        System.out.println("\nТікет до призначення: " + ticket);

        // АСОЦІАЦІЯ — manager призначається на ticket; обидва незалежні
        ticket.assignManager(manager);
        System.out.println("Тікет після призначення: " + ticket);

        ticket.resolve(4);
        System.out.println("Тікет вирішено. Оцінка: " + ticket.getRating() + "/5");

        // ── 7. LOCAL CLASS — форматування звіту ──────────────────────────
        System.out.println("\n--- Local class: ReportFormatter ---");
        printOrderReport(order);
    }

    // Метод із LOCAL CLASS всередині
    private static void printOrderReport(Order order) {

        // ============================================================
        // LOCAL CLASS (локальний клас всередині методу)
        // Видимий лише в межах методу printOrderReport
        // ============================================================
        class ReportFormatter {
            private final String separator = "─".repeat(48);

            public void printHeader(String title) {
                System.out.println(separator);
                System.out.println("  " + title);
                System.out.println(separator);
            }

            public void printRow(String label, String value) {
                System.out.printf("  %-22s: %s%n", label, value);
            }

            public void printItems(List<OrderItem> items) {
                System.out.println("  Позиції:");
                for (int i = 0; i < items.size(); i++) {
                    OrderItem item = items.get(i);
                    System.out.printf("    %d. %-28s x%d  = %.2f UAH%n",
                            i + 1,
                            item.getProduct().getName(),
                            item.getQuantity(),
                            item.getSubtotal());
                }
            }

            public void printTotal(double amount) {
                System.out.println(separator);
                System.out.printf("  %-22s: %.2f UAH%n", "РАЗОМ (з ПДВ 20%)", amount);
                System.out.println(separator);
            }
        }
        // ============================================================

        // Використання local class
        ReportFormatter fmt = new ReportFormatter();
        fmt.printHeader("КВИТАНЦІЯ ЗАМОВЛЕННЯ");
        fmt.printRow("Клієнт",
                order.getCustomer().getLastName() + " "
                        + order.getCustomer().getFirstName());
        fmt.printRow("Email", order.getCustomer().getEmail());
        fmt.printRow("Статус", order.getStatus().getDisplayName());
        fmt.printRow("Оплата", order.getPaymentMethod().getDisplayName());
        fmt.printRow("ТТН", order.getTrackingNumber() != null ? order.getTrackingNumber() : "—");
        fmt.printRow("Коментар", order.getComment());
        fmt.printItems(order.getItems());
        fmt.printTotal(order.getTotalAmount());
    }
}