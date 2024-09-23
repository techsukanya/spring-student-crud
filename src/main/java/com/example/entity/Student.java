package com.example.entity;

import com.example.request.CreateStudentRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Transient //this is not representing any column in our student this is for internal use only.
    private String fullName;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    //Student relationship with subject
    @OneToMany(mappedBy = "student")
    private List<Subject> learningSubjects;
    public Student(CreateStudentRequest createStudentRequest){
        this.firstName = createStudentRequest.getFirstName();
        this.lastName = createStudentRequest.getLastName();
        this.email = createStudentRequest.getEmail();
        this.fullName = createStudentRequest.getFirstName()+" "+createStudentRequest.getLastName();
    }

}
