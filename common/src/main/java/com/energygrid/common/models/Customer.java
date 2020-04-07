package com.energygrid.common.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "user_customer")
public class Customer extends User {
    @Column
    private String phoneNumber;
    @Column
    private String mobileNumber;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String houseNumber;

    @Column(nullable = false)
    private String customerCode;

    public Customer(String firstName, String lastName, String password_hash, String email, boolean isAccountNonExpired, boolean isEnabled,
                    boolean isAccountNonLocked, boolean isCredentialsNonExpired, Set<CustomGrantedAuthority> customGrantedAuthorities,
                    String phoneNumber, String mobileNumber, String zipCode, String street, String city, String houseNumber, String customerCode) {
        super(firstName, lastName, password_hash, email, isAccountNonExpired, isEnabled, isAccountNonLocked, isCredentialsNonExpired, customGrantedAuthorities);
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.zipCode = zipCode;
        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
        this.customerCode = customerCode;
    }

    public Customer() {

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
