package project.product.spring.security.controller;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.product.spring.security.dto.user.UserRequest;
import project.product.spring.security.dto.user.UserResponse;
import project.product.spring.security.services.UserServices;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userServices.findAll());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable Long userId, @RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(userServices.update(userId, userRequest));
    }
}
