package com.example.todolist;

// File: Task.java

public class Task {
    private String name;
    private String description;

    // Constructor
    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

