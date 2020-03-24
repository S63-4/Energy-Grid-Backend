package com.energygrid.common.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "auth")
public class CustomGrantedAuthority implements GrantedAuthority,Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="auth_name")
    private String authority;

    public CustomGrantedAuthority() { }

    public CustomGrantedAuthority(String name) {
        this.authority = name;
    }

    public String getName() {
        return authority;
    }

    public void setName(String name) {
        this.authority = name;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
