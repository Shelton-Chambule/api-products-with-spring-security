package project.product.spring.security.controller;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.product.spring.security.dto.TokenResponse;
import project.product.spring.security.dto.user.UserRequest;
import project.product.spring.security.dto.user.UserResponse;
import project.product.spring.security.entities.User;
import project.product.spring.security.repository.UserRepository;
import project.product.spring.security.services.TokenService;
import project.product.spring.security.services.UserServices;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
   private final UserServices userServices;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository, UserServices userServices) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userServices = userServices;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid UserRequest userRequest){

        var  username = new UsernamePasswordAuthenticationToken(userRequest.getLogin(), userRequest.getPassword());
        var authentication = authenticationManager.authenticate(username);

        var token = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/create/user")
    public ResponseEntity<UserResponse> createUser( @Valid @RequestBody UserRequest userRequest){
        UserResponse user = userServices.saveUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
