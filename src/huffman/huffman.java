package huffman;
import java.util.*;


public class huffman {


	
	public static Map<Character,Integer> fakeBinaryTree(String code64) {
	
	Map< Character,Integer> binaryTree = new HashMap<Character,Integer>(); 
	
	
	System.out.print(code64 + "\n");
	
	for(int i =0 ; i < code64.length(); i++) {
		char tempChar = code64.charAt(i);
		int tempValue = 1;
		if(binaryTree.containsKey(tempChar)) {
			 tempValue = binaryTree.get(tempChar) +1;
		}
		binaryTree.put(tempChar, new Integer(tempValue)); 
		
	}

    List<Map.Entry<Character, Integer> > list = new LinkedList<Map.Entry<Character, Integer> >(binaryTree.entrySet()); 

  
    Collections.sort(list, new Comparator<Map.Entry<Character, Integer> >() { 
        public int compare(Map.Entry<Character, Integer> o1,  
                           Map.Entry<Character, Integer> o2) 
        { 
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

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 Map<Character,Integer> hm =  fakeBinaryTree("QW55kcmmVqIGxveA==");
		 huffmanTree huffpuff=new huffmanTree(hm);
	   
	        
		// Returns Set view      
					Set< Map.Entry< Character,Integer> > st = hm.entrySet();    
					
					for (Map.Entry< Character,Integer> me:st) 
					{ 
					    System.out.print(me.getKey()+":"); 
					    System.out.println(me.getValue()); 
					} 
			
			
		
	
	}

}