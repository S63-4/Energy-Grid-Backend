package com.energygrid.employee_service.common.security;


import com.energygrid.employee_service.common.models.CustomGrantedAuthority;
import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.Collectors;

import static com.energygrid.employee_service.common.security.UserPermissions.*;

public enum UserRole {
    // ADD all the user roles you want, and bind them to the permissions you defined.
    USER(Sets.newHashSet(USER_READ)),
    Employee(Sets.newHashSet(USER_READ, USER_WRITE)),
    Manager(Sets.newHashSet(USER_READ, USER_WRITE,EMPLOYEE_READ,EMPLOYEE_WRITE));

    private final Set<UserPermissions> permissions;

    UserRole(Set<UserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermissions> getPermissions() {
        return permissions;
    }

    public Set<CustomGrantedAuthority> getGrantedAuthorities(){
        Set<CustomGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new CustomGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new CustomGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
