public class Block{
	enum Status{ I, M, C }

	int blockNum;
	Status status;

	Block(int blockNum, Status status){
		this.blockNum = blockNum;
		this.status = status;
	}
}
