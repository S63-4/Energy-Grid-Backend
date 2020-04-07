package com.energygrid.common.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "user_employee")
public class Employee extends User {
    @Column
    private String EmployeeNumber;

    public Employee(String firstName, String lastName, String password_hash, String email, boolean isAccountNonExpired,
                    boolean isEnabled, boolean isAccountNonLocked, boolean isCredentialsNonExpired, Set<CustomGrantedAuthority> customGrantedAuthorities,
                    String employeeNumber) {
        super(firstName, lastName, password_hash, email, isAccountNonExpired, isEnabled, isAccountNonLocked, isCredentialsNonExpired, customGrantedAuthorities);
        this.EmployeeNumber = employeeNumber;
    }

    public Employee() {
    }

    public String getEmployeeNumber() {
        return EmployeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        EmployeeNumber = employeeNumber;
    }
}
