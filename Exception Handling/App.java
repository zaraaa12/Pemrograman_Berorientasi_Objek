public class App{
    public static void main(String[] args) throws Exception{
    int [] Numbers = {1,2,3,4,5};
    try {
        System.out.println(Numbers[9]);
    } catch (Exception e) {
        System.out.println(e.getMessage());
    } finally{
        System.out.println("Next command executed");
    }
}
}