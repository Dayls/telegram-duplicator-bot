package services;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatIdsLoader {
	private static String path = "src/main/resources/params.txt";
	
	public static String getSourceChatId() {
		try(Scanner scanner = new Scanner(Paths.get(path))) {
			while(scanner.hasNextLine()) {
				String nextLine = scanner.nextLine();
				if(nextLine.startsWith("#")) {
					continue;
				}
				
				String[] split = nextLine.split(" ");
				if(split[0].equals("CHAT_SOURCE_ID")) {
					try {
						System.out.println(split[2]);
						return split[2];
					} catch(ArrayIndexOutOfBoundsException e) {
						return new String();
					}
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return new String(); 
	}
	
	public static List<String> getDestinationChatIds() {
		List<String> result = new ArrayList<>();
		try(Scanner scanner = new Scanner(Paths.get(path))) {
			while(scanner.hasNextLine()) {
				String nextLine = scanner.nextLine();
				if(nextLine.startsWith("#") || nextLine.isBlank()) {
					continue;
				}
				
				String[] split = nextLine.split(" ");
				if(split[0].equals("CHAT_DESTINATIONS_IDS")) {
					for(int i = 0; i < split.length; i++) {
						if(i > 1) {
							System.out.println(result);
							result.add(split[i]);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
