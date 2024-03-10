package az.ingress.paginationspecification.service.impl;

import az.ingress.paginationspecification.dto.PageCriteria;
import az.ingress.paginationspecification.dto.PageableUserResponse;
import az.ingress.paginationspecification.entity.User;
import az.ingress.paginationspecification.repository.UserRepository;
import az.ingress.paginationspecification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.paginationspecification.enums.CriteriaConstant.*;
import static az.ingress.paginationspecification.mapper.PageableMapper.mapToPageableResponse;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(Sort.by("name", "id").and(Sort.by("surname").descending().and(Sort.by("age"))));
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> getAllUsers(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(pageable);
    }

    @Override
    public PageableUserResponse getAllUsers(PageCriteria pageCriteria) {
        var pageNumber = pageCriteria.getPageNumber() == null ? PAGE_NUMBER_DEFAULT_VALUE : pageCriteria.getPageNumber();
        var pageSize = pageCriteria.getPageSize() == null ? PAGE_SIZE_DEFAULT_VALUE : pageCriteria.getPageSize();
        var sort = pageCriteria.getSort() == null ? SORT_DEFAULT_VALUE : pageCriteria.getSort();

        Pageable pageable;
        switch (sort[1]) {
            case "asc" -> pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sort[0]));
            case "desc" -> pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sort[0]).descending());
            default -> throw new RuntimeException("Please enter the valid sort direction like {asc,desc}");
        }

        Page<User> userPage = userRepository.findAll(pageable);
        return mapToPageableResponse(userPage);
    }
}
