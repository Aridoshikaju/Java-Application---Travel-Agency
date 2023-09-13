package dao;

import Models.Car.Car;


import java.sql.SQLException;
import java.util.List;

public interface CarDAO {
    List<Car> getAllCars() throws SQLException;
    void addCar(Car car) throws SQLException;
    void deleteCar(String carId) throws SQLException;

//    List<User> searchUsers(String keyword) throws SQLException;

    List<Car> searchCarsByType(String type) throws SQLException;
    Car searchCarByID(String carId) throws SQLException;

    void updateCarPrice(String carId,double newPrice) throws SQLException;
    void updateCarAvailability(String carId,boolean returned) throws SQLException;
}
