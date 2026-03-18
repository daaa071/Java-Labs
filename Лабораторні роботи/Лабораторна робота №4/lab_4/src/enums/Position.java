package enums;

public enum Position {
    MANAGER("Менеджер", "Обробка замовлень та підтримка клієнтів"),
    TECHNICIAN("Технік", "Гарантійне обслуговування"),
    WAREHOUSE("Комірник", "Управління складом");

    private final String title;
    private final String duties;

    Position(String title, String duties) {
        this.title = title;
        this.duties = duties;
    }

    public String getTitle() { return title; }
    public String getDuties() { return duties; }
}