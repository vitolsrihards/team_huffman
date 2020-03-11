package huffman;
import java.util.*;

public class huffmanTree {

	private static Node root;

	public static Map<Character, Integer> mapTree(String code64) {

		Map<Character, Integer> binaryTree = new HashMap<Character, Integer>();

		for (int i = 0; i < code64.length(); i++) {
			char tempChar = code64.charAt(i);
			int tempValue = 1;
			if (binaryTree.containsKey(tempChar)) {
				tempValue = binaryTree.get(tempChar) + 1;
			}
			binaryTree.put(tempChar, new Integer(tempValue));
		}

		List<Map.Entry<Character, Integer>> list = new LinkedList<Map.Entry<Character, Integer>>(binaryTree.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
			public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Collections.reverse(list);

		HashMap<Character, Integer> sortedMap = new LinkedHashMap<Character, Integer>();

		for (Map.Entry<Character, Integer> element : list) {
			sortedMap.put(element.getKey(), element.getValue());
		}
		return sortedMap;
	}

	private static Map<Character, String> getEncodingMap() {
		// Izveido jaunu HashMap izmantojot Map Interface
		Map<Character, String> map = new HashMap<Character, String>();
		// parbauda vai ir izvelets sakuma node no kura veikt mekleshanu
		// Ja ir tad sak parmeklesanu.
		if (root != null) {
			root.fillEncodingMap(map, "");
		}
		return map;
	}

	private static String getMapKeyEncode(char charToFind, Map<Character, String> map) {
		for (Map.Entry<Character, String> entry : map.entrySet()) {
			if (entry.getKey() == charToFind) {
				return entry.getValue();
			}
		}
		return null;
	}

	public static String encode(String msg) {
		// iegust map ar binary values
		Map<Character, String> encodedMap = getEncodingMap();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < msg.length(); i++) {
			char c = msg.charAt(i);
			// iegust noteikta simbola binaro vertibu
			String binaryValue = getMapKeyEncode(c, encodedMap);
			if (binaryValue != null && !binaryValue.isEmpty()) {
				// pievieno vertibu virknei
				sb.append(binaryValue);
			} else {
				throw new RuntimeException("Something went wrong.");
			}
		}
		String encodedString = sb.toString();
		return encodedString;
	}

	public static String decode(String msg) {
		String result = "";
		Node n = root;
		for (int i = 0; i < msg.length(); i++) {
			char ch = msg.charAt(i);
			// 0 = pa kreisi koka
			if (ch == '0') {
				n = n.left;
				// 1 = pa labi koka
			} else {
				n = n.right;
			}
			// lapa
			if (n.left == null) {
				result = result + n.charValue;
				n = root;
			}
		}
		return result;
	}

	public static void createTree(Map<Character, Integer> sortedMap) {

		// Saraksta nodes izveide
		LinkedList<Node> treeNodes = new LinkedList<Node>();

		// No sortedMap key iegust masivu
		// Cikla iet cauri masivam un tiek definets jauns Node objekts
		// Tiek uzstadita jauna objekta atributa vertibas
		// objekts tiek pievienots nodes sarakstam
		for (char ch : sortedMap.keySet()) {
			Node newNode = new Node();
			newNode.charValue = ch;
			newNode.freqCount = sortedMap.get(ch);
			treeNodes.add(newNode);
		}

		// Cikls cauri visiem patreizajiem nodes elementiem
		while (treeNodes.size() > 1) {
			// iegust mazako virsotni
			Node smallest = treeNodes.remove();
			// iegust nakamo mazako virsotni
			Node nextSmallest = treeNodes.remove();
			Node newNode = new Node();
			// tiek izveidota virsotnes vertiba
			newNode.freqCount = smallest.freqCount + nextSmallest.freqCount;
			newNode.left = smallest;
			newNode.right = nextSmallest;
			// pievienoti atjaunotam sarakstam
			treeNodes.add(newNode);
		}
		// tiek saglabata koka sakne (pirmais elements nodes saraksta)
		try {
			root = treeNodes.remove();
		} catch (NoSuchElementException e) {
			System.out.println("Something went wrong.");
		}

	}

	static class Node {
		public char charValue;
		public int freqCount;
		public Node left;
		public Node right;

		public void fillEncodingMap(Map<Character, String> map, String prefix) {
			if (left == null) {
				// Ja aiziets maksimali pa kreisi koka tad pievieno simbolu ar ta bitu virkni
				map.put(charValue, prefix);
			} else {
				// dodas dzilak koka pa kreisi
				left.fillEncodingMap(map, prefix + "0");
				// kad vairs nav iespejams koka doties pa kreisi dodas vienu limeni uz augsu un
				// iegust labo zaru
				right.fillEncodingMap(map, prefix + "1");
				// kad labaja zara noiets maksimali pa kreisi dodas iteracija beidzas un dodas
				// uz agrako limeni(iteraciju)
			}
		}
	}

}