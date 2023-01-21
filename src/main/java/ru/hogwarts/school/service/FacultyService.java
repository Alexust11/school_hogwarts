package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
       public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {

        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return  facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
         facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> findByColor(String color) {

        return facultyRepository.findByColorEquals(color);
    }

    public Collection<Faculty> findByNameOrColor(String name, String color) {
        return facultyRepository.findByNameOrColorIgnoreCase(name, color);
    }
}
