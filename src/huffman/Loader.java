package huffman;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Loader {
	public static String getFile(String failaNos) throws IOException {
		// Izveido jaunu faila objektu un ieejas stream
		File fails = new File("resources/" + failaNos);
		InputStream inputStraume = new FileInputStream(fails);

		// Izveido jaunu masivu, kas ir faila garuma
		byte[] failaBaiti = new byte[(int) fails.length()];

		// Ielasa failu byte masiva un aizver ieejas stream
		int nobide = 0;
		int nolasitais = 0;
		while (nobide < failaBaiti.length
				&& (nolasitais = inputStraume.read(failaBaiti, nobide, failaBaiti.length - nobide)) >= 0) {
			nobide += nolasitais;
		}
		inputStraume.close();
		return encodeBase64(failaBaiti);
	}

	private static String encodeBase64(byte[] fails) throws IOException {
		// Parkode byte masivu uz Base64 String
		byte[] kodetsFails = Base64.getEncoder().encode(fails);
		return new String(kodetsFails, StandardCharsets.UTF_8);
	}
	
	public static byte[] decodeBase64(String fails) throws IOException {
		return Base64.getDecoder().decode(fails);
	}
	
	public static byte[] bitStringToByteArr(String bituVirkne) {
		//Izveido simbolu masivu
	    char[] bituSimboluMasivs = bituVirkne.toCharArray();
	    int bitShortage = (8 - (bituSimboluMasivs.length % 8));
	    char[] bitChars = new char[bituSimboluMasivs.length + bitShortage];
	    System.arraycopy(bituSimboluMasivs, 0, bitChars, 0, bituSimboluMasivs.length);

	    for (int i = 0; i < bitShortage; i++) {
	        bitChars[bituSimboluMasivs.length + i] = '0';
	    }

	    //to bytearray
	    byte[] byteArray = new byte[bitChars.length / 8];
	    for(int i = 0; i < bitChars.length; i++) {
	        if (bitChars[i] == '1'){
	            byteArray[byteArray.length - (i / 8) - 1] |= 1 << (i % 8);
	        }
	    }
	    
	    return byteArray;
	}
	
	public static void setFile(byte[] bituMasivs) throws IOException {
    	System.out.println("Noradiet faila nosaukumu ar paplasinajumu.");
    	String nosaukums = huffmanMain.sc.next();
    	
    	File jaunaisFails = new File("resources/" + nosaukums);
    	FileOutputStream jaunaisOutputStream = new FileOutputStream(jaunaisFails);
    	jaunaisOutputStream.write(bituMasivs);
    	jaunaisOutputStream.close();
    	System.out.println("Fails saspiests ar nosaukumu " + nosaukums);
	}
}