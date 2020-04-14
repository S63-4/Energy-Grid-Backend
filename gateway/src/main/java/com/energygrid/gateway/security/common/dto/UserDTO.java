package com.energygrid.gateway.security.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class UserDTO {
    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private String email;

    @JsonProperty
    private String phoneNumber;

    @JsonProperty
    private String mobileNumber;

    @JsonProperty
    private String zipCode;

    @JsonProperty
    private String houseNumber;

    @JsonProperty
    private String customerCode;

    @JsonProperty
    private Set<String> roles;

    @JsonProperty
    private Set<StatusDTO> status;


    public UserDTO(String firstName, String lastName, String email, String phoneNumber, String mobileNumber, String zipCode, String houseNumber, String customerCode, Set<String> roles, Set<StatusDTO> status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber= phoneNumber;
        this.mobileNumber = mobileNumber;
        this.zipCode = zipCode;
        this.houseNumber = houseNumber;
        this.customerCode = customerCode;
        this.roles = roles;
        this.status = status;
    }

    public UserDTO() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<StatusDTO> getStatus() {
        return status;
    }

    public void setStatus(Set<StatusDTO> status) {
        this.status = status;
    }
}
