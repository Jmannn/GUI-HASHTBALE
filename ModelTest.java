public class ModelTest{

    public static void main(String[] args){

	Model model = new Model(20);

	model.add("sup");
	model.add("Dorg");
	model.add("asdasdas asd sdjil");
	model.add("poigels");

	model.printTable();

	model.remove("poigels");
	model.printTable();


    }
}
