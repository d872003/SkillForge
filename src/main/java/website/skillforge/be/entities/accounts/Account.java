package website.skillforge.be.entities.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import website.skillforge.be.entities.courses.Course;
import website.skillforge.be.entities.courses.CourseEnrollment;
import website.skillforge.be.entities.quizzes.QuizResult;
import website.skillforge.be.enums.status.AccountStatus;
import website.skillforge.be.enums.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private String avatar;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(columnDefinition = "nvarchar(255)")
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(columnDefinition = "nvarchar(255)")
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private Wallet wallet;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role.toString()));
        return authorities;
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

    @OneToMany(mappedBy = "createBy")
    @JsonIgnore
    private List<Course> course;

    @OneToMany(mappedBy = "doBy")
    @JsonIgnore
    private List<QuizResult> quizResult;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<CourseEnrollment> courseEnrollment;
}
