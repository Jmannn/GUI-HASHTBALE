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
    // each method returns [tableInd, chainInd]
    public void printTable(){
	ArrayList<String> chain;
	for (int i = 0; i < hashArray.length; i++){
	    System.err.println(i);
	    if (hashArray[i] != null){
		chain = hashArray[i];
		System.err.println("chain " +chain.size());
		for (int j = 0; j < chain.size(); j++){
		    System.err.print(chain.get(j) + " ");
		}
		System.err.println();
	    }
	}
    }
    public int[] add(String value){
	int[] dummy = new int[2];
	if (divisionHash){
	    return divisionHash(value);
	} else if (doubleHash){
	    return doubleHash(value);
	} else if (universalHash){
	    return universalHash(value);
	}
	/*else if (cuckooHash){
	return cuckooHash(value);
	} */
	else if (linearProbing){
	    return linearHash(value);
	} else if (quadraticProbing){
	    return quadHash(value);
	}
	return dummy;

    }
    /* Call this after, setting change or reset of table */
    public void rehash(){
	ArrayList<String>[] storageArray = this.hashArray.clone();
	for (int tableInd = 0; tableInd < this.hashArray.length; tableInd++){
	    if (this.hashArray[tableInd] != null){

		for (int chainInd = 0; chainInd < this.hashArray[tableInd].size(); chainInd++){	
		    if (this.hashArray[tableInd].isEmpty()){
			this.hashArray[tableInd] = null;
		    } else {
			add(this.hashArray[tableInd].get(chainInd));
		    }
		}
	    }
	}
    }
    /* Turns a string into a key to be hashed */
    private int toKey(String value){
	return Math.abs(value.hashCode());
    }
    /* Used for division, linear, quadratic */
    public int[] divisionHash(String value){
	//do hash
	boolean didHash = false;
	int i = 0;
	int pos = toKey(value) % hashArray.length;
	int[] rowCol = new int[2];
	rowCol[0] = -1;
	rowCol[1] = -1;
	
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
		rowCol[0] = pos;
		rowCol[1] = 0;
		return rowCol;
	    } else if (this.chaining == true){
		rowCol[0] = pos;
		rowCol[1] = this.hashArray[pos].size();
		chain(pos+i, value);
		didHash = true;
		return rowCol;
	    } else if (i==this.hashArray.length){
		System.out.println("Could not hash " + value);
		didHash = true;
		return rowCol;
	    }
	    i++;
	}
	 return rowCol;
    }
    /* Linear probing hash */
    public int[] linearHash(String value){
	//do hash
	boolean didHash = false;
	int i = 0;
	int pos = (toKey(value) +i)% hashArray.length;
	int[] rowCol = new int[2];
	rowCol[0] = -1;
	rowCol[1] = -1;
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
		rowCol[0] = pos;
		rowCol[1] = 0;

	    } else if (this.chaining == true){
		rowCol[0] = pos;
		rowCol[1] = this.hashArray[pos].size();
		chain(pos+i, value);
		didHash = true;
	    } else if (i==this.hashArray.length){
		System.out.println("Could not hash linear : " + value);
		didHash = true;

	    }
	    System.err.println(i);
	    i++;
	    pos = (toKey(value) +i)% hashArray.length;
	   
	}
	 return rowCol;
    }
    /* quadratic probing has */
    public int[] quadHash(String value){
	//do hash
	boolean didHash = false;
	int i = 0;
	int pos = (toKey(value) +i)% hashArray.length;
	int[] rowCol = new int[2];
	rowCol[0] = -1;
	rowCol[1] = -1;
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
		rowCol[0] = pos;
		rowCol[1] = 0;
		didHash = true;
	    } else if (this.chaining == true){
		rowCol[0] = pos;
		rowCol[1] = this.hashArray[pos].size();
		chain(pos+i, value);
		didHash = true;
	    } else if (i==this.hashArray.length){
		System.out.println("Could not hash");
		didHash = true;
	    }
	    i++;
	    pos = (int) (toKey(value) +Math.pow(i,2))% hashArray.length;
	   
	}
	 return rowCol;
    }
    public int[] doubleHash(String value){
	//do hash
	boolean didHash = false;
	int i = 0;
	int pos = (toKey(value) +i)% hashArray.length;
	int[] rowCol = new int[2];
	rowCol[0] = -1;
	rowCol[1] = -1;
	
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
		rowCol[0] = pos;
		rowCol[1] = 0;
		didHash = true;
	    } else if (this.chaining == true){
		rowCol[0] = pos;
		rowCol[1] = this.hashArray[pos].size();
		chain(pos+i, value);
		didHash = true;
	    } else if (i==this.hashArray.length){
		System.out.println("Could not hash");
		didHash = true;
	    }
	    i++;
	    pos = (toKey(value) +i*(1 + toKey(value)%(hashArray.length-1))) % hashArray.length;	 
				    

	}
	 return rowCol;
    }
    public int[] universalHash(String value){
	//do hash
	boolean didHash = false;
	int i = 0;
	int pos = (int) ((this.a * toKey(value) + this.b )% PRIME ) % hashArray.length;
	int[] rowCol = new int[2];
	rowCol[0] = -1;
	rowCol[1] = -1;
	
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
		rowCol[0] = pos;
		rowCol[1] = 0;
		didHash = true;
	    } else if (this.chaining == true){
		rowCol[0] = pos;
		rowCol[1] = this.hashArray[pos].size();
		chain(pos+i, value);
		didHash = true;
	    } else {
		System.out.println("Could not hash " + value);
		didHash = true;
	    }

	}
	 return rowCol;
    }
    public void cuckooHash(String value){
	// could do this recursively, move in value and then move the value to another position
	// re hash it until everything is unoccupied
	// I guess you could move the entire chain if needed

	// this will need a special method in controller
	// return multi d array and think of the new addresses where the things
	// are meant to be shuffled to
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
		    //fix this
		    didHash = true;
		}
	    }
	}
	 return;

    }
    public int[] remove(String value){
	int[] toRemove = new int[2];
	toRemove[0] = -1;
	toRemove[1] = -1;
	for (int tableInd = 0; tableInd < this.hashArray.length; tableInd++){
	    if (this.hashArray[tableInd] != null){

		for (int chainInd = this.hashArray[tableInd].size()-1; chainInd >= 0 ; chainInd--){
		    System.err.println(chainInd);
		    if (this.hashArray[tableInd].get(chainInd).equals(value)){
			String vale = this.hashArray[tableInd].get(chainInd);
			this.hashArray[tableInd].remove(chainInd);
			System.err.println("removed " + vale);
			toRemove[0] = tableInd;
			toRemove[1] = chainInd;
			if (this.hashArray[tableInd].isEmpty()){
			    this.hashArray[tableInd] = null;
			}
			
			break;
		    }
		}
	    }
	}
	return toRemove;
    }
		
    /* for changing unirversal hash a and b parameters*/
    public void universalHashParam(int a, int b){
	this.a = a;
	this.b = b;
	rehash();
    }
    /* Division hashing */	
    public void changeHashing(String hashType){
	divisionHash = false;
	linearProbing = false;
	quadraticProbing = false;
	doubleHash = false;
	universalHash = false;
	cuckooHash = false;


	if (hashType == "Division"){
	    divisionHash = true;
	} else if (hashType == "Double"){
	    doubleHash = true;
	} else if (hashType == "Universal"){
	    universalHash = true;
	} else if (hashType == "Cuckoo"){
	    cuckooHash = true;
	}else if (hashType == "Linear"){
	    linearProbing = true;
	} else if (hashType == "Quadratic"){
	    quadraticProbing = true;
	}

	System.err.println("changed model to " + hashType );

    }
    public int tableSize(){
	return this.hashArray.length;
    }
    public boolean isChaining(){
	return this.chaining;
    }
    public void toggleChaining(){
	if (this.chaining){
	    this.chaining = false;
	} else {
	    this.chaining = true;
	}
    }
    /* this is called if chaining is on 
     * @param ind index of table to chain to
     */
    public void chain(int ind, String value){
	if (this.hashArray[ind].size() < 4){
	this.hashArray[ind].add(value);
	} else {
	    System.err.println("chaining limit reached");
	}
    }




}
