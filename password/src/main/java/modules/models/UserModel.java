package modules.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel  {
    private String email;
    private String masterPassword;
    private List<UsernameModel> usernameModel;
}
