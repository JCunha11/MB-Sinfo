package app.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import app.model.Booking;
/**
 * Class responsible for the access to the Bookings
 */
public class BookingsDAO{

    public static Map<String,Booking> bookings;

    /**
     * Method that loads the bookings
     */
    public void loadBookings(JsonNode rootNode, ObjectMapper objectMapper){
        bookings = new HashMap<>();
        JsonNode bookingsNode = rootNode.path("bookings");
                Iterator<JsonNode> b = bookingsNode.elements();
                while (b.hasNext()) {
                    objectMapper.registerModule(new JavaTimeModule());
                    try {
                        Booking booking=objectMapper.treeToValue(b.next(), Booking.class);
						bookings.put(booking.getId(),booking);
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
                }
    }

    public Map<String,Booking> list(){
        return bookings;
    }

    public Booking create(Booking booking){
        bookings.put(booking.getId(),booking);
        return booking;
    }

    public Booking update(Booking booking){
        return bookings.replace(booking.getId(), booking);
    }

    public void clearBookings(){
        bookings.clear();
    }

}