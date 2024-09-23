package com.example.service;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.entity.Subject;
import com.example.repository.AddressRepository;
import com.example.repository.StudentRepository;
import com.example.repository.SubjectRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.CreateSubjectRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    //First thing we need to have an instance of student repository interface
    @Autowired
    StudentRepository studentRepository;//this will give us the instance of Studentrepository

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;
    public List<Student> getAllStudents() {
        return studentRepository.findAll(); //though we don't have any method in StudentRepository but we can use findall cuz it's extending JPA repository

    }

    public Student createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student(createStudentRequest);//so the constructor we will use , will pass the object

        Address address = new Address();
        address.setStreet(createStudentRequest.getStreet());
        address.setCity(createStudentRequest.getCity());
        address = addressRepository.save(address);//first we created a new record in address table
        student.setAddress(address); //then assigning foreign key value with the help of entity class
        studentRepository.save(student);//we need to pass object of our entity class
        //Save method whatever entity class you provided that object get persistent into the respective table

        List<Subject> subjectsList = new ArrayList<Subject>();
        if(createStudentRequest.getSubjectsLearning()!= null){
            for(CreateSubjectRequest createSubjectRequest : createStudentRequest.getSubjectsLearning()){
                Subject subject = new Subject();
                subject.setSubjectName(createSubjectRequest.getSubjectName());
                subject.setMakrsObtained(createSubjectRequest.getMarksObtained());
                subject.setStudent(student);
                subjectsList.add(subject);
            }
            subjectRepository.saveAll(subjectsList);
        }
        student.setLearningSubjects(subjectsList);
        return student;
    }

    public Student updateStudent(UpdateStudentRequest updateStudentRequest) {
        Student student = studentRepository.findById(updateStudentRequest.getId()).get();
        if (updateStudentRequest.getFirstName() != null && !updateStudentRequest.getFirstName().isEmpty()) {
            student.setFirstName(updateStudentRequest.getFirstName());
        }
        student.setLastName(updateStudentRequest.getLastName());
        student.setEmail(updateStudentRequest.getEmail());

        student = studentRepository.save(student);
        return student;

    }

    public String deleteStudent(long id) {

        studentRepository.deleteById(id);
        return "Student has been successfully deleted";
    }

    //get records by column
    public List<Student> getByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    //get records by 2 columns (understanding And query using Data JPA)
    public List<Student> getByFirstNameAndLastName(String firstName, String lastName) {
        //JPA Method Proxy
       // return studentRepository.findByFirstNameAndLastName(firstName, lastName);
        //JPQL Query
        return studentRepository.getByFristNameAndLastName(firstName, lastName);
    }

    ////get records by 2 columns (understanding OR query using Data JPA)
    public List<Student> getByFirstNameOrLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameOrLastName(firstName, lastName);
    }

    //get records by 2 columns (understanding In query using Data JPA)
    public List<Student> getByFirstNameIn(InQueryRequest inQueryRequest) {
        // Call repository method and pass the list of firstNames from inQueryRequest
        return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
    }

    //Pagination
    public List<Student> getAllWithPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return studentRepository.findAll(pageable).getContent();
    }

    //Sorting
    public List<Student> getAllWithSorting() {
        Sort sort = Sort.by(Sort.Direction.ASC, "firstName" , "lastName" , "email");
        return studentRepository.findAll(sort);
    }

    //Like Query Using JPA
    public List<Student> like(String firstName) {
        return studentRepository.findByFirstNameContains(firstName);
    }
    
    //StartsWith query Using JPA
    public List<Student> startsWith(String firstName){
        return studentRepository.findByFirstNameStartsWith(firstName);
    }


    //Find all studnet endsWith "son" word  query Using JPA
    public List<Student> endsWith(String firstName){
        return studentRepository.findByFirstNameEndsWith(firstName);
    }

    //Update using JPQL Query
    public Integer updateStudentWithJpql(Long id, String firstName){
        return studentRepository.updateFirstName(id, firstName);
    }

    //Delete using JPQL Query
    public Integer deleteStudentWithJpql(String firstName){
        return studentRepository.deleteFirstName(firstName);
    }

    //get all the students who belongs same city
    public List<Student> getByCity(String city){
      return studentRepository.findByAddressCity(city);
       // return studentRepository.getByAddressCity(city);
    }
}


//so throughout your application you can call getAllStudents method inside your student service class and you can get all your records in your particular table