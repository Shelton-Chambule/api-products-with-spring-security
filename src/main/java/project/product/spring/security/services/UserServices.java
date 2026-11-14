package project.product.spring.security.services;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.product.spring.security.dto.user.UserRequest;
import project.product.spring.security.dto.user.UserResponse;
import project.product.spring.security.entity.User;
import project.product.spring.security.mapper.UserMapper;
import project.product.spring.security.repository.UserRepository;
import project.product.spring.security.exception.ResourceNotFound;
import project.product.spring.security.exception.LoginAlreadyExistsException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServices  implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow(() -> {
            return new ResourceNotFound(username);
        });
    }

    @Transactional
    public UserResponse createUser(UserRequest userRequestDTO){
        User user = mapper.toEntity(userRequestDTO);
        verifyLogin(user.getLogin());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        User saved = userRepository.save(user);
        return mapper.toResponse(saved);
    }

    public Page<UserResponse> findAll(Pageable pageable){
        Page<User> page = userRepository.findAll(pageable);
        return mapper.toListResponse(page);
    }

    @Transactional
    public UserResponse findById(Long id){

        if(!userRepository.existsById(id)) throw new ResourceNotFound(id);

        return mapper.toResponse(userRepository.findById(id));
    }

    public void deleteUser(Long id){
        userRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFound(id);
        });

        userRepository.deleteById(id);
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

    private String verifyLogin(String login){
        if(userRepository.existsByLogin(login)){
            throw new LoginAlreadyExistsException("Try other email, this email already exists");
        }
        return login;
    }
}
