package az.ingress.paginationspecification.mapper;

import az.ingress.paginationspecification.dto.PageableCardResponse;
import az.ingress.paginationspecification.dto.PageableUserResponse;
import az.ingress.paginationspecification.entity.Card;
import az.ingress.paginationspecification.entity.User;
import org.springframework.data.domain.Page;

public class PageableMapper {
    public static PageableUserResponse mapToUserPageableResponse(Page<User> page) {
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

    public static PageableCardResponse mapToCardPageableResponse(Page<Card> page) {
        return PageableCardResponse
                .builder()
                .cardList(page.getContent())
                .pageSize(page.getSize())
                .pageNumber(page.getNumber())
                .hasNextPage(page.hasNext())
                .lastPageNumber(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }
}
