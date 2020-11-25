public class InstructionBlock extends Block{
	int words[][];

	InstructionBlock(int blockNum, int[][] words){
		super(blockNum, Status.C);
		this.words = new int[2][4];
		this.words = words;
	}

	InstructionBlock(){
		super(-1, Status.I);
		this.words = new int[2][4];
	}
}
