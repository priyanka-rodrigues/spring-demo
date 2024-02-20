package com.example.service;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.entity.Subject;
import com.example.repository.AddressRepository;
import com.example.repository.StudentRepository;
import com.example.repository.SubjectRepository;
import com.example.request.SubjectCreateRequest;
import com.example.request.StudentCreateRequest;
import com.example.request.StudentUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepo;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;


    public List<Student> getStudentList() {
        return studentRepo.findAll();
    }

    public Student getStudent(int id) {

       Optional <Student> student =  studentRepo.findById(id);

        return student.orElse(null);
    }

    public List<Student> getAllStudentsWithPagination(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize);

        return studentRepo.findAll(pageRequest).getContent();
    }

    public List<Student> findByFirstName(String firstName) {
        List<Student> students = studentRepo.findByFirstName(firstName);

        if (students.size() > 0) {
            return students;
        }
        else return null;
    }
    public List<Student> findByLastName(String lastName) {
        List<Student> students = studentRepo.findByLastName(lastName);
        if (students.size() > 0) {
            return students;
        }
        else return null;
    }

    public List<Student>findByFirstNameAndLastName(String firstName, String lastName) {
        List<Student> students = studentRepo.findByFirstNameAndLastName(firstName,lastName);
        if (students.size() > 0) {
            return students;
        }
        else return null;
    }

    public List<Student>getFirstNameAndLastName(String firstName, String lastName) {
        List<Student> students = studentRepo.getByFirstNameAndLastName(firstName,lastName);
        if (students.size() > 0) {
            return students;
        }
        else return null;
    }

    public Integer updateFirstNameWithJpql(int id, String firstName) {
         return studentRepo.updateFirstName(id,firstName);

    }

//    public List<Student>findByFirstNameIn(InQueryRequest inQueryRequest) {
//        List<Student> students = studentRepo.findByFirstNameIn(firstName);
//        if (students.size() > 0) {
//            return students;
//        }
//        else return null;
//    }

    public List<Student> getLikeFirstName(String firstName) {
       // System.out.println("first NME IS"+ firstName);
        return studentRepo.findByFirstNameContains(firstName);
    }

    public List<Student> getByCity(String city) {
      //  return studentRepo.findByAddressCity(city);
        return studentRepo.getByCity(city);
    }

    public List<Student> startsWith(String firstName) {
        System.out.println("first NME IS"+ firstName);
        return studentRepo.findByFirstNameStartsWith(firstName);
    }

    public List<Student> endsWith(String lastname) {
      //  System.out.println("first NME IS"+ firstName);
        return studentRepo.findByLastNameEndsWith(lastname);
    }


    public Student createStudent(StudentCreateRequest studentCreateRequest) {
        Student student = new Student(studentCreateRequest);

        Address address = new Address();
        address.setCity(studentCreateRequest.getCity());
        address.setStreet(studentCreateRequest.getStreet());;
        address = addressRepository.save(address);
        student.setAddress(address);

        student = studentRepo.save(student);
        List<Subject> subjectList = new ArrayList<>();
        for(SubjectCreateRequest request: studentCreateRequest.getSubjectRequestList()){
            Subject subject = new Subject();
            subject.setSubjectName(request.getSubjectName());
            subject.setMarks(request.getMarks());
            subject.setStudent(student);
            subjectList.add(subject);
        }

        subjectRepository.saveAll(subjectList);
        student.setSubjectList(subjectList);
        //System.out.println(student.getId());
        return student;

    }

    public Student updateStudent(StudentUpdateRequest studentUpdateRequest) {
        Student student = studentRepo.findById(studentUpdateRequest.getId()).get();

                if(studentUpdateRequest.getFirstName() !=null  && !studentUpdateRequest.getFirstName().equals(" ")) {
                    student.setFirstName(studentUpdateRequest.getFirstName());
                }

                if(studentUpdateRequest.getLastName() !=null  && !studentUpdateRequest.getLastName().equals(" ")) {
                    student.setLastName(studentUpdateRequest.getLastName());
                }
                student = studentRepo.save(student);

                return student;

        }
        public String deleteStudent(int id) {
            Optional<Student> studentOptional = studentRepo.findById(id);
            if(studentOptional.isPresent()) {
                studentRepo.deleteById(id);
                return("Student with id" + id+ " "+" deleted successfully");
            }
            else return "student with id " +id+ " " + " not found";
        }
    }

