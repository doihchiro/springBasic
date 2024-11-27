package com.oasis.acquiesce.domain;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
//@Getter
public class Member implements UserDetails {

    private String uid;
    private String pwd;
    private String uname;
    private String email;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    private List<MemberAuth> authList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authList == null || authList.isEmpty()) {
            return null;
        }

        //List<GrantedAuthority> 객체를 반환
        return authList.stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.pwd;
    }

    @Override
    public String getUsername() {
        return this.uid;
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
