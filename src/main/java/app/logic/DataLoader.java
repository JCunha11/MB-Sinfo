package app.logic;

import app.beans.Booking;
import app.beans.Dealer;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Iterator;


public class DataLoader {

    public static void loadData(List<Booking> bookings,List<Dealer> dealers){

        try {
            byte[] jsonData = Files.readAllBytes(Paths.get("dataset.json"));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonData);
            JsonNode dealersNode = rootNode.path("dealers");
            Iterator<JsonNode> d = dealersNode.elements();
            while (d.hasNext()) {
                dealers.add(objectMapper.treeToValue(d.next(), Dealer.class));
            }
            JsonNode bookingsNode = rootNode.path("bookings");
            Iterator<JsonNode> b = bookingsNode.elements();
            while (b.hasNext()) {
                bookings.add(objectMapper.treeToValue(b.next(), Booking.class));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}