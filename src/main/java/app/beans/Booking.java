package app.beans;

import java.time.LocalDateTime;
import org.w3c.dom.Text;

public class Booking{

    public String id;	//Unique identifier for a booking
    public String vehicleId;	//Vehicle identifier
    public String firstName;	//Customer's first name
    public String lastName;	//Customer's last name
    public LocalDateTime pickupDate;	//Day and time of the booking
    public LocalDateTime createdAt;	//Day and Time that the booking entry was created
    public LocalDateTime cancelledAt;	//Day and Time that this booking was cancelled
    public Text cancelledReason;	//Reason for the booking cancelation

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

    public LocalDateTime getPickUpDate(){
        return pickupDate;
    }

    public void setPickUpDate(LocalDateTime pickupDate){
        this.pickupDate=pickupDate;
    }
    
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }

    public LocalDateTime getCancelledAt(){
        return cancelledAt;
    }

    public void setCancelledAt(LocalDateTime cancelledAt){
        this.cancelledAt=cancelledAt;
    }

    public Text getCancelledReason(){
        return cancelledReason;
    }

    public void setCancelledReason(Text cancelledReason){
        this.cancelledReason=cancelledReason;
    }
}

