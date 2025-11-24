package com.example.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.store.entity.Coffee;

@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

}
