package enums;

public enum PaymentMethod {
    CASH("Готівка"),
    CARD("Картка"),
    ONLINE("Онлайн");

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() { return displayName; }
}