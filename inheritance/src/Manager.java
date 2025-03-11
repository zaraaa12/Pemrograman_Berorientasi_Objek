public class Manager extends Employee {
    @Override //serve as a recreation of methods from superclass for sublasses
    
    public void view(){
        System.out.println("Employee ID :"+employeeId);
        System.out.println("Name : "+name);
        System.out.println("Salary :"+salary)
    }
    
    public void tasks(){
        System.out.println("Tasks : Perform management for the franchise");
        System.out.println("---------------------------------------------");
    }


}
