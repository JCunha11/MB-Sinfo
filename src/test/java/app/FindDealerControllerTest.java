package app;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

import app.model.Dealer;
import app.model.Vehicle;
import app.service.DealersDAO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= TestDrive.class)    
@WebAppConfiguration
public class FindDealerControllerTest{

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    
    private DealersDAO dealersDAO = new DealersDAO();

    private List<Dealer> dealers;

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
        dealersDAO.clearDealers();
        ArrayList<String> hours = new ArrayList<>();
        ArrayList<String> days = new ArrayList<>();
        hours.add("1000");
        days.add("monday");
        Map<String,List<String>> availability= new HashMap<>();
        availability.put("monday", hours);
        
        Vehicle v1 = new Vehicle(String.valueOf(UUID.randomUUID()), "AMG", "ELECTRIC", "AUTO", availability);
        Vehicle v2 = new Vehicle(String.valueOf(UUID.randomUUID()), "AMG", "ELECTRIC", "AUTO", availability);
        Vehicle v3 = new Vehicle(String.valueOf(UUID.randomUUID()), "AMG", "ELECTRIC", "MANUAL", availability);
        Vehicle v4 = new Vehicle(String.valueOf(UUID.randomUUID()), "E", "ELECTRIC", "MANUAL", availability);
        List<Vehicle> vehicles1= new ArrayList<>();
        vehicles1.add(v1);
        vehicles1.add(v2);
        vehicles1.add(v3);
        List<Vehicle> vehicles2= new ArrayList<>();
        vehicles2.add(v2);
        vehicles2.add(v3);
        List<Vehicle> vehicles3= new ArrayList<>();
        vehicles3.add(v4);

        Dealer dealer1 = new Dealer("846679bd-5831-4286-969b-056e9c89d74c", "MB Albufeira", 37.104404, -8.236308, vehicles1, days);
        Dealer dealer2 = new Dealer("bbcdbbad-5d0b-45ef-90ac-3581b997e063", "MB Lisboa", 38.746721, -9.229837, vehicles2, days);
        Dealer dealer3 = new Dealer("d4f4d287-1ad6-4968-a8ff-e9e0009ad5d1", "MB Porto", 41.156287, -8.645977, vehicles3, days);
        dealers = new ArrayList<>();
        dealers.add(dealer1);
        dealers.add(dealer2);
        dealers.add(dealer3);
        dealersDAO.create(dealer1);
        dealersDAO.create(dealer2);
        dealersDAO.create(dealer3);
    }

    @Test
    public void dealerFound() throws Exception{
        mockMvc.perform(get("/FindDealer/?model=AMG&fuelType=ELECTRIC&transmission=AUTO&userLatitude=37.104401&userLongitude=-8.236302")).andExpect(status().isOk())
.andExpect(jsonPath("$.id",is(this.dealers.get(0).getId())))
.andExpect(jsonPath("$.name",is(this.dealers.get(0).getName())))
.andExpect(jsonPath("$.latitude",is(this.dealers.get(0).getLatitude())))
.andExpect(jsonPath("$.longitude",is(this.dealers.get(0).getLongitude())))
.andExpect(jsonPath("$.closed",is(this.dealers.get(0).getClosed())));
    }

    @Test
    public void dealerNotFound() throws Exception{
        mockMvc.perform(get("/FindDealer/?model=AMG&fuelType=GASOLINE&transmission=AUTO&userLatitude=37.104401&userLongitude=-8.236302")).andExpect(status().isNotFound());

    }

}