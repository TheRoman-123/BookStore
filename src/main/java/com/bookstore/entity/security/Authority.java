package com.bookstore.entity.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Authority implements GrantedAuthority, Serializable {
    @Serial
    private static final long serialVersionUID = 123123L;

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
