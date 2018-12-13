import java.io.*;

class Data {
	private final String name;
	private final int score;
	
	public Data(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public void print(){
		System.out.println("Name: " + name + " " SCORE: " + score);
	}
	
}

Data app = new Data();

String fileName = "save.txt";
try(BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))){
	String line = null;
	while ((line = br.readLine()) != null){
		String[] parts = line.split(" ");
		String name = parts[0];
		int score = parts[1];
		Data data = app.new Data(name, score);
		line = br.readLine();
		data.print();
	}
}
	
catch(FileNotFoundException fnfe){
	System.out.println(fnfe.getMessage());
}
	
catch(IOException ioe){
	System.out.println(ioe.getMessage());
}