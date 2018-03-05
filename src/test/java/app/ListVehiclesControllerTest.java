package app;

import static org.hamcrest.Matchers.hasSize;
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

import app.beans.Dealer;
import app.beans.Vehicle;
import app.services.DataLoader;
import app.services.DealersDAO;
@RunWith(SpringRunner.class)
@SpringBootTest(classes= TestDrive.class)    
@WebAppConfiguration
public class ListVehiclesControllerTest{

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    
    private DealersDAO dealersDAO = new DealersDAO();

    private List<Dealer> dealers;
    private List<Vehicle> allVehicles;

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
        
        Vehicle v1 = new Vehicle("778a04fd-0a6a-4dc7-92bb-a7517608efc2", "A", "ELECTRIC", "MANUAL", availability);
        Vehicle v2 = new Vehicle("893d97bf-5a9d-4926-ace3-39ad0585c912", "AMG", "GASOLINE", "AUTO", availability);
        Vehicle v3 = new Vehicle("44a36bfa-ec8f-4448-b4c2-809203bdcb9e", "E", "ELECTRIC", "MANUAL", availability);
        Vehicle v4 = new Vehicle("d723b0bd-8eb0-4826-bf5d-44754005d174", "E", "ELECTRIC", "MANUAL", availability);
        allVehicles= new ArrayList<>();
        allVehicles.add(v1);
        allVehicles.add(v2);
        allVehicles.add(v3);
        allVehicles.add(v4);
        List<Vehicle> vehicles1= new ArrayList<>();
        vehicles1.add(v1);
        List<Vehicle> vehicles2= new ArrayList<>();
        vehicles2.add(v2);
        vehicles2.add(v4);
        List<Vehicle> vehicles3= new ArrayList<>();
        vehicles3.add(v3);

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
    public void listVehiclesByFuel() throws Exception{
        mockMvc.perform(get("/ListVehicles/fuel")).andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].id",is(allVehicles.get(0).getId())))
        .andExpect(jsonPath("$[0].model", is(allVehicles.get(0).getModel())))
        .andExpect(jsonPath("$[0].fuel",is(allVehicles.get(0).getFuel() )))
        .andExpect(jsonPath("$[0].transmission",is(allVehicles.get(0).getTransmission())))
        .andExpect(jsonPath("$[1].id",is(allVehicles.get(2).getId())))
        .andExpect(jsonPath("$[1].model", is(allVehicles.get(2).getModel())))
        .andExpect(jsonPath("$[1].fuel",is(allVehicles.get(2).getFuel() )))
        .andExpect(jsonPath("$[1].transmission",is(allVehicles.get(2).getTransmission())))
        .andExpect(jsonPath("$[2].id",is(allVehicles.get(1).getId())))
        .andExpect(jsonPath("$[2].model", is(allVehicles.get(1).getModel())))
        .andExpect(jsonPath("$[2].fuel",is(allVehicles.get(1).getFuel() )))
        .andExpect(jsonPath("$[2].transmission",is(allVehicles.get(1).getTransmission())));
       // .andExpect(jsonPath("$[0].availability[0]",is(allVehicles.get(0).getAvailability())))
    }

    @Test
    public void listVehiclesByModel() throws Exception{
        mockMvc.perform(get("/ListVehicles/model")).andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].id",is(allVehicles.get(0).getId())))
        .andExpect(jsonPath("$[0].model", is(allVehicles.get(0).getModel())))
        .andExpect(jsonPath("$[0].fuel",is(allVehicles.get(0).getFuel() )))
        .andExpect(jsonPath("$[0].transmission",is(allVehicles.get(0).getTransmission())))
        .andExpect(jsonPath("$[1].id",is(allVehicles.get(1).getId())))
        .andExpect(jsonPath("$[1].model", is(allVehicles.get(1).getModel())))
        .andExpect(jsonPath("$[1].fuel",is(allVehicles.get(1).getFuel() )))
        .andExpect(jsonPath("$[1].transmission",is(allVehicles.get(1).getTransmission())))
        .andExpect(jsonPath("$[2].id",is(allVehicles.get(2).getId())))
        .andExpect(jsonPath("$[2].model", is(allVehicles.get(2).getModel())))
        .andExpect(jsonPath("$[2].fuel",is(allVehicles.get(2).getFuel() )))
        .andExpect(jsonPath("$[2].transmission",is(allVehicles.get(2).getTransmission())));
       // .andExpect(jsonPath("$[0].availability[0]",is(allVehicles.get(0).getAvailability())))
    }

    @Test
    public void listVehiclesByTransmission() throws Exception{
        mockMvc.perform(get("/ListVehicles/transmission")).andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].id",is(allVehicles.get(1).getId())))
        .andExpect(jsonPath("$[0].model", is(allVehicles.get(1).getModel())))
        .andExpect(jsonPath("$[0].fuel",is(allVehicles.get(1).getFuel() )))
        .andExpect(jsonPath("$[0].transmission",is(allVehicles.get(1).getTransmission())))
        .andExpect(jsonPath("$[1].id",is(allVehicles.get(0).getId())))
        .andExpect(jsonPath("$[1].model", is(allVehicles.get(0).getModel())))
        .andExpect(jsonPath("$[1].fuel",is(allVehicles.get(0).getFuel() )))
        .andExpect(jsonPath("$[1].transmission",is(allVehicles.get(0).getTransmission())))
        .andExpect(jsonPath("$[2].id",is(allVehicles.get(2).getId())))
        .andExpect(jsonPath("$[2].model", is(allVehicles.get(2).getModel())))
        .andExpect(jsonPath("$[2].fuel",is(allVehicles.get(2).getFuel() )))
        .andExpect(jsonPath("$[2].transmission",is(allVehicles.get(2).getTransmission())));
       // .andExpect(jsonPath("$[0].availability[0]",is(allVehicles.get(0).getAvailability())))
    }

    @Test
    public void listVehiclesByDealer() throws Exception{
        mockMvc.perform(get("/ListVehicles/transmission")).andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].id",is(allVehicles.get(1).getId())))
        .andExpect(jsonPath("$[0].model", is(allVehicles.get(1).getModel())))
        .andExpect(jsonPath("$[0].fuel",is(allVehicles.get(1).getFuel() )))
        .andExpect(jsonPath("$[0].transmission",is(allVehicles.get(1).getTransmission())))
        .andExpect(jsonPath("$[1].id",is(allVehicles.get(0).getId())))
        .andExpect(jsonPath("$[1].model", is(allVehicles.get(0).getModel())))
        .andExpect(jsonPath("$[1].fuel",is(allVehicles.get(0).getFuel() )))
        .andExpect(jsonPath("$[1].transmission",is(allVehicles.get(0).getTransmission())))
        .andExpect(jsonPath("$[2].id",is(allVehicles.get(2).getId())))
        .andExpect(jsonPath("$[2].model", is(allVehicles.get(2).getModel())))
        .andExpect(jsonPath("$[2].fuel",is(allVehicles.get(2).getFuel() )))
        .andExpect(jsonPath("$[2].transmission",is(allVehicles.get(2).getTransmission())));
       // .andExpect(jsonPath("$[0].availability[0]",is(allVehicles.get(0).getAvailability())))
    }


    @Test
    public void wrongSortingParameter() throws Exception{
        mockMvc.perform(get("/ListVehicles/Wrong")).andExpect(status().isNotFound());
    }

}