package com.company.team.enums;

public enum RoleEnum {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String name;

    private RoleEnum(String name) {
        this.name = name;
    }

    public String getRole() {
        return name;
    }

    public static void main(String[] args) {
        System.out.println(RoleEnum.ADMIN);
    }
}
