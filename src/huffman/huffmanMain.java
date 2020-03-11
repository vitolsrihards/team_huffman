package huffman;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class huffmanMain {

	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {

		int menuOption = 0;
		boolean quit = false;
		String encodedString = "";

		try {
			System.out.println("Noradiet faila nosaukumu ar paplasinajumu.");
			String path = sc.nextLine();
			String failsBase64 = Loader.getFile(path);
			Map<Character, Integer> sortedMap = huffmanTree.mapTree(failsBase64);
			huffmanTree.createTree(sortedMap);

			do {

				System.out.print("Izvelne: \n\n");
				System.out.print("1. Saspiest failu\n");
				System.out.print("2. Izvadit zinojumu\n");
				System.out.print("3. Izveleties jaunu failu\n");
				System.out.print("0. Beigt darbu\n");

				do {
					while (!sc.hasNextInt()) {
						System.out.println("Ievadiet skaitli.");
						sc.next();
					}
					menuOption = sc.nextInt();
				} while (menuOption < 0);

				switch (menuOption) {
				case 1:
					encodedString = huffmanTree.encode(failsBase64);
					Loader.setFile(Loader.bitStringToByteArr(encodedString));
					break;
				case 2:
					if (encodedString != null && !encodedString.isEmpty()) {
						String decodedString = huffmanTree.decode(encodedString);
						decodedString = new String(Loader.decodeBase64(decodedString), StandardCharsets.UTF_8);
						Loader.setFile(decodedString.getBytes(StandardCharsets.UTF_8));
					} else {
						System.out.println("Nav encoded zinojuma.");
					}
					break;
				case 3:
					encodedString = "";
					System.out.println("Noradiet faila nosaukumu ar paplasinajumu.");
					path = sc.next();
					failsBase64 = Loader.getFile(path);
					sortedMap = huffmanTree.mapTree(failsBase64);
					huffmanTree.createTree(sortedMap);
					break;
				case 0:
					quit = true;
					break;
				default:
					System.out.println("Nepareiza izvelne.");
				}
			} while (!quit);
			sc.close();
			System.out.println("Paldies par programmas lietosanu!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
