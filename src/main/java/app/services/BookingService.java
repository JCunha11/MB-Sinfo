package app.services;

import app.beans.Booking;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class BookingService{

    public void createBooking(String firstName, String lastName, String vehicleId, LocalDateTime pickupDate, String dealerId){
        //DealersDAO dealersDAO = new DealersDAO();
        
        try{
            if(DealersDAO.dealers.get(dealerId).getVehicleById(vehicleId).isAvailable(pickupDate)){
                for(Booking booking : BookingsDAO.bookings.values()){
                    if(booking.getVehicleId().equals(vehicleId)&&booking.getCancelledAt()!=null){
                        throw new IllegalArgumentException("Vehicle already booked");
                    }
                }
                BookingsDAO bookingDAO = new BookingsDAO();
                bookingDAO.create(new Booking(vehicleId, firstName, lastName, pickupDate));
            }
            //verificar se o pode ser booked nessa data
            
            //verificar se o veiculo ja esta ocupado
        }catch(NoSuchElementException e){
           //ver comportamento de lançar excepcao para cima
        }

    }


}