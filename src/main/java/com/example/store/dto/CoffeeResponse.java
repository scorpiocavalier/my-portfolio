package com.example.store.dto;

public record CoffeeResponse(
  Long id,
  String name,
  Double price,
  String description,
  String size
) {}
