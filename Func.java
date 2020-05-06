import java.util.Random;
public class Func {
   // private static int id_counter =0;

    public static int generate_id() {
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(90000);
        return n;
    }
    
}