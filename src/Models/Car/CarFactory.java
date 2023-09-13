package Models.Car;

public class CarFactory {
    public static Car createCar(String type, String carID,String model, String build, String color) {
        switch(type) {
            case "mechanical":
                return new ConcreteMechanicalCar(carID,model, build, color);
            case "electrical":
                return new ConcreteElectricCar(carID,model, build, color);
            default:
                throw new IllegalArgumentException("Invalid car type: " + type);
        }
    }
}
