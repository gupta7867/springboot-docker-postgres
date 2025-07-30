package com.shivtutorial.WebwithPostgresql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class MyController {

    @Autowired
    private StuRepo repo;

    // ✅ Get all students
    @GetMapping
    public List<Student> getAll() {
        return repo.findAll();
    }

    // ✅ Get student by ID
    @GetMapping("/{id}")
    public Optional<Student> getById(@PathVariable Long id) {
        return repo.findById(id);
    }

    // ✅ Add new student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return repo.save(student);
    }

    // ✅ Update student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        return repo.findById(id).map(stu -> {
            stu.setName(updatedStudent.getName());
            stu.setEmail(updatedStudent.getEmail());
            return repo.save(stu);
        }).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // ✅ Delete student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        repo.deleteById(id);
        return "Student deleted successfully!";
    }
}
