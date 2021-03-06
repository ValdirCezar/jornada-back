package com.valdir.jornadaback.security;

import com.valdir.jornadaback.models.enumerations.ProfileEnum;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSS implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Getter
    private final Long id;
    @Getter
    private final String name;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserSS(Long id, String name, String email, String password, Set<ProfileEnum> profiles) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authorities = profiles.stream().map(x -> new SimpleGrantedAuthority(x.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}