package ru.homework.security.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.homework.models.Reader;
import ru.homework.models.State;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private Reader reader;

    public UserDetailsImpl(Reader reader) {
        this.reader = reader;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole = reader.getRole().name();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole);
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return reader.getHashPassword();
    }

    @Override
    public String getUsername() {
        return reader.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !reader.getState().equals(State.BANNED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return reader.getState().equals(State.ACTIVE);
    }

    public Reader getReader() {
        return reader;
    }
}
