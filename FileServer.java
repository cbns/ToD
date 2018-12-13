import java.net.*;
import java.io.*;

class FileServer {
	public static void main(String[] args){
		new FileServer().start();
	}
	
	public void start(){
		System.out.println("File Server");
		try{
		
			ServerSocket server = new ServerSocket(9999);
		//loop
			while(true){
				// connect to client
				Socket client = server.accept();
				System.out.println("connected...");
				// receive command
				String command = getCommand(client);
				System.out.println(command);
				// service client
				if (command.equals("DIR")){
					doDir(client);
				}
				if (command.startsWith("DOWNLOAD")){
					doDownload(client, command);
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private String getCommand(Socket client)
				throws IOException{
		BufferedReader in = new BufferedReader(
			new InputStreamReader(
				client.getInputStream()));
		return in.readLine();
	}
	
	private void doDir(Socket client)
				throws IOException{
		// get directory list
		File dir = new File(".");
		String[] items = dir.list();
		// send response
		PrintStream out = new PrintStream(
			client.getOutputStream());
		for(String s:items){
			out.println(s);
		}
	}
	
	private void doDownload(Socket client, String cmd)
				throws IOException{
		// open file for copy
		FileInputStream file = new FileInputStream(
			"MessageBoard.java");
		// send response
		OutputStream out = client.getOutputStream();
		int data = -1;
		while((data = file.read()) != -1){
			out.write(data);
		}
	}
}