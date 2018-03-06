package app;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.apache.hadoop.io.Text;
import app.model.Booking;
import app.model.dto.BookingCancelationDTO;
import app.model.dto.BookingCreationDTO;


public class BookingMappingTest{

    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void checkBookingMapping(){
        BookingCreationDTO creation = new BookingCreationDTO();
        creation.setFirstName("Johnny");
        creation.setLastName("English");
        creation.setPickupDate(LocalDateTime.of(2018,03,05,10,00,00));
        creation.setVehicleId("44a36bfa-ec8f-4448-b4c2-809203bdcb9e");

        Booking booking = modelMapper.map(creation,Booking.class);
        assertEquals(booking.getFirstName(), creation.getFirstName());
        assertEquals(booking.getLastName(), creation.getLastName());
        assertEquals(booking.getCreatedAt(), creation.getCreatedAt());
        assertEquals(booking.getPickupDate(), creation.getPickupDate());
        assertEquals(booking.getVehicleId(),creation.getVehicleId());


        BookingCancelationDTO cancelation = new BookingCancelationDTO();
        cancelation.setCancelledReason(new Text("reson"));
        modelMapper.map(cancelation,booking);
        assertEquals(cancelation.getCancelledReason(), booking.getCancelledReason());
        assertEquals(cancelation.getCancelledReason(), booking.getCancelledReason());
        assertEquals(creation.getCreatedAt(), booking.getCreatedAt());
    }
}