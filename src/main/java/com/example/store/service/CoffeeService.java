package com.example.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.store.entity.Coffee;
import com.example.store.repository.CoffeeRepository;

@Service
public class CoffeeService {

  @Autowired
  private CoffeeRepository coffeeRepository;

  public List<Coffee> getAll() {
    return coffeeRepository.findAll();
  }

  public Coffee getOne(Long id) {
    return coffeeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Coffee not found with id: " + id));
  }

  public Coffee create(Coffee coffee) {
    return coffeeRepository.save(coffee);
  }

  public Coffee update(Long id, Coffee coffee) {
    Coffee existingCoffee = getOne(id);

    existingCoffee.setName(coffee.getName());
    existingCoffee.setPrice(coffee.getPrice());
    existingCoffee.setDescription(coffee.getDescription());
    existingCoffee.setSize(coffee.getSize());

    return coffeeRepository.save(existingCoffee);
  }

  public void delete(Long id) {
    getOne(id);
    coffeeRepository.deleteById(id);
  }
}
