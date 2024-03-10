package az.ingress.paginationspecification.repository;

import az.ingress.paginationspecification.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    @Query(value = """
            select s from Student s
            where (:student_name is null or s.name=:student_name)
            and (:student_surname is null or s.surname=:student_surname)
            and (:student_age is null or s.age=:student_age)
            and (:student_gender is null or s.gender=:student_gender)
            """)
    List<Student> findAllStudents(
            @Param("student_name") String name,
            @Param("student_surname") String surname,
            @Param("student_age") Integer age,
            @Param("student_gender") String gender
    );
}
