package com.example.response;

import com.example.entity.Student;
import lombok.*;

import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String street;
    private String city;



    public StudentResponse(Student student) {
        System.out.println("In student response constructor");
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.fullName = student.getFirstName() + " " + student.getLastName();
        this.street = student.getAddress().getStreet();
        this.city = student.getAddress().getCity();


        System.out.println("first name is"+ student.getFirstName());
    }
}
