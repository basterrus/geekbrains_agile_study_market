package com.geekbrains.shop.entities;

public enum RoleEnum {
    ROLE_USER("ROLE_USER"),
    ROLE_MANAGER("ROLE_MANGER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String name;

    RoleEnum(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
}
