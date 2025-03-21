public class App {
    public static void main(String[] args) {
        Vehicle v1 = new Vehicle("Generic", 50);
        v1.displayInfo();
        v1.accelerate();
        
        System.out.println();

        //Subclass Car
        Car car1 = new Car("Porsche", 100, 60);
        car1.displayInfo();
        car1.accelerate(); // Overriding
        car1.honk(); // Overloading

        System.out.println();

        //Subclass Motorcycle
        Motorcycle moto1 = new Motorcycle("Harley", 80, true);
        moto1.displayInfo();
        moto1.accelerate(); // Overriding
    }
}
