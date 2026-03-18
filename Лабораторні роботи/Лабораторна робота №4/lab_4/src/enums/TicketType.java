package enums;

public enum TicketType {
    COMPLAINT("Скарга"),
    REQUEST("Запит"),
    SUGGESTION("Пропозиція");

    private final String displayName;

    TicketType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() { return displayName; }
}