package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.Exception.EntityNotFoundMyException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;


    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {

        this.studentRepository = studentRepository;
        this.facultyRepository=facultyRepository;
    }


    public Student creatStudent(Student student) {

        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(()->new EntityNotFoundMyException("Студента с таким id нет. Запрашиваемый id -"+id));
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> findByAge(int age) {
        return studentRepository.findStudentByAge(age);
    }

    public Collection<Student> findStudentInBetween(Integer min, Integer max) {
        return studentRepository.findStudentByAgeBetween(min, max);
    }
    public Faculty getFacultyByStudent(Long studentId) {
        Student student = studentRepository.findStudentById(studentId);
        return facultyRepository.findFacultyByStudents(student);
    }
    public Integer getAllStudentsQuantity() {
        return this.studentRepository.getStudentsQuantity();
    }
    public Float getStudentsAgeAverage() {
        return this.studentRepository.getStudentsAgeAverage();
    }
    public Collection<Student> getFiveLastStudents() {
        return studentRepository.getFiveLastStudents();
    }
}
