package dao;

import Models.Car.Car;
import Models.Car.CarFactory;
import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepository implements CarDAO{

    DatabaseConnection dbInstance = DatabaseConnection.getDatabaseInstance();
    Connection connection = dbInstance.getConnection();
    public static CarRepository instance = null;

    public CarRepository() throws SQLException {
    }

    public static CarRepository getInstance() throws SQLException {
        if (instance!=null){
            return instance;
        }
        else{
            return new CarRepository();
        }
    }


    @Override
    public List<Car> getAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM car";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String carId = resultSet.getString("carId");
            String model = resultSet.getString("model");
            String build = resultSet.getString("build");
            String color = resultSet.getString("color");
            boolean available = Boolean.parseBoolean(resultSet.getString("available"));
            String type = resultSet.getString("type");
            double price =resultSet.getDouble("price");
            Car car = CarFactory.createCar(type,carId,model,build,color);
            car.setAvailable(available);
            car.setPrice(price);
            cars.add(car);
        }
        return cars;
    }

    @Override
    public void addCar(Car car) throws SQLException {
        String query = "INSERT INTO car(carId,model,build,color,available,type,price) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, car.getCarId());
        statement.setString(2, car.getModel());
        statement.setString(3, car.getBuild());
        statement.setString(4, car.getColor());
        statement.setString(5, String.valueOf(car.isAvailable()));
        statement.setString(6, car.getType());
        statement.setDouble(7, car.getPrice());
        statement.executeUpdate();

    }

    @Override
    public void deleteCar(String carId) throws SQLException {
        String query = "DELETE  FROM car WHERE carId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, carId);
        statement.executeUpdate();
    }

    @Override
    public List<Car> searchCarsByType(String type) throws SQLException {
        String query = "SELECT * FROM car WHERE type = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, type);
        ResultSet resultSet = statement.executeQuery();
        List<Car> cars = new ArrayList<>();
        while (resultSet.next()) {
            String carId = resultSet.getString("carId");
            String model = resultSet.getString("model");
            String build = resultSet.getString("build");
            String color = resultSet.getString("color");
            boolean available = Boolean.parseBoolean(resultSet.getString("available"));
            type = resultSet.getString("type");
            double price =resultSet.getDouble("price");
            Car car = CarFactory.createCar(type,carId,model,build,color);
            car.setAvailable(available);
            car.setPrice(price);
            cars.add(car);
        }
        return cars;
    }

    @Override
    public Car searchCarByID(String carId) throws SQLException {
        String query = "SELECT * FROM car WHERE carId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, carId);
        ResultSet resultSet = statement.executeQuery();
        Car car= null;
        while (resultSet.next()) {
            carId = resultSet.getString("carId");
            String model = resultSet.getString("model");
            String build = resultSet.getString("build");
            String color = resultSet.getString("color");
            boolean available = Boolean.parseBoolean(resultSet.getString("available"));
            String type = resultSet.getString("type");
            double price =resultSet.getDouble("price");
            car = CarFactory.createCar(type,carId,model,build,color);
            car.setAvailable(available);
            car.setPrice(price);

        }

        return car;
    }

    @Override
    public void updateCarPrice(String carId, double newPrice) throws SQLException {
        String query = "UPDATE car SET price = ? WHERE carId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
//        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDouble(1, newPrice);
        statement.setString(2, carId);
        statement.executeUpdate();
    }

    @Override
    public void updateCarAvailability(String carId, boolean returned) throws SQLException {
        String query = "UPDATE car SET available = ? WHERE carId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
//        PreparedStatement statement = connection.prepareStatement(query);
        String avail = (returned)?"true":"false";
        statement.setString(1, avail);
        statement.setString(2, carId);
        statement.executeUpdate();
    }
}
