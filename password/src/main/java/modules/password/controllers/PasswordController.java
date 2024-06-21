package modules.password.controllers;

import modules.password.models.PasswordModel;
import modules.password.services.PasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
public class PasswordController {
    private final PasswordService passwordService;

    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }
    @PostMapping("/create")
    public ResponseEntity<PasswordModel> createPassword(PasswordModel passwordModel) throws Exception {
        return ResponseEntity.ok(passwordService.createPassword(passwordModel));
    }
    @PutMapping("/update/{passwordId}")
    public ResponseEntity<PasswordModel> updatePassword(@PathVariable Long passwordId, @RequestBody PasswordModel passwordModel) throws Exception {
        return ResponseEntity.ok(passwordService.updatePassword(passwordId, passwordModel));
    }
    @DeleteMapping("/delete/{passwordId}")
    public ResponseEntity<Void> deletePassword(@PathVariable Long passwordId) {
        passwordService.deletePassword(passwordId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{passwordId}")
    public ResponseEntity<PasswordModel> getPasswordById(@PathVariable Long passwordId) throws Exception {
        return ResponseEntity.ok(passwordService.getPasswordById(passwordId));
    }
}
