package Controllers;

import Models.Admin;
import Models.Bike.Bike;
import Models.Bike.BikeFactory;
import Models.Car.Car;
import Models.Car.CarFactory;
import Models.User;
import dao.BikeRepository;
import dao.CarRepository;
import dao.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminController {
    private String adminName;
    UserRepository userRepositoryInstance;
    {
        try {
            userRepositoryInstance = UserRepository.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    BikeRepository bikeRepositoryInstance;
    CarRepository carRepositoryInstance;
    {
        try {
            bikeRepositoryInstance = BikeRepository.getInstance();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    {
        try {
            carRepositoryInstance = CarRepository.getInstance();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public AdminController(String adminName){
        this.adminName = adminName;
    }


    public String viewAllUser() {
        try {

            List<User> users = userRepositoryInstance.getAllUsers();

            String USER_NAME = "\nUsername: ";
            String NAME = "\t Name:";
            String EMAIL = "\tEmail: ";
            String PHONE = "\tPhone: ";

            String allusers="";
            String userI;
            for (User user:users) {
                userI = "";
                userI = USER_NAME+ user.getUserName() + NAME + user.getName() + EMAIL+ user.getEmailId() +PHONE + user.getPhoneNumber();
                allusers+=userI;
            }
            return  allusers;
        } catch (SQLException e) {
            System.out.println("User details missing: " + e.getMessage());

        }
        return null;
    }


    public String  addUser(String username,String name,String email,String phone,String password){
//        boolean flag = false;
        String output = "";
        try {
            User user = new User(username,name,email,phone,password);
            userRepositoryInstance.addUser(user);
            output = "User added Successfully";
        } catch (SQLException e) {
            output = "SQLException: An error occurred while adding the user: " + e.getMessage();
        }


        return output;
    }


    public String deleteUser(String username){
//        boolean flag = false;
        String output = "";
        try {
            User user = userRepositoryInstance.searchUser(username);
            if (user!=null){
                userRepositoryInstance.deleteUser(username);
                output = "Deleted user successfully\n";
            }
            else{
                output = "User not found\n";
            }
        } catch (SQLException e) {
            output = "SQLException: An error occurred while deleting the user: " + e.getMessage();
        }


        return output;
    }

    public String viewAllBike() {
        HashMap<String,List<String >> bikeByTypeModel = getAllBikeByModelType();
        String allbikes = "";
        if (bikeByTypeModel!=null){
            for (String type : bikeByTypeModel.keySet()) {
                allbikes+="\n\n\t\t-------------Type: " + type + "-------------------";
                List<String >models = bikeByTypeModel.get(type);
                for (String model : models) {
                    allbikes += "\n\tModel: " +model;
                }
            }
        }
        return allbikes;
    }

    private HashMap<String ,List<String>> getAllBikeByModelType(){
        HashMap<String,List<String>>bikeByTypeModel = null;
        try {
            bikeByTypeModel = new HashMap<>();

//            List<Bike> bikes = bikeRepositoryInstance.searchBikesByType("mechanical");
//            List<Bike> electricBike = bikeRepositoryInstance.searchBikesByType("electrical");
//            List<String,List<String>> modelMechBike = new ArrayList<>();
            List<Bike> mechanicalBike = bikeRepositoryInstance.searchBikesByType("mechanical");
            List<Bike> electricalBike = bikeRepositoryInstance.searchBikesByType("electrical");
            List<String > modelBike = new ArrayList<>();
            String bikeAttr;
            for (int i = 0; i < mechanicalBike.size(); i++) {

                String avail = (mechanicalBike.get(i).isAvailable())?"Available":"Unavailabe";
                bikeAttr = mechanicalBike.get(i).getModel()+"\tbuild:" +mechanicalBike.get(i).getBuild() + "  color:"
                        +mechanicalBike.get(i).getColor()+"  price:"
                        + mechanicalBike.get(i).getPrice()
                        + "Rs./hr   " + avail+ "  bikeId:"+mechanicalBike.get(i).getBikeId();


                modelBike.add(bikeAttr);

            }
            bikeByTypeModel.put("mechanical",new ArrayList<>(modelBike));
            modelBike = new ArrayList<>();
            for (int i = 0; i < electricalBike.size(); i++) {

                String avail = (electricalBike.get(i).isAvailable())?"Available":"Unavailabe";
                bikeAttr = electricalBike.get(i).getModel()+ "\tbuild:" +electricalBike.get(i).getBuild() + "  color:"
                        +electricalBike.get(i).getColor()+"  price:"
                        + electricalBike.get(i).getPrice()
                        + "Rs./hr   " + avail+ "  bikeId:"+electricalBike.get(i).getBikeId();


                modelBike.add(bikeAttr);

            }
            bikeByTypeModel.put("electrical",new ArrayList<>(modelBike));





        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }
        return bikeByTypeModel;
    }


    public String addBike(String bikeId,String type,String model,String build,String color,String available,double price){
        String output = "";
        try {
            try {
                Bike bike = BikeFactory.createBike(type,bikeId,model,build,color);
                bike.setAvailable(true);
                bike.setPrice(price);
                bikeRepositoryInstance.addBike(bike);
                output = "Bike added successfully\n";
            }
            catch (IllegalArgumentException e){
                output = "IllegalArgumentException:" + e.getMessage()+ "\n";
            }




        } catch (SQLException e) {
            output =  "SQLException: An error occurred while adding the user: " + e.getMessage() + "\n";
        }
        return output;
    }

    public boolean deleteBike(String bikeId){
        boolean flag = false;
        try {
            Bike bike = bikeRepositoryInstance.searchBikeByID(bikeId);

            if(bike!=null){

            bikeRepositoryInstance.deleteBike(bikeId);
            flag = true;
            }
        }
        catch (SQLException e){
            System.out.println("An error occurred while deleting the bike: " + e.getMessage());
        }


        return flag;
    }

    public String changePriceCar(String bikeId,double newPrice) {
        String output = "";
        try {
            Bike bike = bikeRepositoryInstance.searchBikeByID(bikeId);

            if(bike!=null){

                bikeRepositoryInstance.updateBikePrice(bikeId,newPrice);
                output = "Updated bike price successfully......";
            }
            else{
                output = "Bike not found";
            }
        }
        catch (SQLException e){
            output = "SQL ERROR: An error occurred while deleting the bike: " + e.getMessage();
        }
        return output;

    }

    
    public String viewAllCar() {
        HashMap<String,List<String >> carByTypeModel = getAllCarByModelType();
        String allcars = "";
        if (carByTypeModel!=null){
            for (String type : carByTypeModel.keySet()) {
                allcars+="\n\n\t\t-------------Type: " + type + "-------------------";
                List<String >models = carByTypeModel.get(type);
                for (String model : models) {
                    allcars += "\n\tModel: " +model;
                }
            }
        }
        return allcars;
    }

    private HashMap<String ,List<String>> getAllCarByModelType(){
        HashMap<String,List<String>>carByTypeModel = null;
        try {
            carByTypeModel = new HashMap<>();

//            List<Car> cars = carRepositoryInstance.searchCarsByType("mechanical");
//            List<Car> electricCar = carRepositoryInstance.searchCarsByType("electrical");
//            List<String,List<String>> modelMechCar = new ArrayList<>();
            List<Car> mechanicalCar = carRepositoryInstance.searchCarsByType("mechanical");
            List<Car> electricalCar = carRepositoryInstance.searchCarsByType("electrical");
            List<String > modelCar = new ArrayList<>();
            String carAttr;
            for (int i = 0; i < mechanicalCar.size(); i++) {

                String avail = (mechanicalCar.get(i).isAvailable())?"Available":"Unavailabe";
                carAttr = mechanicalCar.get(i).getModel()+"\tbuild:" +mechanicalCar.get(i).getBuild() + "  color:"
                        +mechanicalCar.get(i).getColor()+"  price:"
                        + mechanicalCar.get(i).getPrice()
                        + "Rs./hr   " + avail+ "  carId:"+mechanicalCar.get(i).getCarId();


                modelCar.add(carAttr);

            }
            carByTypeModel.put("mechanical",new ArrayList<>(modelCar));
            modelCar = new ArrayList<>();
            for (int i = 0; i < electricalCar.size(); i++) {

                String avail = (electricalCar.get(i).isAvailable())?"Available":"Unavailabe";
                carAttr = electricalCar.get(i).getModel()+ "\tbuild:" +electricalCar.get(i).getBuild() + "  color:"
                        +electricalCar.get(i).getColor()+"  price:"
                        + electricalCar.get(i).getPrice()
                        + "Rs./hr   " + avail+ "  carId:"+electricalCar.get(i).getCarId();


                modelCar.add(carAttr);

            }
            carByTypeModel.put("electrical",new ArrayList<>(modelCar));





        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }
        return carByTypeModel;
    }


    public String addCar(String carId,String type,String model,String build,String color,String available,double price){
        String output = "";
        try {
            try {
                Car car = CarFactory.createCar(type,carId,model,build,color);
                car.setAvailable(true);
                car.setPrice(price);
                carRepositoryInstance.addCar(car);
                output = "Car added successfully\n";
            }
            catch (IllegalArgumentException e){
                output = "IllegalArgumentException:" + e.getMessage()+ "\n";
            }




        } catch (SQLException e) {
            output =  "SQLException: An error occurred while adding the user: " + e.getMessage() + "\n";
        }
        return output;
    }

    public boolean deleteCar(String carId){
        boolean flag = false;
        try {
            Car car = carRepositoryInstance.searchCarByID(carId);

            if(car!=null){

            carRepositoryInstance.deleteCar(carId);
            flag = true;
            }
        }
        catch (SQLException e){
            System.out.println("An error occurred while deleting the car: " + e.getMessage());
        }


        return flag;
    }

    public String changePrice(String carId,double newPrice) {
        String output = "";
        try {
            Car car = carRepositoryInstance.searchCarByID(carId);

            if(car!=null){

                carRepositoryInstance.updateCarPrice(carId,newPrice);
                output = "Updated car price successfully......";
            }
            else{
                output = "Car not found";
            }
        }
        catch (SQLException e){
            output = "SQL ERROR: An error occurred while deleting the car: " + e.getMessage();
        }
        return output;

    }
}
