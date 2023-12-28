package com.example.weblab.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping("/students")
    public String showStudentList(Model model) {
        List<Student> listStudents = service.listAll();
        model.addAttribute("listStudents", listStudents);

        return "students";
    }

    @GetMapping("/students/new")
    public String showNewForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("pageTitle", "Add New Student");

        return "student_form";
    }

    @PostMapping("/students/save")
    public String saveStudent(Student student, RedirectAttributes redirectAttributes) {
        service.save(student);
        redirectAttributes.addFlashAttribute("message", "The student has been saved successfully!");
        redirectAttributes.addFlashAttribute("messageType", "success");

        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Student student = service.get(id);
            model.addAttribute("student", student);
            model.addAttribute("pageTitle", "Edit Student (ID: " + id + " )");

            return "student_form";
        } catch (StudentNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "The student not found!");
            redirectAttributes.addFlashAttribute("messageType", "danger");

            return "redirect:/students";
        }
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message", "The student has been deleted successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } catch (StudentNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "The student not found!");
            redirectAttributes.addFlashAttribute("messageType", "danger");
        }

        return "redirect:/students";
    }
}
