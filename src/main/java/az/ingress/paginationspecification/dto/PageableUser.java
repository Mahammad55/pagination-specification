package az.ingress.paginationspecification.dto;

import az.ingress.paginationspecification.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageableUser {
    private List<User> userList;

    private Integer pageNumber;

    private Integer pageSize;

    private Integer lastPageNumber;

    private Long totalElements;

    private boolean hasNextPage;
}
