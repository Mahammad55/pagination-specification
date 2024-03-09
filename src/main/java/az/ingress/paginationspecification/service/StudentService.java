package az.ingress.paginationspecification.service;

import az.ingress.paginationspecification.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents(String name, String surname);
}
