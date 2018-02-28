package app.beans;

import java.util.List;
import java.util.Map;

public class Vehicle{

    private String id;	//Unique identifier for a vehicle
    private String model;	//Vehicle model
    private String fuel; //Fuel type
    private String transmission; //transmission type
    private Map<String,List<String>> availability;  //days of the week (keys) and the hours (values) available for booking.
    
    
    /*public Vehicle(String id, String model, String fuel, String transmission, Map<String,List<String>> availability){
        this.id=id;
        this.model=model;
        this.fuel=fuel;
        this.transmission=transmission;
        this.availability=availability;
    }*/

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id= id;
    }

    public String getModel(){
        return model;
    }

    public void setModel(String model){
        this.model= model;
    }

    public String getFuel(){
        return fuel;
    }

    public void setFuel(String fuel){
        this.fuel= fuel;
    }

    public String getTransmission(){
        return transmission;
    }

    public void setTransmission(String transmission){
        this.transmission= transmission;
    }

    public Map<String,List<String>> getAvailability(){
        return availability;
    }

    public void setAvailability(Map<String,List<String>> availability){
        this.availability=availability;
    }

}