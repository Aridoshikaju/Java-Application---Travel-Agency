package Views;

import Controllers.Controller;
import Controllers.RentalController;
import Controllers.RentalControllerCar;
import Controllers.UserController;
import Models.User;

import java.util.Objects;
import java.util.Scanner;

public class RentalViewCar {
    private Controller controller = new Controller();
    private String username;

    public void setUsername(String username) {

        this.username = username;
    }

    public void RentalView(String username){
        this.username = username;
    }
    public void rentalMenu(){
            controller.setRentalControllerCar(new RentalControllerCar(this.username));
            Scanner scanner = new Scanner(System.in);

            int choice;
            do{
                System.out.println("Welcome!\n");
                System.out.println("1. Rent a car");
                System.out.println("2. Return a rented car");
                System.out.println("3. View Rental history");
                System.out.println("0. Exit\n");

                System.out.print("Enter your choice: ");
                choice=scanner.nextInt();

                switch(choice){
                    case 1:
                        rentCar();
                        break;
                    case 2:
                        returnCar();
                        break;
                    case 3:
                        viewRentalHistory();
                        break;

                    case -1:
                        System.out.println("Redirecting to login page.....\n");
                        break;
                    case 0:
                        System.out.println("Thank you");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.\n");
                        break;
                }
            }while(choice!=-1);


    }
    public void rentCar(){
        Scanner scanner = new Scanner(System.in);

        System.out.println(controller.getRentalControllerCar().viewAllCar());
        System.out.println("Enter selected car id: ");
        String carId = scanner.nextLine();
        boolean avail = controller.getRentalControllerCar().checkAvailability(carId);
        if (!avail) {
            System.out.println("Car not available");
            System.out.println("Notify options");
        }
        else{

            controller.getRentalControllerCar().rentCar(this.username,carId);

        }
    }

    public void returnCar(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter car id: ");
        String carId = scanner.nextLine();
        System.out.println("Enter rental id");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.getRentalControllerCar().returnCar(id,carId);
    }

    public void viewRentalHistory(){
        controller.getRentalControllerCar().viewMyRentalHistory(this.username);
    }
}
