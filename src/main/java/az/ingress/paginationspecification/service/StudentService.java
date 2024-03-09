package az.ingress.paginationspecification.service;

import az.ingress.paginationspecification.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents(String name, String surname);

    List<Student> getStudentsLikeName(String name);

    List<Student> getAllStudentsByAllFields(String name, String surname, Integer age, String gender);
}
