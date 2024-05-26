package models;

public class Participant {
    private final String name;
    private final String email;

    public Participant(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
