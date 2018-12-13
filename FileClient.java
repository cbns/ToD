import java.io.*;
import java.net.*;

class FileClient {
	public static void main(String[] args){
		new FileClient().start();
	}
	
	public void start(){
		System.out.println("File Client");
		// connect to server
		Socket server = null;
		try{
			server = new Socket("localhost", 9999);
			System.out.println("Connected...");
		} catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		// loop
		boolean finished = false;
		while(!finished){
			// read in user command
			int cmd = selectCommand();
			switch(cmd){
				case 1: // directory list
					getDirectoryList(server); break;
				case 3: // download file
					download(server, "FileServer.class");
				case 9: finished = true; break;
			}
			// send command
			// receive response
		}
	}
	
	private int selectCommand(){
		BufferedReader in =
			new BufferedReader(
				new InputStreamReader(
					System.in));
		System.out.println("Select a function:");
		System.out.println("1 - list contents");
		System.out.println("2 - upload file");
		System.out.println("3 - download file");
		System.out.println("4 - delete file");
		System.out.println("9 - Exit");
		System.out.println("Enter selection: ");
		int cmd = -1;
		try{
			String command = in.readLine();
			cmd = Integer.valueOf(command);
		}catch (NumberFormatException e){
			//e.printStackTrace();
		} catch(IOException e){
			//e.printStackTrace();
		}
		return cmd;
	}
	
	private void getDirectoryList(Socket server){
		// get input output streams
		try{
			BufferedReader in = new BufferedReader(
				new InputStreamReader(
					server.getInputStream()));
			PrintStream out = new PrintStream(
				server.getOutputStream());
			//PrintWriter
			// send DIR command
			out.println("DIR");
			// receive directory list
			String line = null;
			while ((line = in.readLine()) != null){
				System.out.println(line);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private void download(Socket server, String filename){
		// get input output streams
		try{
			BufferedReader in = new BufferedReader(
				new InputStreamReader(
					server.getInputStream()));
			PrintStream out = new PrintStream(
				server.getOutputStream());
			// PrintWriter
			// send DOWNLOAD command
			out.println("DOWNLOAD:"+filename);
			// create local file t ostore data
			FileOutputStream file =
				new FileOutputStream("copy.txt");
			// receive file data from server
			int data = -1;
			while ((data = 
				server.getInputStream().read()) != -1){
				// write data to local file
				file.write(data);
			}
			file.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}