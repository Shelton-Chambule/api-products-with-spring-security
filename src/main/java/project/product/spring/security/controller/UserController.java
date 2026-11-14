package project.product.spring.security.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import project.product.spring.security.dto.TokenResponse;
import project.product.spring.security.dto.user.UserRequest;
import project.product.spring.security.dto.user.UserResponse;
import project.product.spring.security.entity.User;
import project.product.spring.security.services.TokenService;
import project.product.spring.security.services.UserServices;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserServices userServices;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/createUser")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest){
            return  ResponseEntity.status(HttpStatus.CREATED).body(userServices.createUser( userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid UserRequest userRequest){

        var  username = new UsernamePasswordAuthenticationToken(userRequest.getLogin(), userRequest.getPassword());
        var authentication = authenticationManager.authenticate(username);

        var token = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new TokenResponse(token));
    }

    @GetMapping("/findAll")
    public ResponseEntity<Page<UserResponse>> getAllUser(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userServices.findAll(pageable));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userServices.update(userId, userRequest));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userServices.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public  ResponseEntity<UserResponse> getOneUser(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userServices.findById(userId));
    }

}
