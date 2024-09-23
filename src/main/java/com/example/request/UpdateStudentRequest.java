package com.example.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentRequest {
    @NotNull(message = "Student ID is required")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
