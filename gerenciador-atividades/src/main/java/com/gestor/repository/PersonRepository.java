package com.gestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestor.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}