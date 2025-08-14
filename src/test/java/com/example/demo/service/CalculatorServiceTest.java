package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorServiceTest {

  private final CalculatorService calculatorService = new CalculatorService();

  @Test
  void testAdd() {
    assertEquals(5, calculatorService.add(2, 3));
  }

  @Test
  void testSubtract() {
    assertEquals(1, calculatorService.subtract(3, 2));
  }
}
