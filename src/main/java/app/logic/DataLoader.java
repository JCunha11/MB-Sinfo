package app.logic;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import app.logic.DealersDAO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.beans.Booking;

public class DataLoader {


    public void loadData() {
        
        
            try {
                byte[] jsonData = Files.readAllBytes(Paths.get("dataset.json"));
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(jsonData);
                DealersDAO dealersDAO = new DealersDAO();
                dealersDAO.LoadDealers(rootNode, objectMapper);
                BookingsDAO bookingsDAO = new BookingsDAO();
                bookingsDAO.loadBookings(rootNode, objectMapper);
            } catch (IOException e) {
                e.printStackTrace();
            }
        
    }

    

}