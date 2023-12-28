package com.example.weblab.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public List<Student> listAll() {
        return (List<Student>) repository.findAll();
    }

    public void save(Student student) {
        repository.save(student);
    }

    public Student get(Integer id) throws StudentNotFoundException {
        Optional<Student> result = repository.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        throw new StudentNotFoundException("Could not find any students with ID: " + id);
    }

    public void delete(Integer id) throws StudentNotFoundException {
        Optional<Student> optionalStudent = repository.findById(id);

        if (optionalStudent.isEmpty()) {
            throw new StudentNotFoundException("Could not find any students with ID: " + id);
        }

        repository.deleteById(id);
    }
}
