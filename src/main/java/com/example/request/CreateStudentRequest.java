package com.example.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateStudentRequest {
    @JsonProperty("first_name")
    @NotBlank(message = "First Name is Required") //server side validation
    private String firstName;

    private String lastName;
    private String email;

    private String street;
    private String city;

    private List<CreateSubjectRequest> subjectsLearning;

}
