package com.example.controller;

import com.example.entity.Student;
import com.example.request.CreateStudentRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/student/")
public class StudentController {
//    @Value("${app.name:Default Demo App* }")
//    private String appName;
//    @GetMapping("/get")
//   // @RequestMapping(value = "/get", method = RequestMethod.GET)
//    public StudentResponse getStudent(){
//        //return "Hello Student";
//        StudentResponse obj = new StudentResponse(1,"Sukanya","Saha");
//        return obj;
//    }
    @Autowired
    StudentService studentService;
    @GetMapping("/getAll")
    public List<StudentResponse> getAllStudents (){
        List<Student> studentList =  studentService.getAllStudents();
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student)); // it will convert each and every object of student to the studentresponse and then add
        });
        return studentResponseList;

    }
    @PostMapping("/create")
    public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest){
        //@RequestBody annotation thid will convert the Json basically in the request payload to Java Object.
       Student student = studentService.createStudent(createStudentRequest);
       return new StudentResponse(student);

    }

    @PutMapping("/update")
    public StudentResponse updateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest){
        Student student = studentService.updateStudent(updateStudentRequest);
        return new StudentResponse(student);
    }

//    @DeleteMapping("/delete")
//    public String deleteStudent(@RequestParam Long id){
//        return studentService.deleteStudent(id);
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
       return studentService.deleteStudent(id);
    }

    //Get records by column using Data JPPA
    @GetMapping("/getByFirstName/{firstName}")
    public List<StudentResponse> getByFirstName(@PathVariable String firstName){
        List<Student> studentList = studentService.getByFirstName(firstName);
        //converting List of Student to list of student response
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student)); // it will convert each and every object of student to the studentresponse and then add
        });
        return studentResponseList;

    }

    //Get firstName and Last Name both (Understanding And query using JPA)
    @GetMapping("/getByFirstNameAndLastName/{firstName}/{lastName}")
    public List<StudentResponse> getByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName){
       List<Student>  studentsList = studentService.getByFirstNameAndLastName(firstName, lastName);
        //converting List of Student to list of student response
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentsList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student)); // it will convert each and every object of student to the studentresponse and then add
        });
        return studentResponseList;
    }

    //Get firstName and Last Name both (Understanding And query using JPA)
    @GetMapping("/getByFirstNameOrLastName/{firstName}/{lastName}")
    public List<StudentResponse> getByFirstNameOrLastName(@PathVariable String firstName, @PathVariable String lastName){
        List<Student>  studentsList = studentService.getByFirstNameOrLastName(firstName, lastName);
        //converting List of Student to list of student response
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentsList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student)); // it will convert each and every object of student to the studentresponse and then add
        });
        return studentResponseList;
    }



    //get firstname from differnt column understnading In query
    @GetMapping("getByFirstNameIn")
    public List<StudentResponse> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest){
        List<Student> studentList = studentService.getByFirstNameIn(inQueryRequest);
        //converting List of Student to list of student response
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student)); // it will convert each and every object of student to the studentresponse and then add
        });
        return studentResponseList;
    }
    @GetMapping("getAllWithPagination")
    public List<StudentResponse> getAllWithPagination(@RequestParam int pageNo, @RequestParam int pageSize){
        List<Student> studentList = studentService.getAllWithPagination(pageNo, pageSize);
        //converting List of Student to list of student response
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student)); // it will convert each and every object of student to the studentresponse and then add
        });
        return studentResponseList;
    }


    //Sorting using JPA
    @GetMapping("getAllWithSorting")
    public List<StudentResponse> getAllWithSorting(){
        List<Student> studentList = studentService.getAllWithSorting();
        //converting List of Student to list of student response
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student)); // it will convert each and every object of student to the studentresponse and then add
        });
        return studentResponseList;
    }

    //Like Query using JPA
    @GetMapping("/like/{firstName}")
    public List<StudentResponse> like(@PathVariable String firstName){
        List<Student> studentList = studentService.like(firstName);
        //converting List of Student to list of student response
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student)); // it will convert each and every object of student to the studentresponse and then add
        });
        return studentResponseList;

    }

    //Starts with JPA

    //Like Query using JPA
    @GetMapping("/startsWith/{firstName}")
    public List<StudentResponse> startsWith(@PathVariable String firstName){
        List<Student> studentList = studentService.startsWith(firstName);
        //converting List of Student to list of student response
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student)); // it will convert each and every object of student to the studentresponse and then add
        });
        return studentResponseList;

    }
    //Find All the STudents whose firstName ends with "son" Word
    @GetMapping("/endsWith/{firstName}")
    public List<StudentResponse> endsWith(@PathVariable String firstName){
        List<Student> studentList = studentService.endsWith(firstName);
        //converting List of Student to list of student response
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student->{
            studentResponseList.add(new StudentResponse(student)); // it will convert each and every object of student to the studentresponse and then add
        });
        return studentResponseList;

    }

    //PutMapping Update student with JPQL
    @PutMapping("updateFirstName/{id}/{firstName}")
    public String updateStudentWithJpql(@PathVariable Long id, @PathVariable String firstName){
        return studentService.updateStudentWithJpql(id, firstName)+ "Student(s) updated";
    }


    //Delete Mapping using JPQL
    @DeleteMapping("deleteByFirstName/{firstName}")
    public String deleteStudentJpql(@PathVariable String firstName) {
        return studentService.deleteStudentWithJpql(firstName) + "Student(S) deleted";
    }

        //Join query with Spring Data JPA
       // get all the students which belong from same city
        @GetMapping("/getByCity/{city}")
        public List<StudentResponse> getByCity(@PathVariable String city) {
            List<Student> studentList = studentService.getByCity(city);

            // Converting List of Student to list of StudentResponse
            List<StudentResponse> studentResponseList = new ArrayList<>();
            studentList.forEach(student -> {
                studentResponseList.add(new StudentResponse(student));
            });
            return studentResponseList;
        }
    }


