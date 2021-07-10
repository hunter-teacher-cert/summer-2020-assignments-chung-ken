import java.io.*;

public class BlockchainFileDriver {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//set up file writing with filename
		String filename = "chainCopy1.txt";
		FileOutputStream fos = new FileOutputStream(filename);
		PrintWriter pw = new PrintWriter(fos);
		
		//set up blockchain and initial block
		Block firstBlock = new Block(0, "07/24/2020", "Genesis block - MZ", "0");
		Blockchain hunterChain = new Blockchain(new Block(0, "07/01/2020", "Genesis block - MZ", "0"));
		pw.println(hunterChain.getLastBlock());
		
		/**create new Blocks with the following information(parameters):
		 * 1) next index
		 * 2) timestamp
		 * 3) data to be stored
		 * 4) previous hash value
		 *
		 * The Block class will then use all of the given information to
		 * generate a hash value for itself.
		 */
		
		Block nextBlock = new Block(hunterChain.getLastIndex() + 1, "07/02/2020", "Teacher block - JADW", hunterChain.getLastHash());
		hunterChain.addBlock(nextBlock);
		pw.println(hunterChain.getLastBlock());
		
		nextBlock = new Block(hunterChain.getLastIndex() + 1, "07/03/2020", "Teacher block - TM", hunterChain.getLastHash());
		hunterChain.addBlock(nextBlock);
		pw.println(hunterChain.getLastBlock());
		
		nextBlock = new Block(hunterChain.getLastIndex() + 1, "07/04/2020", "Student block - MA", hunterChain.getLastHash());
		hunterChain.addBlock(nextBlock);
		pw.println(hunterChain.getLastBlock());
		
		nextBlock = new Block(hunterChain.getLastIndex() + 1, "07/05/2020", "Teacher block - IA", hunterChain.getLastHash());
		hunterChain.addBlock(nextBlock);
		pw.println(hunterChain.getLastBlock());
		
		nextBlock = new Block(hunterChain.getLastIndex() + 1, "07/06/2020", "Teacher block - LB", hunterChain.getLastHash());
		hunterChain.addBlock(nextBlock);
		pw.println(hunterChain.getLastBlock());
		
		nextBlock = new Block(hunterChain.getLastIndex() + 1, "07/07/2020", "Teacher block - PB", hunterChain.getLastHash());
		hunterChain.addBlock(nextBlock);
		pw.println(hunterChain.getLastBlock());
		
		pw.close();
		System.out.println("Done writing to file: " + filename);
	}
	
}//end of class