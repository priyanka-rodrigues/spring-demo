package com.example.entity;

import com.example.request.StudentCreateRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "STUDENT")
@NoArgsConstructor

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Transient
    private String fullName;
    @OneToOne
    @JoinColumn(name ="aid")
    private Address address;

    @OneToMany(mappedBy = "student")
    private List<Subject> subjectList;

    public Student(StudentCreateRequest studentCreateRequest) {
        this.firstName = studentCreateRequest.getFirstName();
        this.lastName = studentCreateRequest.getLastName();
        this.fullName = studentCreateRequest.getFirstName() + " " + studentCreateRequest.getLastName();
    }
}
