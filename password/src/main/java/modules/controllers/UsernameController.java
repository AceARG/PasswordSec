package modules.controllers;

import modules.models.UsernameModel;
import modules.services.UsernameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/username")
public class UsernameController {
    private final UsernameService usernameService;

    public UsernameController(UsernameService usernameService) {
        this.usernameService = usernameService;
    }
    @PostMapping("/create")
    public ResponseEntity<UsernameModel> createUsername(UsernameModel username) throws Exception {
        return ResponseEntity.ok(usernameService.createUsername(username));
    }
    @GetMapping("/{usernameId}")
    public ResponseEntity<UsernameModel> getUsernameById(@PathVariable Long usernameId) throws Exception {
        return ResponseEntity.ok(usernameService.getUsernameById(usernameId));
    }
    @GetMapping("/all")
    public ResponseEntity<List<UsernameModel>> getAllUsername(){
        return ResponseEntity.ok(usernameService.getAllUsernames());
    }

}
