package app.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.model.Booking;
import app.model.dto.BookingCancelationDTO;
import app.model.dto.BookingCreationDTO;
import app.service.BookingService;
import app.service.BookingsDAO;
import app.service.CancelBookingService;

/**
 * 
 * Controler that directs the requests for booking and cancelation to the Booking and CancelBooking Services.
 * 
 */
@RestController
public class BookingController{

    @Autowired
    private ModelMapper modelMapper;

    private BookingsDAO bookingsDAO = new BookingsDAO();

    
    @PostMapping("/book/")
    public ResponseEntity <?> createBooking(@RequestBody BookingCreationDTO bookingDTO){
        Booking booking = convertCreationDTOToBooking(bookingDTO);
        BookingService bookingService= new BookingService();
        try{
            bookingService.createBooking(booking);
            BookingCreationDTO answer = convertToCreationDTO(booking);
            return new ResponseEntity<>(answer,HttpStatus.CREATED);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cancel/")
    public ResponseEntity<?> cancelBooking(@RequestBody BookingCancelationDTO bookingDTO){
        
        CancelBookingService cancelBookingService = new CancelBookingService();
        Booking booking = cancelBookingService.cancelBooking(bookingDTO);
        if(booking==null){
            return new ResponseEntity<>("No Booking found for ID " + bookingDTO.getId(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(bookingDTO,HttpStatus.OK);
    }

    private BookingCreationDTO convertToCreationDTO(Booking booking){
        BookingCreationDTO bookingDTO = modelMapper.map(booking,BookingCreationDTO.class);
        bookingDTO.setFirstName(booking.getFirstName());
        bookingDTO.setLastName(booking.getLastName());
        bookingDTO.setPickupDate(booking.getPickupDate());
        bookingDTO.setVehicleId(booking.getVehicleId());
        return bookingDTO;
    }

    private Booking convertCreationDTOToBooking(BookingCreationDTO bookingDTO){
        Booking booking = modelMapper.map(bookingDTO,Booking.class);
        booking.setFirstName(bookingDTO.getFirstName());
        booking.setLastName(bookingDTO.getLastName());
        booking.setPickupDate(bookingDTO.getPickupDate());
        booking.setVehicleId(bookingDTO.getVehicleId());
        return booking;
    }

    
}