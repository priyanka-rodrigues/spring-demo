package com.example.repository;

import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByFirstName(String firstName);
    List<Student> findByLastName(String lastName);
    List<Student> findByFirstNameAndLastName(String firstname, String lastName);
    List<Student> findByFirstNameContains(String firstName);
    List<Student> findByFirstNameStartsWith(String firstName);
    List<Student> findByLastNameEndsWith(String lastName);

    @Query("from Student where firstName = :firstName and lastName = :lastName")
    List<Student> getByFirstNameAndLastName(String firstName, String lastName);

    @Transactional
    @Modifying
    @Query("Update Student set firstName = :firstName where id =:id" )
    Integer updateFirstName(int id, String firstName);

    List<Student> findByAddressCity(String city);

    @Query("From Student where address.city =:city")
            List<Student> getByCity(String city);
}




