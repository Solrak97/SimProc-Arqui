public class DataBlock extends Block{
	int words[];

	DataBlock(int blockNum, int w1, int w2){
		super(blockNum, Status.I);
		words = new int[2];
		words[1] = w1;
		words[2] = w2;
	}

}
