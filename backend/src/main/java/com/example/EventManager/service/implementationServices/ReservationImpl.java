package com.example.EventManager.service.implementationServices;


import com.example.EventManager.entity.Event;
import com.example.EventManager.entity.Reservation;
import com.example.EventManager.entity.User;
import com.example.EventManager.repository.EventRepository;
import com.example.EventManager.repository.ResirvationRepository;
import com.example.EventManager.repository.UserReposetory;
import com.example.EventManager.service.interfaceServices.ReservationService;
import org.springframework.stereotype.Service;

@Service
public class ReservationImpl implements ReservationService {
    final ResirvationRepository resirvationRepository;
    final EventRepository eventRepository;
    final UserReposetory userReposetory;


    public ReservationImpl(ResirvationRepository resirvationRepository, EventRepository eventRepository, UserReposetory userReposetory) {
        this.resirvationRepository = resirvationRepository;
        this.eventRepository = eventRepository;
        this.userReposetory = userReposetory;
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        Reservation newReservation = new Reservation();
        newReservation.setUserName(reservation.getUserName());
        newReservation.setNumberOfSeats(reservation.getNumberOfSeats());

       Long eventId = reservation.getEvent().getId();
       int userId = reservation.getUser().getId();

       if(eventId == null || userId == -1) {
           throw new IllegalArgumentException("Event id and User id are required");
       }

       Event event = eventRepository.findById(eventId).orElseThrow(()-> new RuntimeException("Event not found"));

       User user = userReposetory.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));

       newReservation.setEvent(event);
       newReservation.setUser(user);
        //mis à jour des places

        int checkNumPlace = event.getPlacesDisponibles() - reservation.getNumberOfSeats();
        if(checkNumPlace < 0) {
            throw new RuntimeException("Number of Seats not enough");
        }
        event.setPlacesDisponibles(checkNumPlace);
        eventRepository.save(event);


        return resirvationRepository.save(newReservation);
    }
}
