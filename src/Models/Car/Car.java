package Models.Car;

public abstract class Car {
    private String carId;
    private String model;
    private String build;
    private String color;
    private boolean available;

    public Car(String carId, String model, String build, String color) {
        this.carId = carId;
        this.model = model;
        this.build = build;
        this.color = color;
        this.available = true;
    }
    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }

    public String getBuild() {
        return build;
    }

    public String getColor() {
        return color;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract boolean isModelAvailable(String model);
    public abstract double getPrice();
    public abstract void setPrice(double price);
    public abstract  String getType();
}
