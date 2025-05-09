public class Throw {
    static void checkAge(int age){
        if(age<=18){
            throw new ArithmeticException("Access Denied");
        } else{
            System.out.println("Access Granted");
        }
    }
}
