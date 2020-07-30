public class BlockchainDriver {
	
	public static void main(String[] args) {
		
		Blockchain hunterChain = new Blockchain(new Block(0, "07/01/2020", "Genesis block - MZ", "0"));
		System.out.println(hunterChain.getLastBlock());
		
		Block nextBlock = new Block(hunterChain.getLastIndex() + 1, "07/02/2020", "Teacher block - JADW", hunterChain.getLastHash());
		hunterChain.addBlock(nextBlock);
		System.out.println(hunterChain.getLastBlock());
		
		nextBlock = new Block(hunterChain.getLastIndex() + 1, "07/03/2020", "Teacher block - TM", hunterChain.getLastHash());
		hunterChain.addBlock(nextBlock);
		System.out.println(hunterChain.getLastBlock());
		
		nextBlock = new Block(hunterChain.getLastIndex() + 1, "07/04/2020", "Student block - TL", hunterChain.getLastHash());
		hunterChain.addBlock(nextBlock);
		System.out.println(hunterChain.getLastBlock());
		
		nextBlock = new Block(hunterChain.getLastIndex() + 1, "07/05/2020", "Student block - RW", hunterChain.getLastHash());
		hunterChain.addBlock(nextBlock);
		System.out.println(hunterChain.getLastBlock());
		
	}
	
}//end of class

