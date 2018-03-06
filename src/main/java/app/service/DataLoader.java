package app.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import app.model.Dealer;
import app.model.Vehicle;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataLoader {

    /**
     * Class responsible for the loading of data from the provided JSON file
     * 
     */
    public void loadData() {
            try {
                byte[] jsonData = Files.readAllBytes(Paths.get("dataset.json"));
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(jsonData);
                DealersDAO dealersDAO = new DealersDAO();
                dealersDAO.loadDealers(rootNode, objectMapper);
                BookingsDAO bookingsDAO = new BookingsDAO();
                bookingsDAO.loadBookings(rootNode, objectMapper);
                for(Dealer d : DealersDAO.dealers.values()){
                    String dealerId = d.getId();
                    for(Vehicle v: d.getVehicles()){
                        v.setDealerId(dealerId);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        
    }

    

}