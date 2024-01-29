package com.example.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class StudentUpdateRequest {

        @NotNull(message = "Student id is mandatory")
        private int id;
        private String firstName;
        private String lastName;
  }
