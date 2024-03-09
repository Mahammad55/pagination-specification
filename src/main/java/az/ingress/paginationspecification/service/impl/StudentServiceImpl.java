package az.ingress.paginationspecification.service.impl;

import az.ingress.paginationspecification.entity.Student;
import az.ingress.paginationspecification.repository.StudentRepository;
import az.ingress.paginationspecification.service.StudentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.collection.internal.StandardIdentifierBagSemantics;
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

    @Override
    public List<Student> getAllStudentsByAllFields(String name, String surname, Integer age, String gender) {
        return studentRepository.findAllStudents(name, surname, age, gender);
    }

    @Override
    public List<Student> getAllStudents(Student student) {
        Specification<Student> studentSpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (student.getId() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("id"), student.getId()));
            }

            if (student.getName() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("name"), student.getName()));
            }

            if (student.getSurname() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("surname"), student.getSurname()));
            }

            if (student.getAge() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("age"), student.getAge()));
            }

            if (student.getGender() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("gender"), student.getGender()));
            }

            query.where(criteriaBuilder.and(predicateList.toArray(new Predicate[0])));
            return query.getRestriction();
        };

        return studentRepository.findAll(studentSpecification);
    }
}