public class Chef extends Employee{
    @Override
    public void view(){
        System.out.println("Name :"+name);
        System.out.println("Employee ID :"+employeeId);
        System.out.println("Salary :"+salary);
    }
    public void tasks(){
        System.out.println("Tasks : Cooking food and making drinks");
        System.out.println("----------------------------------------");
    }

}