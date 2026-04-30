package interfaceoop;

interface Drivable {
    void start();
    void stop();
    default void describe() {
        System.out.println("This is a drivable vehicle.");
    }
}
abstract class Vehicle implements Drivable {
    private String brand;
    public Vehicle(String brand) {
        this.brand = brand;
        System.out.println("Vehicle constructor called");
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getBrand() {
        return brand;
    }    
    public abstract double calculateFuelEfficiency();
    public abstract String toString();
}
class Car extends Vehicle {
    private double distanceTravelled;
    private double fuelConsumed;
    public Car(String brand, double distanceTravelled, double fuelConsumed) {
        super(brand);
        this.distanceTravelled = distanceTravelled;
        this.fuelConsumed = fuelConsumed;
        System.out.println("Car constructor called");
    }
    public void setDistanceTravelled(double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }
    public double getDistanceTravelled() {
        return distanceTravelled;
    }
    public void setFuelConsumed(double fuelConsumed) {
        this.fuelConsumed = fuelConsumed;
    }
    public double getFuelConsumed() {
        return fuelConsumed;
    }
    @Override
    public double calculateFuelEfficiency() {
        return distanceTravelled / fuelConsumed;
    }
    @Override
    public String toString() {
        return "Car brand is "+getBrand()+" and fuel efficiency is: "+calculateFuelEfficiency();
    }
    @Override
    public void start() {
        System.out.println("Car "+getBrand()+" engine started.");
    }
    @Override
    public void stop() {
        System.out.println("Car "+getBrand()+" engine stopped.");
    }
}
class Motorcycle extends Vehicle {
    private int engineCapacity;
    private double mileage;
    public Motorcycle(String brand, int engineCapacity, double mileage) {
        super(brand);
        this.engineCapacity = engineCapacity;
        this.mileage = mileage;
        System.out.println("Motorcycle constructor called");
    }
    public int getEngineCapacity() {
        return engineCapacity;
    }
    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
    public double getMileage() {
        return mileage;
    }
    public void setMileage(double mileage) {
        this.mileage = mileage;
    }
    @Override
    public double calculateFuelEfficiency() {
        return mileage;
    }
    @Override
    public String toString() {
        return "Motorcycle brand is " + getBrand()+" and fuel efficiency is: "+calculateFuelEfficiency();
    }
    @Override
    public void start() {
        System.out.println("Motorcycle " +getBrand()+" engine started.");
    }
    @Override
    public void stop() {
        System.out.println("Motorcycle "+getBrand()+" engine stopped.");
    }
}
public class main {
    public static void main (String[] args) {
        Vehicle car = new Car("Toyota", 500, 40);
        Vehicle bike = new Motorcycle("Yamaha", 150, 45.5);
        System.out.println(car.toString());
        System.out.println(bike.toString());
        car.start();
        car.stop();
        bike.start();
        bike.stop();
        car.describe();
        bike.setBrand("Honda");
        System.out.println(bike.toString());
    }
}
