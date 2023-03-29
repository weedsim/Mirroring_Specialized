package com.a306.fanftasy.domain.user.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean enabled;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails(String username, String password, boolean enabled, List<String> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {//계정 권한
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {//계정 만료여부 확인 True면 만료 X
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//계정 잠금여부 확인 True면 잠금X
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {//비밀번호 만료여부 리턴 True면 만료 X
        return true;
    }

    @Override
    public boolean isEnabled() { //계정 활성화 여부 True면 활성화
        return enabled;
    }
}