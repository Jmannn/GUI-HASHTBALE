import java.util.ArrayList;
/* The model for a GUI representation of hashtable.
 * @author Johnny Mann & Anaru Hudson
 */
public class Model{
    /* Max size of HashTable */
    public static final MAX_SIZE = 20;
    /* Stores hash table entries in an array of arraylists
     * to allow for chaining via arraylist
     */
    public static ArrayList<String>[] hashArray;

    public Model(int size){
    };

    public void resize(int size){
	//create new table
	//rehash it

    }
    /* Turns a string into a key to be hashed */
    private int toKey(String value){
	return value.hasCode();
    }
    /* Calculates hash from calc'd key and stores element */
    public void addElement(String value){
    }
    public void changeKey(int){
    }

}
