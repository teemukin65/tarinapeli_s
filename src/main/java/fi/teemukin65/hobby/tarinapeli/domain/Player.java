package fi.teemukin65.hobby.tarinapeli.domain;

import lombok.Data;

import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by teemu on 24.4.2017.
 */
@Data
@NoArgsConstructor
@Entity
public class Player implements UserDetails
{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true )
    private long id;

    @Email
    @Column( name = "USERNAME")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "ROLE")
    private PlayerRole role;





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<PlayerRole> roles = new ArrayList<>();
        roles.add(this.role);
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
