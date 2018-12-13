import java.util.ArrayList;
import java.io.*;

class MessageBoard{
	//Message[] messages;
	private ArrayList messages;
	
	public MessageBoard(){
		messages = new ArrayList();
	}
	
	public void add(Message msg){
		messages.add(msg);
	}
	
	public void list(){
		System.out.println();
		System.out.println("messages:");
		System.out.println("=============");
		for(int i=0; i<messages.size(); i++){
			System.out.println(messages.get(i));
//		    if (messages.get(i) instanceof 
//					PrivateMessage){ 			
//				PrivateMessage m = (PrivateMessage)messages.get(i);
//				System.out.println(
//					m.getMessage() + " from: " +
//					m.getOwner() + " -- " + 
//					m.getSecret());
//				System.out.println(m);	
//			}else if (messages.get(i) instanceof Message){				
//				Message m = (Message)messages.get(i);
//				System.out.println(
//					m.getMessage() + " from: " +
//					m.getOwner());
//				System.out.println(m);
//			} 
		}
		System.out.println();
		System.out.println();

	}

	public void loadFromFile() 
				throws FileNotFoundException,
					MessageBoardInitException{
		BufferedReader input = null;
			input = 
			new BufferedReader(
				new FileReader(
					"messages.dat"));
		boolean finished = false;
		String line = null;
		try{		
			while((line = input.readLine()) != null){
				String[] items = line.split(":");
				String owner = items[0];
				String message = items[1];
				Message msg = new Message(
						message, owner);
				messages.add(msg);
				throw new IOException("read error");
			}
//		}catch(FileNotFoundException e){
//			//e.printStackTrace();	
//			throw e;
		}catch(IOException e){
//			System.out.println("*********");
//			e.printStackTrace();
			throw new MessageBoardInitException(); 
		} 
	}
}