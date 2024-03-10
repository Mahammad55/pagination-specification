package az.ingress.paginationspecification.controller;

import az.ingress.paginationspecification.dto.PageCriteria;
import az.ingress.paginationspecification.dto.PageableUserResponse;
import az.ingress.paginationspecification.entity.User;
import az.ingress.paginationspecification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/all1")
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @GetMapping("/all2")
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam int pageSize, @RequestParam int pageNumber) {
        return ResponseEntity.ok(userService.getAllUsers(pageSize, pageNumber));
    }

    @GetMapping("/all3")
    public ResponseEntity<PageableUserResponse> getAllUsers(PageCriteria pageCriteria) {
        return ResponseEntity.ok(userService.getAllUsers(pageCriteria));
    }
}
