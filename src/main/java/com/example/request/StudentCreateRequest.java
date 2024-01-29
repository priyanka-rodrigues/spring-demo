package com.example.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateRequest {
   // private int id;
    @NotBlank(message = "Name cannot be blank")
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private List<SubjectCreateRequest> subjectRequestList;


}