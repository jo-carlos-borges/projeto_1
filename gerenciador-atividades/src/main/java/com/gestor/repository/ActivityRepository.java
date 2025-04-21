package com.gestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestor.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}