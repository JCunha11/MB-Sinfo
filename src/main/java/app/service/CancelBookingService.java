package app.service;

import app.model.Booking;
import app.model.dto.BookingCancelationDTO;

public class CancelBookingService{
    /**
     * Method used to cancel bookings
     * @param bookingDTO the bookingDTO with the id of the booking and reason for cancelation 
     */
    public Booking cancelBooking(BookingCancelationDTO bookingDTO){
        Booking booking =null;
        if(( booking = BookingsDAO.bookings.get(bookingDTO.getId()))!=null){
            booking.setCancelledAt(bookingDTO.getCanncelledAt());
            booking.setCancelledReason(bookingDTO.getCancelledReason());
            BookingsDAO bookingsDao = new BookingsDAO();
            bookingsDao.update(booking);
        }
        return booking;
    }
}