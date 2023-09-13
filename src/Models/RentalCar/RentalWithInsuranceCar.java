package Models.RentalCar;

public class RentalWithInsuranceCar extends RentalDecoratorCar {
    private double insuranceAmount = 100.00;

    public RentalWithInsuranceCar(RentalCar rental) {
        super(rental);
    }
    public double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }


    @Override
    public double calculatePrice() {
//        return rental.calculatePrice();
        double basePrice = rental.calculatePrice();
        return this.insuranceAmount + basePrice;
    }
}

