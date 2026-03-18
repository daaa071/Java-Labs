package model;

import enums.Position;

public class Staff {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Position position;

    public Staff(String firstName, String lastName, String phone,
                 String email, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public Position getPosition() { return position; }

    @Override
    public String toString() {
        return String.format("Staff{%s %s, position='%s'}",
                firstName, lastName, position.getTitle());
    }
}