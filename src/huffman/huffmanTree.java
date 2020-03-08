package huffman;
import java.util.*;

public class huffmanTree
{   
	class Node {
	    public char charValue;
	    public int freqCount;
	    public Node left;
	    public Node right;
	}
	
   private Node root;

   public huffmanTree(Map<Character, Integer> sortedMap) {
	   
	  // Saraksta nodes izveide
      LinkedList<Node> treeNodes = new LinkedList<Node>();
 

      // No sortedMap key iegūst masīvu 
      // Ciklā iet cauri masīvam un tiek definēts jauns Node objekts
      // Tiek uzstādīti jaunā objekta atribūtu vērtības
      // objekts tiek pievienots nodes sarakstam
      for (char ch : sortedMap.keySet()){
         Node newNode = new Node();
         newNode.charValue = ch;
         newNode.freqCount = sortedMap.get(ch);
         treeNodes.add(newNode);
      }

      // Tiek saglabāta galvenā koka sakne ( pirmais elements nodes sarakstā)
      root = treeNodes.remove(); 
      // Cikls cauri visiem patreizējiem nodes elementiem
      while(treeNodes.size() >1) {
    	  //Iegūst mazāko virsotni
          Node smallest = treeNodes.remove();
         //Iegūst nākamo mazāko virsotni
          Node nextSmallest = treeNodes.remove();
          // Izveido jaunu objektu
          Node newNode = new Node();
          // Tiek izveidota virsotnes vērtība
          newNode.freqCount = smallest.freqCount + nextSmallest.freqCount;
          // Kreisā zara lapa
          newNode.left = smallest;
          // Labā zara lapa
          newNode.right = nextSmallest;
          // Pievieno atjaunoti sarakstam
          treeNodes.add(newNode);
    	}

   
   }





}
