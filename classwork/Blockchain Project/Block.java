import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException; 

public class Block {
	private int index;
	private String timestamp;
	private String data;
	private String previousHash = "";
	private String hash = "";
	
	public Block(int index, String timestamp, String data, String previousHash) {
		this.index = index;
		this.timestamp = timestamp;
		this.data = data;
		this.previousHash = previousHash;
		this.hash = this.calculateHash();
	}
	
	// THIS BLOCK IS IMMUTABLE!
	public String getHash() {
		return this.hash;
	}
	
	public String getTimestamp() {
		return this.timestamp;
	}
	
	public String getPreviousHash() {
		return this.previousHash;
	}
	
	public String getData() {
		return this.data;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public String getAllData() {
		return this.index + this.previousHash + this.timestamp + this.data;
	}
	
	@Override
	public String toString() {
		String output = "";
		output += "\nPrevious Hash = " + previousHash;
		output += "\nHeight = " + index;
		output += "\nTimestamp = " + timestamp;
		output += "\nData = " + data;
		output += "\nHash = " + hash;
		output += "\n";
		
		return output;
	}
	
	/******* CODE BELOW IS FOR GENERATING SHA-256 HASH VALUE IN HEX *********
	 ******* sources: https://www.baeldung.com/sha-256-hashing-java *********
	 ******* 	  and https://www.geeksforgeeks.org/sha-256-hash-in-java/ ***
	 */
	public String calculateHash() {
		try {
			return getSHA(getAllData());
		}
		//For specifying wrong message digest algorithms  
        catch (NoSuchAlgorithmException e) {  
            System.out.println("Exception thrown for incorrect algorithm: " + e);  
        }
		return null;
	}
	
	//MessageDigest is java's class for SHA-256 hashing
	public static String getSHA(String input) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		byte[] encodedhash = md.digest(input.getBytes(StandardCharsets.UTF_8));
		
		return bytesToHex(encodedhash);
	}
	
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