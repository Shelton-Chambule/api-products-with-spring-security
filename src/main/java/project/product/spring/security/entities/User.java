package project.product.spring.security.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_user")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User  implements UserDetails {

    private Long id;
    private String login;
    private String password;
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.MANAGER){
            return List.of(new SimpleGrantedAuthority("ROLE_MANAGER"), new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        else if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));

    }

    @Override
    public String getUsername() {
        return login;
    }
}
