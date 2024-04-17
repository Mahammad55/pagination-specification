package az.ingress.paginationspecification.service;

import az.ingress.paginationspecification.dto.PageCriteria;
import az.ingress.paginationspecification.dto.PageableUser;
import az.ingress.paginationspecification.dto.PageableUserResponse;
import az.ingress.paginationspecification.dto.UserResponse;
import az.ingress.paginationspecification.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    Page<User> getAllUsers(Pageable pageable);

    Page<UserResponse> getAllUserResponse(Pageable pageable);

    Page<User> getAllUsers(int pageSize, int pageNumber);

    PageableUser getAllUsers(PageCriteria pageCriteria);

    PageableUserResponse getAllUsersResponses(PageCriteria pageCriteria);
}
