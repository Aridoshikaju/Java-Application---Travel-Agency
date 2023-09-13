package Models.Car;
public class ConcreteElectricCar extends ElectricCar {
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;
    public ConcreteElectricCar(String carId,String model, String build, String color) {
        super(carId,model, build, color);
    }

    public boolean isModelAvailable(String model) {
        return this.getModel().equals(model) && this.isAvailable();
    }
    public String getType(){
        return "electrical";
    }
}
