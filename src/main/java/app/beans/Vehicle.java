package app.beans;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Comparator;

public class Vehicle {

    private String id; //Unique identifier for a vehicle
    private String model; //Vehicle model
    private String fuel; //Fuel type
    private String transmission; //transmission type
    private Map<String, List<String>> availability; //days of the week (keys) and the hours (values) available for booking.

    public Vehicle(String id, String model, String fuel, String transmission, Map<String,List<String>> availability){
        this.id=id;
        this.model=model;
        this.fuel=fuel;
        this.transmission=transmission;
        this.availability=availability;
    }

    public Vehicle(){
        
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public Map<String, List<String>> getAvailability() {
        return availability;
    }

    public void setAvailability(Map<String, List<String>> availability) {
        this.availability = availability;
    }

    /*Comparator by model*/
    public static Comparator<Vehicle> vehicleModelComparator = new Comparator<Vehicle>() {
        public int compare(Vehicle v1, Vehicle v2) {
            String vehicle1Model = v1.getModel().toUpperCase();
            String vehicle2Model = v2.getModel().toUpperCase();

            return vehicle1Model.compareTo(vehicle2Model);
        }
    };

    /*Comparator by FuelType*/
    public static Comparator<Vehicle> vehicleFuelTypeComparator = new Comparator<Vehicle>() {
        public int compare(Vehicle v1, Vehicle v2) {
            String vehicle1FuelType = v1.getFuel().toUpperCase();
            String vehicle2FuelType = v2.getFuel().toUpperCase();

            return vehicle1FuelType.compareTo(vehicle2FuelType);
        }
    };

    /*Comparator by Transmission*/
    public static Comparator<Vehicle> vehicleTransmissionComparator = new Comparator<Vehicle>() {
        public int compare(Vehicle v1, Vehicle v2) {
            String vehicle2Transmission = v2.getTransmission().toUpperCase();
            String vehicle1Transmission = v1.getTransmission().toUpperCase();

            return vehicle1Transmission.compareTo(vehicle2Transmission);
        }
    };

    public boolean isAvailable(LocalDateTime pickupDate){
        String day = pickupDate.getDayOfWeek().toString().toLowerCase();
        String time = pickupDate.getHour()+""+pickupDate.getMinute();
        
        if(availability.containsKey(day)){
            if(availability.get(day).contains(time)){
                return true;
            }
        }
        return false;
    }
}