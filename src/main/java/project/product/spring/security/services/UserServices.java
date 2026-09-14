package project.product.spring.security.services;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.product.spring.security.dto.user.UserRequest;
import project.product.spring.security.dto.user.UserResponse;
import project.product.spring.security.entities.User;
import project.product.spring.security.repository.UserRepository;
import project.product.spring.security.services.exeption.ResourceNotFound;
import project.product.spring.security.services.exeption.VerifyLoginException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServices  implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServices(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    private String verifyLogin(String login){
        if(userRepository.existsByLogin(login)){
            throw new VerifyLoginException("Try other email, this email already exists");
        }
        return login;
    }

    public UserResponse saveUser(UserRequest userRequestDTO){
        User user =  new User();

        user.setLogin(verifyLogin(userRequestDTO.getLogin()));
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setRole(userRequestDTO.getRole());
        userRepository.save(user);
        return new UserResponse(user);
    }

    public List<UserResponse> findAll(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponse::new).collect(Collectors.toList());
    }

    public UserResponse update(Long id, UserRequest userRequestDTO){
        try{
            User user = userRepository.getReferenceById(id);
            updateUser(user,userRequestDTO);
            userRepository.save(user);
            return new UserResponse(user);
        }catch (EntityNotFoundException exception){
            throw new ResourceNotFound(id);
        }
    }

    private void updateUser(User user, UserRequest userRequestDTO) {
        user.setLogin(userRequestDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
    }

}
