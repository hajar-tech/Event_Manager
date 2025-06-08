package com.example.EventManager.repository;

import com.example.EventManager.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResirvationRepository extends JpaRepository<Reservation,Long> {
}
