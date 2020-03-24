package com.energygrid.common.models;


import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(name = "password_hash", nullable = false)
    private String hash;

    @Column(nullable = false, unique = true)
    private String email;

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

    @Column
    private  boolean isAccountNonExpired;
    @Column
    private  boolean isEnabled;
    @Column
    private  boolean isAccountNonLocked;
    @Column
    private  boolean isCredentialsNonExpired;

    public void setStatus(Set<Status> status) {
        this.status = status;
    }


    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Set<Status> status;

    public User(String firstName, String lastName, String password_hash, String email, String phoneNumber, String mobileNumber, String zipCode, String street, String city, String houseNumber, String customerCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hash = password_hash;
        this.email= email;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.zipCode = zipCode;
        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
        this.customerCode = customerCode;

    }

    public User(){
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }


    public String getPasswordHash() {
        return hash;
    }


    public String getEmail() {
        return email;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


    public void setPassword(String password_hash) {
        this.hash = password_hash;

    }

    public Set<Status> getStatus() {
        return status;
    }
}
