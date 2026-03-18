package enums;

public enum OrderStatus {
    NEW("Нове"),
    PROCESSING("В обробці"),
    SHIPPED("Відправлено"),
    COMPLETED("Завершено"),
    CANCELLED("Скасовано");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() { return displayName; }
}