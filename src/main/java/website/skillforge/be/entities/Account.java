package website.skillforge.be.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import website.skillforge.be.enums.Role;

import java.util.Collection;

@Entity
@Getter
@Setter
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true, nullable = false)
    String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    String fullName;
    @Column(unique = true, nullable = false)
    String email;
    String phone;
    @Enumerated(EnumType.STRING)
    Role role;
    String status;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
