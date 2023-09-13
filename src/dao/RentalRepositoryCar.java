package dao;

import Models.Car.Car;
import Models.Rental.ConcreteRental;
import Models.Rental.Rental;
import Models.RentalCar.ConcreteRentalCar;
import Models.RentalCar.RentalCar;
import Models.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalRepositoryCar implements RentalDAOCar {
    DatabaseConnection dbInstance = DatabaseConnection.getDatabaseInstance();
    Connection connection = dbInstance.getConnection();
    public static RentalRepositoryCar instance = null;


    public RentalRepositoryCar() throws SQLException {
    }
    public static RentalRepositoryCar getInstance() throws SQLException {
        if (instance!=null){
            return instance;
        }
        else{
            return new RentalRepositoryCar();
        }
    }

    @Override
    public void addNewRental(RentalCar rental) throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateString = formatter.format(date);
        String query = "INSERT INTO rentalcar (carId,username,rentDate,duration,returned,price) VALUES (?, ?, ?, ?,?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, rental.getSelectedCar().getCarId());
        statement.setString(2, rental.getUser().getUserName());
        statement.setString(3,formatter.format(rental.getRentDate()));
        statement.setDouble(4,rental.getDuration());
        statement.setBoolean(5,rental.isReturned());
        statement.setDouble(6,rental.calculatePrice());

        statement.executeUpdate();

    }

    @Override
    public void updateAvailability(int id) throws SQLException{
        String query = "UPDATE rentalcar SET returned = ? WHERE rental_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
//        PreparedStatement statement = connection.prepareStatement(query);
        statement.setBoolean(1,true);
        statement.setInt(2,id);

        statement.executeUpdate();
    }

    @Override
    public List<RentalCar> viewRentalHistory(String username) throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "SELECT * FROM rentalcar WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        List<RentalCar> rentals = new ArrayList<>();
        while (resultSet.next()) {
            int rental_id = resultSet.getInt("rental_id");
            String carId = resultSet.getString("carId");
            String date = resultSet.getString("rentDate");
            Date rentDate = null;
            try {
                rentDate = formatter.parse(date);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
            boolean returned = resultSet.getBoolean("returned");
            double duration = resultSet.getDouble("duration");
            double price =resultSet.getDouble("price");

            UserRepository userRepositoryInstance = UserRepository.getInstance();
            User user = userRepositoryInstance.searchUser(username);
            CarRepository carRepository = CarRepository.getInstance();
            Car car = carRepository.searchCarByID(carId);

            RentalCar rental = new ConcreteRentalCar(user,car,duration,rentDate,returned);
            rental.setRental_id(rental_id);

            rentals.add(rental);
        }
        return rentals;
    }
}
