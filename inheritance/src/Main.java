public class Main {
    public static void main(String[] args) {

        Employee employee = new Employee();
        Manager manager = new Manager();
        Cashier cashier = new Cashier();
        Chef chef = new Chef();
        Waiters waiters = new Waiters();

        //input nilai variabel with object
        Manager.name = "Bilqis Zahra";
        Manager.employeeId = 20230040040;
        Manager.salary = "50 juta";
        Cashier.name = "Vallery";
        Cashier.employeeId = 001
        Cashier.salary = "3 juta";
        Chef.name = "Chef Juna";
        Chef.employeeId = 002
        Chef.salary = "7 juta";
        Waiters.name = "Ody";
        Waiters.employeeId = 003
        Waiters.salary = "3 juta";

        //calls a function in the superclass
        Employee.view();

        Manager.view();
        Manager.tasks();
        Cashier.view();
        Cashier.tasks();
        Chef.view();
        Chef.tasks();
        Waiters.view();
        Waiters.tasks();
    }
}
