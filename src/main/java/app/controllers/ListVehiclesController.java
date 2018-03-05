package app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import app.beans.Vehicle;
import app.services.VehicleListerService;

@RestController
public class ListVehiclesController{

    
    
    @GetMapping("/ListVehicles/{sortby}")
    public ResponseEntity<?> getSortedVehicles(@PathVariable("sortby") String attribute){
        
        VehicleListerService lister = new VehicleListerService();
        List<Vehicle> vehicles = lister.listVehiclesByAttribute(attribute);
       
        if(lister.listVehiclesByAttribute(attribute)!=null){
            return new ResponseEntity<List<Vehicle>>( vehicles,HttpStatus.OK);
        }
        return new ResponseEntity<>( "Wrong Sorting Attribute",HttpStatus.NOT_FOUND);
    }

}