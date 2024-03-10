package az.ingress.paginationspecification.mapper;

import az.ingress.paginationspecification.dto.PageableUserResponse;
import az.ingress.paginationspecification.entity.User;
import org.springframework.data.domain.Page;

public class PageableMapper {
    public static PageableUserResponse mapToPageableResponse(Page<User> page) {
        return PageableUserResponse
                .builder()
                .userList(page.getContent())
                .pageSize(page.getSize())
                .pageNumber(page.getNumber())
                .hasNextPage(page.hasNext())
                .lastPageNumber(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }
}
