public class Car extends Vehicle{
    private int fuelLevel;

    public Car(String brand, int speed, int fuelLevel) {
        super(brand, speed);
        this.fuelLevel = fuelLevel;
    }

    // Overriding method

    @Override
    public void accelerate() {
        speed += 50;
        System.out.println(brand + " car accelerates quickly. New speed: " + speed + " km/h");
    }

    // Overloading method

    public void honk() {
        System.out.println(brand + " car honks: Beep Beep!");
    }

    public void honk(String sound) {
        System.out.println(brand + " car honks: " + sound);
    }
}
