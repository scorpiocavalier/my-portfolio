package com.example.store.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.dto.CoffeeRequest;
import com.example.store.dto.CoffeeResponse;
import com.example.store.entity.Coffee;
import com.example.store.service.CoffeeService;

@RestController
@RequestMapping("/api/coffees")
public class CoffeeController {
  @Autowired
  private CoffeeService coffeeService;

  @GetMapping
  public ResponseEntity<List<CoffeeResponse>> getAll() {
    List<CoffeeResponse> coffees = coffeeService.getAll().stream()
      .map(this::toResponse)
      .collect(Collectors.toList());
    return ResponseEntity.ok(coffees); // status 200
  }

  @GetMapping("/{id}")
  public ResponseEntity<CoffeeResponse> getOne(@PathVariable Long id) {
    Coffee coffee = coffeeService.getOne(id);
    return ResponseEntity.ok(toResponse(coffee)); // status 200
  }

  @PostMapping
  public ResponseEntity<CoffeeResponse> create(@RequestBody CoffeeRequest request) {
    Coffee coffee = toEntity(request);
    Coffee savedCoffee = coffeeService.create(coffee);
    return ResponseEntity
      .status(HttpStatus.CREATED) // status 201
      .header("Location", "/api/coffees/" + savedCoffee.getId())
      .body(toResponse(savedCoffee));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CoffeeResponse> update(@PathVariable Long id, @RequestBody CoffeeRequest request) {
    Coffee coffee = toEntity(request);
    Coffee updatedCoffee = coffeeService.update(id, coffee);
    return ResponseEntity.ok(toResponse(updatedCoffee)); // status 200
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    coffeeService.delete(id);
    return ResponseEntity.noContent().build(); // status 204
  }

  // Helper method: Entity → DTO
  private CoffeeResponse toResponse(Coffee coffee) {
    return new CoffeeResponse(
      coffee.getId(),
      coffee.getName(),
      coffee.getPrice(),
      coffee.getDescription(),
      coffee.getSize()
    );
  }

  // Helper method: DTO → Entity
  private Coffee toEntity(CoffeeRequest request) {
    return new Coffee(
      request.name(),
      request.price(),
      request.description(),
      request.size()
    );
  }
}
