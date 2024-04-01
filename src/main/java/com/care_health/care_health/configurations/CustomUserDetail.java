package com.care_health.care_health.configurations;

import com.care_health.care_health.dtos.UserDTO;
import com.care_health.care_health.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetail implements UserDetails {

    /* info user from database */
    private String userName;

    @JsonIgnore
    private String password;

    private String email;

    private boolean isUserStatus;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities; // get role of this user
    }


    // from info user transfer info custom user detail of security
    public static CustomUserDetail mapUserToDetail(User user) {

        System.out.println("CustomUserDetail");

        // get all role of user
        List<GrantedAuthority> listAuthoritys = user.getListRoles().stream()
                .map(roleItem -> new SimpleGrantedAuthority(roleItem.getRoleName().name()))
                .collect(Collectors.toList());
        System.out.println("listAuthoritys" + listAuthoritys);
        // return a customer user detail
        return new CustomUserDetail(
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.isUserStatus(),
                listAuthoritys
        );
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
