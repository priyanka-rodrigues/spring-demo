package com.example.controller;

import com.example.entity.Student;
import com.example.request.StudentCreateRequest;
import com.example.request.StudentUpdateRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/student")
class StudentController {
    @Autowired
    StudentService studentService;

    private String appName;

    @GetMapping()
    public List<StudentResponse> getAllStudents() {
        List<Student> studentList = studentService.getStudentList();
        List<StudentResponse> studentResponseList = new ArrayList<>();

        for (Student student : studentList) {
            studentResponseList.add(new StudentResponse(student));
        }

        return studentResponseList;
    }


    @GetMapping("get/{id}")
    public Student getStudentById(@PathVariable int id) {

        Student student = studentService.getStudent(id);
        return student;
    }

    @GetMapping("getByFirstName/{firstName}")
    public List<Student> getByFirstName(@PathVariable String firstName) {
        List<Student> students = studentService.findByFirstName(firstName);
        return students;
    }

    @GetMapping("getByLastName/{lastName}")
    public List<Student> getByLastName(@PathVariable String lastName) {
        List<Student> students = studentService.findByLastName(lastName);
        return students;
    }

    @GetMapping("getByFirstNameAndLastName/{firstName}/{lastName}")
    public List<Student> getByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        List<Student> students = studentService.findByFirstNameAndLastName(firstName, lastName);
        return students;
    }


    @GetMapping("/getByCity/{city}")
    public List<Student> getByCity(@PathVariable String city) {
        List<Student> students = studentService.getByCity(city);
        return students;
    }
//

//    @GetMapping("getByFirstNameIn/{firstName}")
//    public List<Student> getByFirstNameIn(@PathVariable String firstName) {
//        List<Student> students = studentService.findByFirstNameIn(firstName);
////        return students;
//    }

    @GetMapping("getAllWithPaginationn")
    public List<Student> getAllStudentsWithPagination(@RequestParam int pageNo, @RequestParam int pageSize) {
        List<Student> students = studentService.getAllStudentsWithPagination(pageNo,pageSize);
        return students;
    }

    @GetMapping("getLikeFirstName/{firstName}")
    public List<StudentResponse> getLikeFirstName(@PathVariable String firstName) {
        List<Student> students = studentService.getLikeFirstName(firstName);
        List<StudentResponse> studentResponseList = new ArrayList<>();

        for (Student student : students) {
            studentResponseList.add(new StudentResponse(student));
        }

        return studentResponseList;
    }


    @GetMapping("startsWith/{firstName}")
    public List<StudentResponse> getstartsWith(@PathVariable String firstName) {
        List<Student> students = studentService.startsWith(firstName);
        List<StudentResponse> studentResponseList = new ArrayList<>();

        for (Student student : students) {
            studentResponseList.add(new StudentResponse(student));
        }

        return studentResponseList;
    }

    @GetMapping("endsWith/{lastName}")
    public List<StudentResponse> getEndsWith(@PathVariable String lastName) {
        List<Student> students = studentService.endsWith(lastName);
        List<StudentResponse> studentResponseList = new ArrayList<>();

        for (Student student : students) {
            studentResponseList.add(new StudentResponse(student));
        }

        return studentResponseList;
    }

    @PostMapping()
    public StudentResponse createStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest) {
        Student student = studentService.createStudent(studentCreateRequest);
        return new StudentResponse(student);
    }

    @PutMapping("/update")
    public StudentResponse updateStudent(@Valid @RequestBody StudentUpdateRequest studentUpdateRequest) {
        Student student = studentService.updateStudent(studentUpdateRequest);
        return new StudentResponse(student);
    }

    @PutMapping("/updateWithJpql/{id}/{firstName}")
    public String updateFirstNameWithJpql(@PathVariable int id,  @PathVariable String firstName) {
        return studentService.updateFirstNameWithJpql(id,firstName) + " " + "students updated";
    }


//    @DeleteMapping("/delete") // using Request Param
//    public String deleteStduent(@RequestParam int id) {
//        return studentService.deleteStudent(id);
//    }

    @DeleteMapping("delete/{id}")
    public String deleteStduent(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }

}