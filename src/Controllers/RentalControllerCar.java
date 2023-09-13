package Controllers;

import Models.Car.Car;
import Models.Rental.ConcreteRental;
import Models.Rental.Rental;
import Models.Rental.RentalWithHelmet;
import Models.Rental.RentalWithInsurance;
import Models.RentalCar.ConcreteRentalCar;
import Models.RentalCar.RentalCar;
import Models.RentalCar.RentalWithInsuranceCar;
import Models.User;
import dao.CarRepository;
import dao.RentalRepository;
import dao.RentalRepositoryCar;
import dao.UserRepository;

import java.sql.SQLException;
import java.util.*;

public class RentalControllerCar {

    CarRepository carRepositoryInstance;
    {
        try {
            carRepositoryInstance = CarRepository.getInstance();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    UserRepository userRepositoryInstance;
    {
        try {
            userRepositoryInstance = UserRepository.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    RentalRepositoryCar rentalRepository;
    {
        try {
            rentalRepository = RentalRepositoryCar.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String username;
    public RentalControllerCar(String username){
        this.username = username;
    }
    public String viewAllCar() {
        HashMap<String,List<String >>carByTypeModel = getAllCarByModelType();
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

                String avail;
                if(Boolean.toString(mechanicalCar.get(i).isAvailable()).equals("true")){
                avail = "Available";
            }else{ avail = "Unavailable";}
                carAttr = mechanicalCar.get(i).getModel()+"\tbuild:" +mechanicalCar.get(i).getBuild() + "  color:"
                        +mechanicalCar.get(i).getColor()+"  price:"
                        + mechanicalCar.get(i).getPrice()
                        + "Rs./hr   " + avail+ "  carId:"+mechanicalCar.get(i).getCarId();


                modelCar.add(carAttr);

            }
            carByTypeModel.put("mechanical",new ArrayList<>(modelCar));
            modelCar = new ArrayList<>();
            for (int i = 0; i < electricalCar.size(); i++) {

                String avail ="";
                if(Boolean.toString(electricalCar.get(i).isAvailable()).equals("true")){
                    avail = "Available";
                }else{ avail = "Unavailable";}
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

    public boolean checkAvailability(String carId){
        boolean flag = false;
        try{
            Car car = carRepositoryInstance.searchCarByID(carId);
            if (car != null) {
                if (car.isAvailable()){
                    flag = true;
                }
            }

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return flag;
    }

    public void rentCar(String username, String carId) {
        Scanner scanner = new Scanner(System.in);
        User user = null;
        Car car = null;
        try {
             user  = userRepositoryInstance.searchUser(username);
             car = carRepositoryInstance.searchCarByID(carId);
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

        System.out.println("Enter the duration in hrs: ");
        double duration = scanner.nextDouble();

        RentalCar rental = new ConcreteRentalCar(user,car,duration,new Date(),false);

        rental.getUser();
        System.out.println("Rented car \t\t Rental cost: "+rental.calculatePrice());
        System.out.println("Do you want insurance in this ride?[Y/N]");
        boolean insurance = scanner.next().toLowerCase().charAt(0) == 'y';
        if (insurance){
            rental = new RentalWithInsuranceCar(rental);
            System.out.println("Insurance added\t\t Rental cost: "+rental.calculatePrice());
        }

        System.out.println("Do payment of rupees: " + rental.calculatePrice());
        double payment = scanner.nextDouble();
        while (payment != rental.calculatePrice()){
            System.out.println("Rental unsuccessful");
            System.out.println("Enter valid amount");
            payment = scanner.nextDouble();
        }
        System.out.println("Rental successful.......");



        car.setAvailable(false);
        try {
            rentalRepository.addNewRental(rental);
            System.out.println("Note rental id for future references.\t\tRental id :");
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        try {

        carRepositoryInstance.updateCarAvailability(carId,false);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }



    }

    public void returnCar(int id,String carId) {
        try {
            rentalRepository.updateAvailability(id);
        } catch (SQLException e) {
            System.err.println("Error updating"+e.getMessage());
        }

        try {
            carRepositoryInstance.updateCarAvailability(carId,true);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void viewMyRentalHistory(String username) {
        try {

        List<RentalCar> rentals = rentalRepository.viewRentalHistory(username);
        for(RentalCar rental:rentals){
            System.out.println("Car ID: "+rental.getSelectedCar().getCarId() + "  rental id:"+rental.getRental_id() + " date:"+rental.getRentDate() + "  returned:"+rental.isReturned() + "  price:"+rental.getPrice());
//            System.out.println("User ID: "+rental.getUser().);
        }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
