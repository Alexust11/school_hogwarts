package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Collection<Student> findStudentByAge(int age);

    Collection<Student> findStudentByAgeBetween(int min, int max);
    Collection<Student> findStudentByFacultyId(long id);

    Student findStudentById(Long id);

    Object findAllByAge(int eq);
    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Integer getStudentsQuantity();
    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    Float getStudentsAgeAverage();
    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5;", nativeQuery = true)
    Collection<Student> getFiveLastStudents();
}
