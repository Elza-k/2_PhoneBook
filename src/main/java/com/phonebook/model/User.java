package com.phonebook.model;

public class User {
    private String email;
    private String passwort;

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPasswort(String passwort) {
        this.passwort = passwort;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswort() {
        return passwort;
    }
}
