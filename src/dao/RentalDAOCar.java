package dao;

import Models.Rental.Rental;
import Models.RentalCar.RentalCar;

import java.sql.SQLException;
import java.util.List;

public interface RentalDAOCar {
    public void addNewRental(RentalCar rental) throws SQLException;
    public void updateAvailability(int id)  throws SQLException;
    public List<RentalCar> viewRentalHistory(String username) throws SQLException;
}
