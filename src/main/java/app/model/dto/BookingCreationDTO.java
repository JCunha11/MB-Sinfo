package app.model.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class BookingCreationDTO {

    @NotNull
    private String vehicleId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private LocalDateTime pickupDate;

    @JsonIgnore
    private LocalDateTime createdAt;

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleId(){
        return vehicleId;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getFirstName(){
        return vehicleId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setPickupDate(LocalDateTime pickupDate) {
        this.pickupDate = pickupDate;
    }

    
    public LocalDateTime getPickupDate(){
        return pickupDate;
    }

    
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

}