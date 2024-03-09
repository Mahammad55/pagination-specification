package az.ingress.paginationspecification.controller;

import az.ingress.paginationspecification.entity.Student;
import az.ingress.paginationspecification.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(
            @RequestParam(value = "Name", required = false) String name,
            @RequestParam(value = "Surname", required = false) String surname
    ) {
        return ResponseEntity.ok(studentService.getAllStudents(name, surname));
    }
}
