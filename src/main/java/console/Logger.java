package console;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

	private static final String FILE_LOG = "genetic.log";
	private static File fileLog = null;
	private static FileWriter fileWriter = null;
	
	public Logger(){
		try {
			fileLog = new File(FILE_LOG);
			System.out.println(fileLog.getAbsolutePath());
			fileWriter = new FileWriter(fileLog);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void log(String log){
		try {
			fileWriter.write(log);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(){
		if(fileWriter != null){
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
