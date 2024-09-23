package com.example.repository;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.request.InQueryRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository //we will use this interface to interact with DB
public interface StudentRepository extends JpaRepository<Student,Long> {
//    CrudRepository<>
//    PagingAndSortingRepository<>// JPA repository is combination of Crud and PagingSorting

    //Get Records By column using JPA
     List<Student> findByFirstName(String firstName) ;// when we call this method it will create native Sql query, it will hit the db and get the data
    //Get records by 2 column using JPA
     List<Student> findByFirstNameAndLastName(String firstName, String lastName); //passing arguements order should be similar with the method name

    //OR query using JPA
    List<Student> findByFirstNameOrLastName(String firstName, String lastName); //Using method proxy giving by JPA

    //In query using JPA
    List<Student> findByFirstNameIn(List<String> firstNames);

    //Like Query using JPA

    List<Student> findByFirstNameContains(String firstName);

    //STart Query using JPA
    List<Student> findByFirstNameStartsWith(String firstName);

    //FInd all students whose firstName ends with "son" word
    List<Student> findByFirstNameEndsWith(String firstName);


    //JPQL Query
    @Query("From Student where firstName = :firstName and lastName = :lastName")
    List<Student> getByFristNameAndLastName(String firstName , String lastName);

    @Modifying
    @Transactional
    @Query("Update Student set firstName = :firstName where id= :id")
    Integer updateFirstName(Long id, String firstName); // you have only 2 return types(void & Integer) when You add @Modifying annotation

    @Modifying
    @Transactional
    @Query("Delete from Student where firstName = :firstName")
    Integer deleteFirstName(String firstName);

    public List<Student> findByAddressCity(String city);

    //JPQL
//    @Query("From Student where address.city = :city")
//    List<Student> getByAddressCity(String City);
}


