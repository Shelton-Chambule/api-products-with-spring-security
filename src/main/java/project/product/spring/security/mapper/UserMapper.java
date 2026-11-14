package project.product.spring.security.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import project.product.spring.security.dto.user.UserRequest;
import project.product.spring.security.dto.user.UserResponse;
import project.product.spring.security.entity.User;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntity(UserRequest userRequest);

    UserResponse toResponse(User user);

    UserResponse toResponse(Optional<User> user);

    Page<UserResponse> toListResponse(Page<User> list);

}
