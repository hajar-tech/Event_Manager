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

        Event event = new Event();
        eventRepository.findById(event.getId()).orElseThrow(()-> new RuntimeException("Event not found"));
        newReservation.setEvent(event);

        User user = new User();
        userReposetory.findById(user.getId()).orElseThrow(()-> new RuntimeException("User Not Found"));
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
