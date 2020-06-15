package com.energygrid.gateway.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterDTO {

    @JsonProperty
    private String email;

    @JsonProperty
    private String password;

    @JsonProperty
    private String customerCode;

    public RegisterDTO(){}

    public RegisterDTO(String email, String password, String customerCode) {
        this.email = email;
        this.password = password;
        this.customerCode = customerCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
