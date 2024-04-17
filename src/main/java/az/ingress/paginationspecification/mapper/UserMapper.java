package az.ingress.paginationspecification.mapper;

import az.ingress.paginationspecification.dto.UserResponse;
import az.ingress.paginationspecification.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse entityToResponse(User user);
}
