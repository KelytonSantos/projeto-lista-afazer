package com.list.to_do.entities.ENUM;

public enum AccessLevel {
    VIEW("VIEW"),
    EDIT("EDIT"), // set just in share case
    ADMIN("ADMIN");

    private String userRole;

    AccessLevel(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }
}
