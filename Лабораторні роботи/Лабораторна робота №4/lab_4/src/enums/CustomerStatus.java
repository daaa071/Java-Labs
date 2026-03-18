package enums;

public enum CustomerStatus {
    REGULAR("Звичайний"),
    LOYAL("Постійний"),
    BLOCKED("Заблокований");

    private final String displayName;

    CustomerStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() { return displayName; }
}