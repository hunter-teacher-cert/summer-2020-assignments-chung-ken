import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException; 

//Proof of Work Class
public class PoW {
	
	public static void main(String[] args) {
		String input = "I Love Dumplings!";
		String nonce = "";
		System.out.println( calculateHash(input + nonce) );
		long startingI = 0;
		for (int difficulty = 1; difficulty < 18; difficulty++) {
			String padding = "";
			for (int z = 0; z < difficulty; z++) {
				padding += "0";
			}
			long startTime = System.nanoTime();
			for (long i = startingI; i < Long.MAX_VALUE; i++) {
				nonce = "" + i;
				String hash = calculateHash(input + nonce);
				if (hash.substring(0,difficulty).equals(padding)) {
					System.out.println("\n" + difficulty + " zeros Solved!");
					System.out.println("nonce = " + nonce);
					System.out.println("hash = " + hash);
					startingI = i;
					break;
				}
			}
			long totalTime = System.nanoTime() - startTime;
			if (totalTime < 1000)
				System.out.println("computational time = " + totalTime + " ns");
			else if (totalTime < 1000_000)
				System.out.println("computational time = " + (totalTime/1000) + " us");
			else if (totalTime < 1000_000_000)
				System.out.println("computational time = " + (totalTime/1000_000) + " ms");
			else {
				long seconds = (totalTime/1000_000_000);
				if (seconds < 60)
					System.out.println("computational time = " + seconds + " s");
				else {
					int second = (int)(seconds%60);
					long minutes = seconds/60;
					if (minutes < 60)
						System.out.println("computational time = " + minutes + " m : "+ second + " s");
					else {
						int minute = (int)(minutes%50);
						long hours = minutes/60;
						System.out.println("computational time = " + hours + " h : " + minute + " m : "+ second + " s");
					}
				}
			}
		}
		System.out.println("End of Algorithm");
	}
	
/*********** HASH CALCULATING BELOW - READ AT YOUR OWN RISK ***************/
	public static String calculateHash(String input) {
		try {
			return getSHA(input);
		}
		//For specifying wrong message digest algorithms  
        catch (NoSuchAlgorithmException e) {  
            System.out.println("Exception thrown for incorrect algorithm: " + e);  
        }
		return null;
	}
	
	
	/******* CODE BELOW IS FOR GENERATING SHA-256 HASH VALUE IN HEX *********/
	
	//sources: https://www.baeldung.com/sha-256-hashing-java
	// and https://www.geeksforgeeks.org/sha-256-hash-in-java/
	//MessageDigest is java's class for SHA-256 hashing
	public static String getSHA(String input) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		byte[] encodedhash = md.digest(input.getBytes(StandardCharsets.UTF_8));
		
		return bytesToHex(encodedhash);
	}
	
	//source: https://www.baeldung.com/sha-256-hashing-java
	//bytesToHex converts a string of bytes to a string of hex
	private static String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if(hex.length() == 1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

}//end of class