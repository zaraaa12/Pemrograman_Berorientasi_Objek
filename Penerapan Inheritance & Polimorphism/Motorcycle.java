public class Motorcycle extends Vehicle{
    private boolean hasHelmet;

    public Motorcycle(String brand, int speed, boolean hasHelmet) {
        super(brand, speed);
        this.hasHelmet = hasHelmet;
    }

    // Overriding method
    @Override
    public void accelerate() {
        speed += 15;
        System.out.println(brand + " motorcycle accelerates smoothly. New speed: " + speed + " km/h");
    }
}
