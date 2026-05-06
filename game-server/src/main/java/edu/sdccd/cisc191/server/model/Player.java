package edu.sdccd.cisc191.server.model;

public class Player {
    private final String name;

    public Player(String name) {
        this.name = (name == null || name.isBlank()) ? "Player" : name.trim();
    }

    public String getName() {
        return name;
    }
}
