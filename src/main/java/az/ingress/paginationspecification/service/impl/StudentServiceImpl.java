package az.ingress.paginationspecification.service.impl;

import az.ingress.paginationspecification.entity.Student;
import az.ingress.paginationspecification.repository.StudentRepository;
import az.ingress.paginationspecification.service.StudentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents(String name, String surname) {
        Specification<Student> studentSpecification;

        studentSpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (name != null) {
                predicateList.add(criteriaBuilder.equal(root.get("name"), name));
            }

            if (surname != null) {
                predicateList.add(criteriaBuilder.equal(root.get("surname"), surname));
            }

            query.where(criteriaBuilder.and(predicateList.toArray(new Predicate[0])));
            return query.getRestriction();
        };

        return studentRepository.findAll(studentSpecification);
    }

    @Override
    public List<Student> getStudentsLikeName(String name) {
        Specification<Student> studentSpecification = new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("name"), "%" + name + "%");
            }
        };
        return studentRepository.findAll(studentSpecification);
    }
}