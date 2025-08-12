package com.workshop.CarRental.Service;

import com.workshop.CarRental.Entity.CarRentalUser;
import com.workshop.CarRental.Repository.CarRentalRepository;
import com.workshop.Entity.Booking;
import com.workshop.Repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarRentalBookingService {

    @Autowired
    private BookingRepo bookingRepository;

    @Autowired
    private CarRentalRepository carRentalRepository;

    public Booking createBookingForUser(int userId, Booking bookingDetails) {
        CarRentalUser user = carRentalRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("CarRentalUser not found with id: " + userId));
        
        bookingDetails.setCarRentalUser(user);
        return bookingRepository.save(bookingDetails);
    }
}