package abstraction;

abstract class Vehicle {
    String brand;

    // these are abstract methods
    abstract double calculateFuelEfficiency();
    
    public abstract String toString();

    // abstract class can have the constructor
    public Vehicle(String brand) {

        System.out.println("Vehicle constructor called");
        this.brand = brand;
    }

    // this is a concrete method
    public String getbrand() {

        return brand;

    }
}

class Car extends Vehicle {

    double distanceTravelled;
    double fuelConsumed;

    public Car(String brand, double distanceTravelled,double fuelConsumed) {

        // calling Vehicle constructor
        super(brand);
        System.out.println("Car constructor called");
        this.distanceTravelled = distanceTravelled;
        this.fuelConsumed=fuelConsumed;
    }

    @Override
    double calculateFuelEfficiency() {

        return distanceTravelled / fuelConsumed;
    }

    @Override
    public String toString() {

        return "Car brand is " + super.getbrand()
                + " and fuel efficiency is : " + calculateFuelEfficiency();
    }
}

class Motorcycle extends Vehicle {

    int engineCapacity;
    double mileage;

    public Motorcycle(String brand, int engineCapacity,double mileage)
             {
        // calling Shape constructor
        super(brand);
        System.out.println("Motorcycle constructor called");
        this.engineCapacity = engineCapacity;
        this.mileage = mileage;
    }

    @Override
    double calculateFuelEfficiency() {
        return mileage;
    }

    @Override
    public String toString() {
        return "Motorcycle brand is " + super.getbrand()
                + "and fuel efficiency is : " + calculateFuelEfficiency();
    }
}

public class Abstraction {
    public static void main(String[] args) {
        Vehicle s1 = new Car("Toyota",500.0,40.0);
        Vehicle s2 = new Motorcycle("Yamaha",150,45.5);

        System.out.println(s1.toString());
        System.out.println(s2.toString());
    }
}
 