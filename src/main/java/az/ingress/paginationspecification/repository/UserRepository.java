package az.ingress.paginationspecification.repository;

import az.ingress.paginationspecification.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
