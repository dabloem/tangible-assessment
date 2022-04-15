package com.abnamro.assessment.repository;

import com.abnamro.assessment.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    @Query(nativeQuery = true, value = "select count(person_name) from person where person_name = :personName")
    Integer isNameExist(@Param("personName") String personName);
}
