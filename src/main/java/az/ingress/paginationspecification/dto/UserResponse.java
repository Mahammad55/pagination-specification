package az.ingress.paginationspecification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String name;

    private String surname;

    private Integer age;

    private String birthPlace;
}
