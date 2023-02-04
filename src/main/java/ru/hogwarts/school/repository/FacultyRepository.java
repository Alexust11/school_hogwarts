package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    Collection<Faculty> findByColorEquals(String color);


    Collection<Faculty> findByNameOrColorIgnoreCase(String name, String color);
    Faculty findFacultyByStudents(Student student);

    Object findAllByColor(String eq);

    Collection<Faculty> findByColor(String color);
}
