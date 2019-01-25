public class ModelTest{

    public static void main(String[] args){

	Model model = new Model(20);

	model.add("sup");
	model.add("Dorg");

	model.add("poigels");

	model.remove("poigels");
	model.printTable();
	model.changeHashing("double", true);
        model.add("asdasdas asd sdjil");
	model.printTable();


    }
}
