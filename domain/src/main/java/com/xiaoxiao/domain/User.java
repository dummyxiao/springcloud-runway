package com.xiaoxiao.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

/**
 * @author xiaobenneng@hotmail.com
 * @date 2020/3/14
 */
@Data
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    boolean isAccountNonExpired=true;

    boolean isAccountNonLocked=true;

    boolean isCredentialsNonExpired=true;

    boolean isEnabled=true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {

        isAccountNonExpired = accountNonExpired;

        if (accountNonExpired==null){
            isAccountNonExpired = true;
        }
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
        if (accountNonLocked==null){
            isAccountNonLocked = true;
        }
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
        if (credentialsNonExpired == null){
            isCredentialsNonExpired = true;
        }
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
        if (enabled==null){
            isEnabled = true;
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
