package console;

import java.util.Scanner;

public class ConsoleScanner {
	
	private Scanner scanner = new Scanner(System.in);
	
	public ConsoleScanner() {
	}
	
	public Boolean readBoolean(String msg) {
		System.out.println(msg);
		String option = scanner.nextLine();
		option = option.toUpperCase();
		switch(option) {
			case "Y": return true;
			case "N": return false;
		}
		return null;		
	}
	
	public String readString(String msg) {
		System.out.println(msg);
		return scanner.nextLine();
	}
	
	public Integer readInt(String msg) {
		System.out.println(msg);
		return Integer.parseInt(scanner.nextLine());
	}
	
	public Double readDouble(String msg) {
		System.out.println(msg);
		return Double.parseDouble(scanner.nextLine());
	}

}
