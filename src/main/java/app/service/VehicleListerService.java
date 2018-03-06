package app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.model.Dealer;
import app.model.Vehicle;

public class VehicleListerService {
    /**
     * Method used to list all vehicles according to a specific attribute
     * @param attribute  the parameter by which the cars will be sorted
     */
    public List<Vehicle> listVehiclesByAttribute(String attribute) {
        List<Vehicle> vehicles;

        switch (attribute) {

        case "model":

            vehicles = new ArrayList<Vehicle>();
            for (Dealer dealer : DealersDAO.dealers.values()) {
                vehicles.addAll(dealer.getVehicles());
            }
            Collections.sort(vehicles, Vehicle.vehicleModelComparator);
            return vehicles;

        case "fuel":

            vehicles = new ArrayList<Vehicle>();
            for (Dealer dealer : DealersDAO.dealers.values()) {
                vehicles.addAll(dealer.getVehicles());
            }
            Collections.sort(vehicles, Vehicle.vehicleFuelTypeComparator);
            return vehicles;

        case "transmission":
            vehicles = new ArrayList<Vehicle>();
            for (Dealer dealer : DealersDAO.dealers.values()) {
                vehicles.addAll(dealer.getVehicles());
            }

            Collections.sort(vehicles, Vehicle.vehicleTransmissionComparator);
            return vehicles;

        case "dealer":
            vehicles = new ArrayList<Vehicle>();
            for (Dealer dealer : DealersDAO.dealers.values()) {
                vehicles.addAll(dealer.getVehicles());
            }
            return vehicles;
        
        default:
            return vehicles=null;
        }

    }
    
}