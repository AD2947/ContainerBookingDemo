package com.example.demo.model;

public enum ContainerType {
    DRY("DRY"),
    REEFER("REEFER");

    private final String input;
    private ContainerType(String input) {
        this.input = input;
    }
}
