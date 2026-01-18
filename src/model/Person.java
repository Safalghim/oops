package model;

public class Person {

    private int id;
    private String name;
    private String email;

    // ✅ REQUIRED constructor for DAO
    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Optional (but recommended)
    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
}
