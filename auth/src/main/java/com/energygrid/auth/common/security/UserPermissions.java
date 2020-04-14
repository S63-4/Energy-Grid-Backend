package com.energygrid.auth.common.security;

public enum UserPermissions {
    // declare all the different permissions here.
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    UserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
