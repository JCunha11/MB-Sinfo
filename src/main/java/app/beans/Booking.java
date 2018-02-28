package app.beans;

import java.time.*;

public class Booking{

    public String id;	//Unique identifier for a booking
    public String vehicleId;	//Vehicle identifier
    public String firstName;	//Customer's first name
    public String lastName;	//Customer's last name
    public String pickupDate;	//Day and time of the booking
    public String createdAt;	//Day and Time that the booking entry was created
    public String cancelledAt;	//Day and Time that this booking was cancelled
    public String cancelledReason;	//Reason for the booking cancelation

   /* public Booking(String id, String vehicleId, String firstName, String lastName, String pickupDate, String createdAt,
            String cancelledAt, String cancelledReason) {

        this.id = id;
        this.vehicleId = vehicleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pickupDate = pickupDate;
        this.createdAt = createdAt;
        this.cancelledAt = cancelledAt;
        this.cancelledReason = cancelledReason;

    }*/

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id=id;
    }  

    public String getVehicleId(){
        return vehicleId;
    }

    public void setVehicleId(String vehicleId){
        this.vehicleId=vehicleId;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName=lastName;
    }

    public String getPickUpDate(){
        return pickupDate;
    }

    public void setPickUpDate(String pickupDate){
        this.pickupDate=pickupDate;
    }
    
    public String getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(String createdAt){
        this.createdAt=createdAt;
    }

    public String getCancelledAt(){
        return cancelledAt;
    }

    public void setCancelledAt(String cancelledAt){
        this.cancelledAt=cancelledAt;
    }

    public String getCancelledReason(){
        return cancelledReason;
    }

    public void setCancelledReason(String cancelledReason){
        this.cancelledReason=cancelledReason;
    }
}

