package com.shivtutorial.WebwithPostgresql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class NormalController {

    @Autowired
    private StuRepo repo;

    // ✅ Home page
    @GetMapping("/")
    public String home() {
        return "index"; // Renders index.html
    }

    // ✅ Show Add Student Form
    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "addStudent";
    }

    // ✅ Save Student (Add or Update)
    @PostMapping("/save")
    public String saveData(@ModelAttribute Student stu) {
        repo.save(stu);
        return "redirect:/list";
    }

    // ✅ Show All Students
    @GetMapping("/list")
    public String showAll(Model model) {
        List<Student> students = repo.findAll();
        model.addAttribute("students", students);
        return "listStudents";
    }

    // ✅ Edit Student (Reuse Add Form)
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") Long id, Model model) {
        Optional<Student> student = repo.findById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            return "addStudent"; // Same form for edit
        }
        return "redirect:/list";
    }

    // ✅ Delete Student
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        repo.deleteById(id);
        return "redirect:/list";
    }

    // ✅ View Student Details
    @GetMapping("/view/{id}")
    public String viewStudent(@PathVariable("id") Long id, Model model) {
        Optional<Student> student = repo.findById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            return "viewStudent"; // You need to create this page
        }
        return "redirect:/list";
    }
}
