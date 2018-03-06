/*package app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import app.model.Booking;
import app.model.dto.BookingCreationDTO;
import app.service.BookingsDAO;
import app.service.DataLoader;
@RunWith(SpringRunner.class)
@SpringBootTest(classes= TestDrive.class)    
@WebAppConfiguration
public class BookingControllerTest{

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    
    private BookingsDAO bookingsDAO = new BookingsDAO();
    private DataLoader dataLoader = new DataLoader();

    private List<Booking> bookings;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
    
    @Before
    public void setup(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        
  
    }

    @Test
    public void createBookingSuccess() throws Exception{
        BookingCreationDTO newBooking = new BookingCreationDTO();
        newBooking.setFirstName("Johnny");
        newBooking.setLastName("English");
        newBooking.setVehicleId("f00d3390-58ae-4510-987e-a5bfe14973ff");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(newBooking);  
        
        mockMvc.perform(post("/book/").contentType("application/json").content(json))
        .andExpect(status().isCreated()); 
        for(Booking booking : bookingsDAO.list().values()){
            if(!booking.getCreatedAt().equals(newBooking.getCreatedAt())){
                fail();
            }
        }
        
    }
    
    //TODO test creation with booked vehicle
    

    //TODO test creation with wrong parameters

    //TODO test cancelation success
       
        

    


}*/