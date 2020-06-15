package com.energygrid.gateway.security.common.security;

public enum UserPermissions {
    // declare all the different permissions here.
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("Employee:write");


    private final String permission;

    UserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
