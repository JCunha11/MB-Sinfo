package app.service;

import app.model.Booking;
import app.model.Dealer;
import app.model.Vehicle;

public class BookingService{
    /**
     * Method used to create a new booking
     * @param booking  the booking to be createds
     */
    public Booking createBooking(Booking booking) throws IllegalArgumentException{
    BookingsDAO bookingsDAO = new BookingsDAO();
       for(Dealer dealer : DealersDAO.dealers.values()){
           Vehicle vehicle = dealer.getVehicleById(booking.getVehicleId());
           if(vehicle!=null&&vehicle.isAvailable(booking.getPickupDate())){
               for(Booking b : bookingsDAO.list().values()){
                   if(b.getVehicleId().equals(booking.getVehicleId())&&b.getCancelledAt()==null){
                        throw new IllegalArgumentException("Vehicle already booked");
                   }
               }
               Booking newBooking= new Booking(booking.getVehicleId(), booking.getFirstName(), booking.getLastName(), booking.getPickupDate());
               
               return bookingsDAO.create(newBooking);
           }
       }

       throw new IllegalArgumentException("Vehicle dos not exist");
    }
}