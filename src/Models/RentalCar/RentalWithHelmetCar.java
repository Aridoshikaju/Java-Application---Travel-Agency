package Models.RentalCar;

public class RentalWithHelmetCar extends RentalDecoratorCar{
    private double helmetPrice = 50.00;

    public double getHelmetPrice() {
        return helmetPrice;
    }

    public void setHelmetPrice(double helmetPrice) {
        this.helmetPrice = helmetPrice;
    }

    public RentalWithHelmetCar(RentalCar rental) {
        super(rental);
    }

    @Override
    public double calculatePrice(){
        return rental.calculatePrice() + this.helmetPrice;
    }

}
