package com.example.response;

import com.example.entity.Student;
import com.example.entity.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
//@ToString
//@EqualsAndHashCode
//@Data
public class StudentResponse {
    //@JsonIgnore //Json with Jackson Library
    private long id;
    @JsonProperty("first_name") // Json with Jackson Library
    private String firstName;
    private String lastName;
    private String email;
    private String fullName;
    private String street;
    private String city;
    private List<SubjectResponse> learningSubjets;

    public StudentResponse (Student student){
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        this.fullName = student.getFirstName() + " "+ student.getLastName();
        this.street = student.getAddress().getStreet();
        this.city = student.getAddress().getCity();//this two things we have assigned in address object that is inside student entity class

        if(student.getLearningSubjects()!= null){
            learningSubjets = new ArrayList<SubjectResponse>();
            for(Subject subject : student.getLearningSubjects()){
                learningSubjets.add(new SubjectResponse(subject));
            }

        }
    }

}
