package huffman;
import java.util.*;


public class huffman {

	public static void main(String[] args) throws Exception {

		String msg = "";

		Map<Character, Integer> sortedMap = huffmanTree.fakeBinaryTree(msg);
		huffmanTree huffObj = new huffmanTree(sortedMap);

		// encoding
		String encodedString = huffObj.encode(msg);
		System.out.println(encodedString);
		// decoding
		String decodedString = huffObj.decode(encodedString);
		System.out.println(decodedString);

//
//		Scanner sc = new Scanner(System.in);
//		int menuOption;
//		boolean quit = false;
//
//		try {
//
//			System.out.println("Noradiet faila nosaukumu.");
//
//			String path = sc.nextLine();
//
//			do {
//				System.out.print("Izvelne: \n\n");
//				System.out.print("1. Saspiest failu");
//				System.out.print("2. Dekompreset failu");
//				System.out.print("0. Beigt darbu");
//				menuOption = sc.nextInt();
//
//				switch (menuOption) {
//				case 1:
////					getFile(path);
//					break;
//				case 2:
////					getFile(path);
//					break;
//				case 0:
//					quit = true;
//					break;
//				default:
//					System.out.println("Nepareiza izvelne.");
//				}
//			} while (!quit);
//			sc.close();
//			System.out.println("Paldies par programmas lietosanu!");
//		} catch (Exception e) {
//			System.out.println("Notikusi kluda.");
//		}
	}
}