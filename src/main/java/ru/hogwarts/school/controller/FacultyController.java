package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{count}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long count) {
        Faculty faculty = facultyService.findFaculty(count);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty creatFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{count}")
    public void deleteFaculty(@PathVariable long count) {
        facultyService.deleteFaculty(count);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculties());

    }
    @GetMapping("color")
    public ResponseEntity<Collection<Faculty>> findFaculties(@RequestParam(required = false) String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("nameAndColor")
    public ResponseEntity<Collection<Faculty>> findByNameOrColor(@RequestParam (required = false)String name, @RequestParam(required = false)String color) {
        Collection<Faculty> resultat = facultyService.findByNameOrColor(name, color);
        return ResponseEntity.ok(resultat);
    }
}
