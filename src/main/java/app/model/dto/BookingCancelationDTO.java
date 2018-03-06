package app.model.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import org.apache.hadoop.io.Text;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BookingCancelationDTO {

    @NotNull
    private String id;

    @NotNull
    private Text cancelledReason;


    @JsonIgnore
    private final LocalDateTime cancelledAt = LocalDateTime.now();

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        return id;
    }
    
    public void setCancelledReason(Text cancelledReason) {
        this.cancelledReason = cancelledReason;
    }
    
    public Text getCancelledReason(){
        return cancelledReason;
    }

    public LocalDateTime getCanncelledAt(){
        return cancelledAt;
    }
}