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
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private Integer numberStudent = 0;
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

    public Collection<String> getAllStudentsStartingLetterA() {
        logger.info("Вызван метод выводы имен студентов в верхем регистре чьи имена начинаются на А");
        Collection<Student> students = this.getAllStudents();
        return students.stream()
                .map(e -> e.getName().toUpperCase(Locale.ROOT))
                .filter(e -> e.startsWith("A"))
                .sorted()
                .toList();
    }

    public Double getAverageAge() {
        logger.info("Вызван метод подсчета среднего возраста студентов");
        Collection<Student> students = this.getAllStudents();
        return students.stream()
                .mapToInt(e -> e.getAge())
                .average()
                .orElse(0);
    }



    public Collection<Student> getFiveLastStudents()
    {
        logger.info("Вызван метод вывода пяти последних студентов");
        return studentRepository.getFiveLastStudents();
    }
    public void printStudentsThreads() {
        logger.info("Вызван метод для печати нескольких студентов в разных потоках");
        List<Student> students = new ArrayList<>(this.getAllStudents());

        new Thread(() -> {
            printStudent(students, "Поток 1", 2);
            printStudent(students, "Поток 1", 3);
        }).start();

        new Thread(() -> {
            printStudent(students, "Поток 2", 4);
            printStudent(students, "Поток 2", 5);
        }).start();

        printStudent(students, "Поток 0", 0);
        printStudent(students, "Поток 0", 1);
    }

    private void printStudent(List<Student> students, String message, int number) {
        System.out.println(message + ": #" + number + ": " + students.get(number).getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printFewStudentsThreadsSync() {
        logger.info("Вызван метод для печати нескольких учащихся в разных синхронизированных потоках");
        List<Student> students = new ArrayList<>(this.getAllStudents());

        Thread thread1 = new Thread(() -> {
            printStudentSync(students, "Поток 1", 2);
            printStudentSync(students, "Поток 1", 3);
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            printStudentSync(students, "Поток 2", 4);
            printStudentSync(students, "Поток 2", 5);
        });
        thread2.start();

        printStudentSync(students, "Поток 0", 0);
        printStudentSync(students, "Поток 0", 1);
    }

    private void printStudentSync(List<Student> students, String message, int number) {
        while (numberStudent != number) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized (numberStudent) {
            System.out.println(message + ". Порядковый номер студента в таблице" + number + ": " + students.get(number).getName());
            numberStudent++;
        }
    }


}
