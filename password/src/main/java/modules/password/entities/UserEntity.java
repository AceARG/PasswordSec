package modules.password.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends IdentifierEntity{
    @Column
    private String email;
    @Column
    private String masterPassword;
    @Column
    private UsernameEntity username;
}
