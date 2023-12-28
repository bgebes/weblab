package com.example.weblab;

import com.example.weblab.student.Student;
import com.example.weblab.student.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class StudentRepositoryTests {
    @Autowired
    private StudentRepository repository;

    @Test
    public void testAddNew() {
        Student student = new Student();
        student.setFirstName("Çınar");
        student.setLastName("Civan");
        student.setEmail("cinar@ornek.com");
        student.setPassword("123456abcdef");

        Student savedStudent = repository.save(student);

        Assertions.assertThat(savedStudent).isNotNull();
        Assertions.assertThat(savedStudent.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<Student> students = repository.findAll();
        Assertions.assertThat(students).hasSizeGreaterThan(0);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testUpdate() {
        Integer studentID = 1;
        String newestPassword = "654321fedcba";

        Optional<Student> optionalStudent = repository.findById(studentID);
        Assertions.assertThat(optionalStudent).isPresent();

        Student student = optionalStudent.get();
        student.setPassword(newestPassword);
        repository.save(student);

        Student updatedStudent = repository.findById(studentID).get();
        Assertions.assertThat(updatedStudent.getPassword()).isEqualTo(newestPassword);
    }

    @Test
    public void testGet() {
        Integer studentID = 1;

        Optional<Student> optionalStudent = repository.findById(studentID);
        Assertions.assertThat(optionalStudent).isPresent();
    }

    @Test
    public void testDelete() {
        Integer studentID = 3;

        Optional<Student> optionalStudent = repository.findById(studentID);
        Assertions.assertThat(optionalStudent).isPresent();

        repository.deleteById(studentID);
        Optional<Student> optionalDeletedStudent = repository.findById(studentID);
        Assertions.assertThat(optionalDeletedStudent).isNotPresent();
    }
}
