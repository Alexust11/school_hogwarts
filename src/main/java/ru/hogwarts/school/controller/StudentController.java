package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("{count}")
    public ResponseEntity<Student> getFaculty(@PathVariable long count) {
        Student student = studentService.findStudent(count);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student creatStudent(@RequestBody Student student) {
        return studentService.creatStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{count}")
    public void deleteStudent(@PathVariable long count) {
        studentService.deleteStudent(count);

    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAllStudents());

    }

    @GetMapping("age")
    public ResponseEntity findStudents(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("ageBetween")
    public ResponseEntity<Collection<Student>> findInBetween(@RequestParam Integer min, @RequestParam Integer max) {
        Collection<Student> foundStudents = studentService.findStudentInBetween(min, max);
        return ResponseEntity.ok(foundStudents);
    }

}
