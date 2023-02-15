package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.Exception.EntityNotFoundMyException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Stream;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final StudentRepository studentRepository;
       public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository=studentRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Вызван метод создания факультета");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Вызван метод поиска факультета");
        logger.debug("Произвед поиск студента по id :{}", id);
        Faculty result = facultyRepository.findById(id).orElse(null);
        logger.debug("{} факультет найден по id {}", result, id);
        return result;
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Вызван метод редактирования факультета");
           return  facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Вызван метод удаления факультета по id");
           facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties()
    {
        logger.info("Вызван метод вывода всех факультетов");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("Вызван метод поиска факультетов по цвету");
        return facultyRepository.findByColorEquals(color);
    }

    public Collection<Faculty> findByNameOrColor(String name, String color) {
        logger.info("Вызван метод поиска факультета по названию или цвету");
        return facultyRepository.findByNameOrColorIgnoreCase(name, color);
    }
    public Collection<Student> getStudentsByFaculty(long id) {
        logger.info("Вызван метод вывода всех студентов факультета");
           return studentRepository.findStudentByFacultyId(id);
    }
    public String getFacultyNameWithLongestName() {
        logger.info("Was invoked method for find longest faculty name");
        return getAllFaculties().stream()
                .map(e -> e.getName())
                .reduce("", (a, b) -> a.length() > b.length() ? a : b);
    }
    public ResponseEntity<Integer> calculateFormula() {
        logger.info("Вызов метода расчета суммы по формуле");

        long start = System.nanoTime();
        Integer result = Stream.iterate(1, a -> a + 1)
                .limit(1000000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
        long finish = System.nanoTime();
        long elapsed = finish - start; //
        System.out.println(elapsed);
        return ResponseEntity.ok(result);
    }

}
