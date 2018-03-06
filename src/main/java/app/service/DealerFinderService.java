package app.service;

import app.model.Dealer;
import app.model.Vehicle;

public class DealerFinderService {
    /**
     * Method used to find the closest dealer that has a car with the specified characteristics
     * @param model model of the car
     * @param fuelType type of fuel of the car
     * @param transmission type of transmission of the car
     * @param userLatitude user's latitude
     * @param userLongitude  user's longitude
     */
    public Dealer findDealer(String model, String fuelType, String transmission, Number userLatitude, Number userLongitude){
        Double shortestDistance = -1.0;
        Dealer dealer = null;
        for(Dealer d: DealersDAO.dealers.values()){
            for(Vehicle vehicle : d.getVehicles()){
                if(vehicle.getModel().equals(model)&&vehicle.getFuel().equals(fuelType)&&vehicle.getTransmission().equals(transmission)){
                    Double distance=distance(userLatitude,d.getLatitude(),userLongitude,d.getLongitude());
                    if(shortestDistance==-1.0){
                        shortestDistance=distance;
                        dealer=d;
                    }
                    else if(distance < shortestDistance){
                        shortestDistance=distance;
                        dealer=d;
                    }
                }
            }
        }
        return dealer;
    }

    private Double distance(Number latitude1, Number latitude2, Number longitude1, Number longitude2) {
        final int r = 6371; // R of the earth
        Double lat1 = latitude1.doubleValue();
        Double lat2 = latitude2.doubleValue();
        Double long1 = longitude1.doubleValue();
        Double long2 = longitude2.doubleValue();
        Double latitudeDistance = toRad(lat2 - lat1);
        Double longitudeDistance = toRad(long2 - long1);
        Double a = Math.sin(latitudeDistance / 2) * Math.sin(latitudeDistance / 2) + Math.cos(toRad(lat1))
                * Math.cos(toRad(lat2)) * Math.sin(longitudeDistance / 2) * Math.sin(longitudeDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return r * c;


    }

    private Double toRad(Double value) {
        return value * Math.PI / 180;
    }
}