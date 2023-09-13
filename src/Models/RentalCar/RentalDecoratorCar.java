package Models.RentalCar;


import Models.Car.Car;
import Models.User;

import java.util.Date;

public  class RentalDecoratorCar implements RentalCar{
    protected RentalCar rental;

    public RentalDecoratorCar(RentalCar rental){
        this.rental = rental;
    }

    @Override
    public int getRental_id() {
        return rental.getRental_id();
    }

    @Override
    public void setRental_id(int rental_id) {
        rental.setRental_id(rental_id);
    }

    @Override
    public double calculatePrice(){
        return rental.calculatePrice();
    }

    @Override
    public Car getSelectedCar() {
        return rental.getSelectedCar();
    }

    @Override
    public double getDuration() {
        return rental.getDuration();
    }

    @Override
    public double getMin_duration() {
        return rental.getMin_duration();
    }

    @Override
    public void setDuration(double duration) {
        rental.setDuration(duration);
    }

    @Override
    public Date getRentDate() {
        return rental.getRentDate();
    }

    @Override
    public boolean isReturned() {
        return rental.isReturned();
    }

    @Override
    public void setReturned(boolean returned) {
        rental.setReturned(returned);
    }

    @Override
    public double getPrice() {
        return  rental.getPrice();
    }

    @Override
    public void setPrice(double price) {
        rental.setPrice(price);
    }

    @Override
    public User getUser() {
        return rental.getUser();
    }
}

