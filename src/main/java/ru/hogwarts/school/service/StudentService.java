package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository=facultyRepository;
    }


    public Student creatStudent(Student student) {
        logger.info("Вызван метод создания студента ");
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.info("Вызван метод поиска студента по id {}",id);
        return studentRepository.findById(id).orElseThrow(()->new EntityNotFoundMyException("Студента с таким id нет. Запрашиваемый id -"+id));
    }

    public Student editStudent(Student student) {
        logger.info("Вызван метод редактирования студента ");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.info("Вызван метод удаления студента {}",id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.info("Вызван метод вывода всех студентов ");
        return studentRepository.findAll();
    }

    public Collection<Student> findByAge(int age) {
        logger.info("Вызван метод поиска студента по возроасту = {}", age);
        return studentRepository.findStudentByAge(age);
    }

    public Collection<Student> findStudentInBetween(Integer min, Integer max) {
        logger.info("Вызван метод поиска студента по возроасту между min и max");
        return studentRepository.findStudentByAgeBetween(min, max);
    }
    public Faculty getFacultyByStudent(Long studentId) {
        logger.info ("Вызван метод поиска факультета по id студента");
        logger.debug("Поиск студента по id: {}", studentId);
        Student student = studentRepository.findStudentById(studentId);
        Faculty result = facultyRepository.findFacultyByStudents(student);
        logger.debug("{} факультет найден для студента с id: {}", result, studentId);
        return result;
    }
    public Integer getAllStudentsQuantity() {
        logger.info("Вызван метод вывода всех студентов");
        return this.studentRepository.getStudentsQuantity();
    }
    public Float getStudentsAgeAverage() {
        logger.info("Вызван метод вывода среднего возраста студентов");
        return this.studentRepository.getStudentsAgeAverage();
    }
    public Collection<Student> getFiveLastStudents()
    {
        logger.info("Вызван метод вывода пяти последних студентов");
        return studentRepository.getFiveLastStudents();
    }
}
