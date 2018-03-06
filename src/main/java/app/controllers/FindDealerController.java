package app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.model.Dealer;
import app.service.DealerFinderService;


/**
 * Controller responsible for directing the GET request to the FindDealerService
 */
@RestController
public class FindDealerController{

    
    private DealerFinderService finder = new DealerFinderService();

    
    @GetMapping("/FindDealer/")
    public ResponseEntity<?> getSortedVehicles(@RequestParam String model, @RequestParam String fuelType, @RequestParam String transmission, @RequestParam Number userLatitude, @RequestParam Number userLongitude){
        Dealer dealer = finder.findDealer(model, fuelType, transmission, userLatitude, userLongitude);
        if(dealer!=null){
            return new ResponseEntity<Dealer>(dealer,HttpStatus.OK);
        }
        return new ResponseEntity<String>("No dealer found with model: "+model+", fuel Type: "+ fuelType+", tansmission: "+ transmission,HttpStatus.NOT_FOUND);
    }

    

}