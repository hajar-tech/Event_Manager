package com.example.EventManager.dto;

import java.time.LocalDate;

public record EventDto (
  long id,
    String titre,
    String description,
    LocalDate date,
  int placesDisponibles,
  String category,
  String location){
}
