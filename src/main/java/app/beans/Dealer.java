package app.beans;

import java.util.List;


public class Dealer{

private String id;	//Unique identifier for a dealer
private String name;	//Name of the Dealer
private Number latitude;	//Latitude of dealer's location
private Number longitude;	//Longitude of dealer's location
private List<Vehicle> vehicles; //List  of vehicles in that dealer
private List<String> closed;	//List of days that the dealer is closed for business

/*public Dealer(String id, String name, Number latitude, Number longitude, List<Vehicle> vehicles, List<String> closed){
    this.id=id;
    this.name=name;
    this.latitude=latitude;
    this.longitude=longitude;
    this.vehicles=vehicles;
    this.closed=closed;
}*/

public String getId(){
    return id;
}

public void setId(String id){
    this.id= id;
}

public String getName(){
    return name;
}

public void setName(String name){
    this.name= name;
}

public Number getLatitude(){
    return latitude;
}

public void setLatitude(Number latitude){
    this.latitude= latitude;
}

public Number getLongitude(){
    return longitude;
}

public void setLongitude(Number longitude){
    this.longitude= longitude;
}

public List<Vehicle> getVehicles(){
    return vehicles;
}

public void setVehicles(List<Vehicle> vehicles){
    this.vehicles=vehicles;
}

public List<String> getClosed(){
    return closed;
}

public void setClosed(List<String> closed ){
    this.closed=closed;
}

}
