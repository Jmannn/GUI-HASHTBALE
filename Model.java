import java.util.ArrayList;
import java.lang.Math;
/* The model for a GUI representation of hashtable.
 * @author Johnny Mann & Anaru Hudson
 */
public class Model{
    /* Max size of HashTable */
    public static final int MAX_SIZE = 20;
    /* Hashing prime big enough to handle hashcode */
    public static final long PRIME = 1000000007;  
    /* Stores hash table entries in an array of arraylists
     * to allow for chaining via arraylist
     */
    public static ArrayList<String>[] hashArray;
    
    /* Hashing types */
    public static boolean divisionHash = false;//
    public static boolean doubleHash = false;//
    public static boolean universalHash = false;//
    public static boolean cuckooHash = false;
    public static boolean linearProbing = false;//
    public static boolean quadraticProbing = false;//
    /* Optional Hashing */
    public static boolean chaining = false;
    /* Universal has Param */
    public static int a = 3;
    public static int b = 4;
    //return could not hash if no collision resolution is on
 
    public Model(int size){
	this.hashArray = new ArrayList[size];
	this.divisionHash = true;
    };
    public void printTable(){
	ArrayList<String> chain;
	for (int i = 0; i < hashArray.length; i++){
	    System.err.println(i);
	    if (hashArray[i] != null){
		chain = hashArray[i];
		for (int j = 0; j < chain.size(); j++){
		    System.err.print(chain.get(j) + " ");
		}
		System.err.println();
	    }
	}
    }
    public void add(String value){
	if (divisionHash){
	    divisionHash(value);
	} else if (doubleHash){
	    doubleHash(value);
	} else if (universalHash){
	    universalHash(value);
	} else if (cuckooHash){
	    cuckooHash(value);
	} else if (linearProbing){
	    linearHash(value);
	} else if (quadraticProbing){
	    quadHash(value);
	}
	
    }
    public void resize(int size){
	//create new table
	//rehash it

    }
    /* Call this after, setting change or reset of table */
    public void rehash(){
    }
    /* Turns a string into a key to be hashed */
    private int toKey(String value){
	return Math.abs(value.hashCode());
    }
    /* Used for division, linear, quadratic */
    public void divisionHash(String value){
	//do hash
	boolean didHash = false;
	int i = 0;
	int pos = toKey(value) % hashArray.length;
	
	while (didHash == false){
	    //if a space is found
	    //add
	    //if colision && chain
	    //call chain method
	    //if linear probe, increment i
	    //if quad probe, increment i and try hash by i square

            //divhash
	    //System.err.println("hasharratlen " +this.hashArray.length);
	    //System.err.println("pos" + pos);
	    if (this.hashArray[pos] == null){
		this.hashArray[pos] = new ArrayList<String>();
		this.hashArray[pos].add(value);
		didHash = true;
	    } else if (this.chaining == true){
		chain(pos+i, value);
		didHash = true;
	    } else if (i==this.hashArray.length){
		System.out.println("Could not hash " + value);
		didHash = true;
	    }
	    i++;
	}
    }
    /* Linear probing hash */
    public void linearHash(String value){
	//do hash
	boolean didHash = false;
	int i = 0;
	int pos = (toKey(value) +i)% hashArray.length;
	
	while (didHash == false){
	    //if a space is found
	    //add
	    //if colision && chain
	    //call chain method
	    //if linear probe, increment i
	    //if quad probe, increment i and try hash by i square

            //divhash
	    if (this.hashArray[pos] == null){
		this.hashArray[pos] = new ArrayList<String>();
		this.hashArray[pos].add(value);
		didHash = true;
	    } else if (this.chaining == true){
		chain(pos+i, value);
		didHash = true;
	    } else if (i==this.hashArray.length){
		System.out.println("Could not hash");
		didHash = true;
	    }
	    i++;
	    pos = (toKey(value) +i)% hashArray.length;
	}
    }
    /* quadratic probing has */
    public void quadHash(String value){
	//do hash
	boolean didHash = false;
	int i = 0;
	int pos = (toKey(value) +i)% hashArray.length;
	
	while (didHash == false){
	    //if a space is found
	    //add
	    //if colision && chain
	    //call chain method
	    //if linear probe, increment i
	    //if quad probe, increment i and try hash by i square

            //divhash
	    if (this.hashArray[pos] == null){
		this.hashArray[pos] = new ArrayList<String>();
		this.hashArray[pos].add(value);
		didHash = true;
	    } else if (this.chaining == true){
		chain(pos+i, value);
		didHash = true;
	    } else if (i==this.hashArray.length){
		System.out.println("Could not hash");
		didHash = true;
	    }
	    i++;
	    pos = (int) (toKey(value) +Math.pow(i,2))% hashArray.length;
	}
    }
    public void doubleHash(String value){
	//do hash
	boolean didHash = false;
	int i = 0;
	int pos = (toKey(value) +i)% hashArray.length;
	
	while (didHash == false){
	    //if a space is found
	    //add
	    //if colision && chain
	    //call chain method
	    //if linear probe, increment i
	    //if quad probe, increment i and try hash by i square

            //divhash
	    if (this.hashArray[pos] == null){
		this.hashArray[pos] = new ArrayList<String>();
		this.hashArray[pos].add(value);
		didHash = true;
	    } else if (this.chaining == true){
		chain(pos+i, value);
		didHash = true;
	    } else if (i==this.hashArray.length){
		System.out.println("Could not hash");
		didHash = true;
	    }
	    i++;
	    pos = (toKey(value) +i*(1 + toKey(value)%(hashArray.length-1))) % hashArray.length;
				    

	}
    }
    public void universalHash(String value){
	//do hash
	boolean didHash = false;
	int i = 0;
	int pos = (int) ((this.a * toKey(value) + this.b )% PRIME ) % hashArray.length;
	
	while (didHash == false){
	    //if a space is found
	    //add
	    //if colision && chain
	    //call chain method
	    //if linear probe, increment i
	    //if quad probe, increment i and try hash by i square

            //divhash
	    if (this.hashArray[pos] == null){
		this.hashArray[pos] = new ArrayList<String>();
		this.hashArray[pos].add(value);
		didHash = true;
	    } else if (this.chaining == true){
		chain(pos+i, value);
		didHash = true;
	    } else {
		System.out.println("Could not hash");
		didHash = true;
	    }

	}
    }
    public void cuckooHash(String value){
	// could do this recursively, move in value and then move the value to another position
	// re hash it until everything is unoccupied
	// I guess you could move the entire chain if needed
	boolean didHash = false;
	String valueHolder;
	int i = 0;
	int pos = toKey(value) % hashArray.length;
	int cuckooPos = 1 + toKey(value) % (hashArray.length-1);
	while (didHash == false){
	    if (hashArray[pos] == null){
		this.hashArray[pos] = new ArrayList<String>();
		this.hashArray[pos].add(value);
		didHash = true;	
	    } else {
		valueHolder = this.hashArray[pos].get(0);
		this.hashArray[pos].remove(0);
		this.hashArray[pos].add(value);
		if (hashArray[cuckooPos] == null){
		    this.hashArray[cuckooPos] = new ArrayList<String>();
		    this.hashArray[cuckooPos].add(valueHolder);
		    didHash = true;
		} else {
		    cuckooHash(valueHolder);
		    didHash = true;
		}
	    }
	}

    }
    public void remove(String value){
	for (int tableInd = 0; tableInd < this.hashArray.length; tableInd++){
	    if (this.hashArray[tableInd] != null){

		for (int chainInd = 0; chainInd < this.hashArray[tableInd].size(); chainInd++){
		    if (this.hashArray[tableInd].get(chainInd).equals(value)){
			String vale = this.hashArray[tableInd].get(chainInd);
			this.hashArray[tableInd].remove(chainInd);
			System.err.println("removed " + vale);
			break;
		    }
		    if (this.hashArray[tableInd].isEmpty()){
			this.hashArray[tableInd] = null;
		    }
		}
	    }
	}
    }
		
    /* for changing unirversal hash a and b parameters*/
    public void universalHashParam(int a, int b){
	this.a = a;
	this.b = b;
	rehash();
    }
    /* Division hashing */	
    public void changeHashing(String hashType, boolean chaining){
	divisionHash = false;
	linearProbing = false;
	quadraticProbing = false;
	doubleHash = false;
	universalHash = false;
	cuckooHash = false;

	chaining = false;
	if (hashType == "division"){
	    divisionHash = true;
	} else if (hashType == "double"){
	    doubleHash = true;
	} else if (hashType == "universal"){
	    universalHash = true;
	} else if (hashType == "cuckoo"){
	    cuckooHash = true;
	}else if (hashType == "linear"){
	    linearProbing = true;
	} else if (hashType == "quadratic"){
	    quadraticProbing = true;
	}
	if (chaining == true){
	    chaining = true;
	}

    }
    public int tableSize(){
	return this.hashArray.length;
    }
    /* this is called if chaining is on 
     * @param ind index of table to chain to
     */
    public void chain(int ind, String value){
	this.hashArray[ind].add("value");
    }




}
