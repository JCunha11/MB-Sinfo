package app.beans;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.w3c.dom.Text;

public class Booking{

    final private String id;	//Unique identifier for a booking
    private String vehicleId;	//Vehicle identifier
    private String firstName;	//Customer's first name
    private String lastName;	//Customer's last name
    private LocalDateTime pickupDate;	//Day and time of the booking
    private LocalDateTime createdAt;	//Day and Time that the booking entry was created
    private LocalDateTime cancelledAt;	//Day and Time that this booking was cancelled
    private Text cancelledReason;	//Reason for the booking cancelation

  
    public Booking( String vehicleId, String firstName, String lastName, LocalDateTime pickupDate) {

        this.id=String.valueOf(UUID.randomUUID());
        this.vehicleId = vehicleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pickupDate = pickupDate;
        this.createdAt = LocalDateTime.now();
    }
    
    @JsonCreator
    private Booking(@JsonProperty("id") String id, @JsonProperty("vehicleId") String vehicleId, @JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName,@JsonProperty("pickupDate")  LocalDateTime pickupDate,@JsonProperty("createdAt")  LocalDateTime createdAt,@JsonProperty("cancelledAt")  LocalDateTime cancelledAt,@JsonProperty("cancelledReason")  Text cancelledReason){
        this.id = id;
        this.vehicleId = vehicleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pickupDate = pickupDate;
        this.createdAt = createdAt;
        this.cancelledAt= cancelledAt;
        this.cancelledReason = cancelledReason;
    }

    public String getId(){
        return id;
    }


   /* public void setId(String id){
        this.id = id;
    }*/  

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

    public boolean isCancelled(){
        return cancelledAt!=null;
    }
}

