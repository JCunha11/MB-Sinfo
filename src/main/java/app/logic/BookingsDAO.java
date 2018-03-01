package app.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import app.beans.Booking;

public class BookingsDAO{

    private static List<Booking> bookings;

    public void loadBookings(JsonNode rootNode, ObjectMapper objectMapper){
        bookings = new ArrayList<>();
        JsonNode bookingsNode = rootNode.path("bookings");
                Iterator<JsonNode> b = bookingsNode.elements();
                while (b.hasNext()) {
                    objectMapper.registerModule(new JavaTimeModule());
                    try {
						bookings.add(objectMapper.treeToValue(b.next(), Booking.class));
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
                }
    }

    public List<Booking> list(){
        return bookings;
    }

    public Booking get(String id){
        for(Booking booking : bookings){
            if(booking.getId().equals(id)){
                return booking;
            }
        }
        return null;
    }

    public Booking create(Booking booking){
        booking.setId(String.valueOf(UUID.randomUUID()));
        bookings.add(booking);
        return booking;
    }

    public Booking update(String id, Booking booking){
        for(Booking b : bookings){
            if(b.getId().equals(id)){
                booking.setId(id);
                bookings.remove(b);
                bookings.add(booking);
                return booking;
                
            }
        }
        return null;
    }

}