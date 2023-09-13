package Models.RentalCar;

import Models.Car.Car;
import Models.User;

import java.util.Date;

public interface RentalCar {

    public int getRental_id() ;

    public void setRental_id(int rental_id) ;
    public double calculatePrice();
    public Car getSelectedCar();

    public double getDuration();

    public double getMin_duration();

    public void setDuration(double duration);

    public Date getRentDate();


    public boolean isReturned();

    public void setReturned(boolean returned);
    public double getPrice();

    public void setPrice(double price);
    public User getUser();

}
