package Controllers;

import Views.AdminView;
import Views.LoginView;
import Views.RentalView;
import Views.RentalViewCar;
import Views.UserView;

public class Controller {
    UserController userController;
    LoginController loginController;

    AdminController adminController;
    RentalController rentalController;
    RentalControllerCar rentalControllerCar;

    public RentalController getRentalController() {
        return rentalController;
    }

    public void setRentalController(RentalController rentalController) {
        this.rentalController = rentalController;
    }


    public RentalControllerCar getRentalControllerCar() {
        return rentalControllerCar;
    }

    public void setRentalControllerCar(RentalControllerCar rentalControllerCar) {
        this.rentalControllerCar = rentalControllerCar;
    }

    public AdminController getAdminController() {
        return adminController;
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }



    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public UserController getUserController() {
        return userController;
    }

    public LoginController getLoginController() {
        return loginController;
    }


    public void start(){
        LoginView loginView = new LoginView();
        this.setLoginController(new LoginController(loginView));
        loginView.setController(this);
        loginView.menuLoginPage();
    }

    public void userPage(String username){
        this.setUserController(new UserController(username));
        UserView userView = new UserView(username);
        userView.userMenu();
    }

    public void adminPage(String adminName){
        this.setAdminController(new AdminController(adminName));
        AdminView adminView = new AdminView();
        adminView.adminMenu();
    }

    public void rentalPage(String username){
        this.setRentalController(new RentalController(username));
        RentalView rentalView = new RentalView();
        rentalView.setUsername(username);
        rentalView.rentalMenu();
    }

    public void rentalPageCar(String username){
        this.setRentalControllerCar(new RentalControllerCar(username));
        RentalViewCar rentalView = new RentalViewCar();
        rentalView.setUsername(username);
        rentalView.rentalMenu();
    }
}
