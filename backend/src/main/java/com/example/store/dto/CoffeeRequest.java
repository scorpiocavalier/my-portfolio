package com.example.store.dto;

public record CoffeeRequest(
  String name,
  Double price,
  String description,
  String size
) {}
