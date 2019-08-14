package net.snoopgame.hr.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {

    private final Long id;
    private final String userName;
    private final String lastName;
    private final String middleName;
    private final String email;
    private final String password;
    private final boolean enabled;
    private final Date lastPasswordResetDate;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Long id, String userName, String lastName, String middleName, String email, String password, boolean enabled,
                   Date resetDate, Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.userName = userName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.lastPasswordResetDate = resetDate;
        this.authorities = authorities;
    }

    @JsonIgnore
    public Long getId(){ return id; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    @JsonIgnore
    @Override
    public String getPassword() { return password; }

    public String getEmail() { return email; }

    @Override
    public String getUsername() { return userName; }


    public String getLastName() { return  lastName; }


    public String getMiddleName() { return  middleName; }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() { return true;}

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() { return true;}

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() { return true;}

    @Override
    public boolean isEnabled() { return enabled; }

    @JsonIgnore
    public Date getLastPasswordResetDate() { return lastPasswordResetDate; }
}
