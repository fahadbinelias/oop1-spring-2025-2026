package problem;

class Vehicle{
    public double speed(){
        return 0;
    }
}
class   Car extends Vehicle{
    double engineSize;
    double fuelCapacity;
    Car(){
        engineSize=0.0;
        fuelCapacity=0.0;
    }
    Car(double engineSize,double fuelCpacity){
        this.engineSize=engineSize;
        this.fuelCapacity=fuelCapacity;
    }
    @Override
    public double speed(){
        return engineSize*fuelCapacity*0.5;
    }
    
} 
class   Bike extends Vehicle{
    double wheelSize;
    double frameWeight;
    Bike(){
        wheelSize=0.0;
        frameWeight=0.0;
    }
    Bike(double wheelSize,double frameWeight){
        this.wheelSize=wheelSize;
        this.frameWeight=frameWeight;
    }
    @Override
    public double speed(){
        return frameWeight/wheelSize*10;
    }
    
} 
class   Boat extends Vehicle{
    double hullLength;
    double displacement;
    Boat(){
        hullLength=0.0;
        displacement=0.0;
    }
    Boat(double hullLength,double displacement){
        this.hullLength=hullLength;
        this.displacement=displacement;
    }
    @Override
    public double speed(){
        return displacement/hullLength*3;
    }
    
}
public class Polymorphism {
    public static void main(String[] args) {

        
        Vehicle[] Vehicles = new Vehicle[3];

        
        Vehicles[0] = new Car(5.0,3);
        Vehicles[1] = new Bike(6.0, 4.0);
        Vehicles[2] = new Boat(50.0, 100.0);

        
        for (Vehicle vehicle : Vehicles) {
            System.out.println(vehicle.getClass().getSimpleName() + " Speed = " + vehicle.speed());
        }
    }
}
  

 
