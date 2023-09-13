package Models.Car;
public class ConcreteMechanicalCar extends MechanicalCar {
    private double price;
    public ConcreteMechanicalCar(String carID,String model, String build, String color) {
        super(carID,model, build, color);
    }

    public boolean isModelAvailable(String model) {
        return this.getModel().equals(model) && this.isAvailable();
    }
    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price){
        this.price = price;
    }
    public String getType(){
        return "mechanical";
    }
}