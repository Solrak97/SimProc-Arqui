public class DataBlock extends Block{
	int words[];

	DataBlock(int blockNum, int[] words){
		super(blockNum, Status.C);
		this.words = words;
	}

	DataBlock(){
		super(-1, Status.I);
		this.words = new int[2];
	}

}
