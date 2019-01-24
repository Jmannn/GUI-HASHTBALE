import java.util.Random;

public class Model {
    
    public Model(){}
    
    public int getIndex(String input, String hashFunction){
        Random rand = new Random();
        return rand.nextInt(19);
    }
}
