package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Loader {
	public static String getFile (String failaNos) throws IOException {
		// Izveido jaunu faila objektu un ieejas stream
		File fails = new File("resources/" + failaNos);
		InputStream inputStraume = new FileInputStream(fails);

		// Izveido jaunu masivu, kas ir faila garuma
	    byte[] failaBaiti = new byte[(int)fails.length()];

	    // Ielasa failu byte masiva un aizver ieejas stream
	    int nobide = 0;
	    int nolasitais = 0;
	    while (nobide < failaBaiti.length && (nolasitais = inputStraume.read(failaBaiti, nobide, failaBaiti.length - nobide)) >= 0) {
	    	nobide += nolasitais;
	    }
	    inputStraume.close();
		return encodeBase64(failaBaiti);
	}

	private static String encodeBase64 (byte[] fails) throws IOException {
		// Parkode byte masivu uz Base64 String
	    byte [] kodetsFails = Base64.getEncoder().encode(fails);
	    return new String(kodetsFails, StandardCharsets.US_ASCII);
	}
}