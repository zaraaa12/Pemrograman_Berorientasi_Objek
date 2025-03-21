class Vehicle{
    protected  String brand;
    protected int speed;

    public Vehicle(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Speed: " + speed + " km/h");
    }

    public void accelerate() {
        speed += 10;
        System.out.println(brand + " accelerates. New speed: " + speed + " km/h");
    }
}