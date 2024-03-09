package az.ingress.paginationspecification.controller;

import az.ingress.paginationspecification.dto.SearchCriteria;
import az.ingress.paginationspecification.entity.Student;
import az.ingress.paginationspecification.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents(
            @RequestParam(value = "Name", required = false) String name,
            @RequestParam(value = "Surname", required = false) String surname
    ) {
        return ResponseEntity.ok(studentService.getAllStudents(name, surname));
    }

    @GetMapping("/like")
    public ResponseEntity<List<Student>> getStudentsLikeName(@RequestParam(value = "Name", required = false) String name) {
        return ResponseEntity.ok(studentService.getStudentsLikeName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudentsByAllFields(
            @RequestParam(value = "Name", required = false) String name,
            @RequestParam(value = "Surname", required = false) String surname,
            @RequestParam(value = "Age", required = false) Integer age,
            @RequestParam(value = "Gender", required = false) String gender
    ) {
        return ResponseEntity.ok(studentService.getAllStudentsByAllFields(name, surname, age, gender));
    }

    @GetMapping("/all1")
    public ResponseEntity<List<Student>> getAllStudents(Student student) {
        return ResponseEntity.ok(studentService.getAllStudents(student));
    }

    @GetMapping("/all2")
    public ResponseEntity<List<Student>> getAllStudents(@RequestBody List<SearchCriteria> searchCriteriaList) {
        return ResponseEntity.ok(studentService.getAllStudents(searchCriteriaList));
    }
}
