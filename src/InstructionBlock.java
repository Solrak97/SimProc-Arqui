public class InstructionBlock extends Block{
	int words[][];

	InstructionBlock(int blockNum, int[] w1, int[] w2){
		super(blockNum, Status.I);
		words = new int[2][4];
		words[1] = w1;
		words[2] = w2;
	}
}
