package modules.password.controllers;

import modules.password.models.UserModel;
import modules.password.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user){
        return ResponseEntity.ok(userService.createUser(user));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }
}
