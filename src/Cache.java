import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class Cache {
	Cycler cycler;
	DataBlock dataCache[];
	InstructionBlock instructionCache[];
	Memory sharedMemory;
	Messenger messenger;
	Queue<Integer> indexQueue;

    public Cache(Memory sharedMemory, Messenger messenger, Cycler cycler){
		this.cycler = cycler;
		dataCache = new DataBlock[4];


		instructionCache = new InstructionBlock[8];
		for(int i = 0; i < instructionCache.length; i++){
			instructionCache[i] = new InstructionBlock();
		}

		indexQueue = new LinkedList<>();
		this.sharedMemory = sharedMemory;
		this.messenger = messenger;
    }



/*
	void cacheMaping(int cacheIndex){
		if(indexQueue.size() < 4){
			indexQueue.addLast(cacheIndex);
		}else if(indexQueue.size() == 4){
			int toBuffer = indexQueue.peek();
			indexQueue.remove();
			indexQueue.addLast(cacheIndex);
		}
	}

*/
	public void copyWait(){
		for(int i = 0; i < 4; i++){
			cycler.nextCycle();
		}
	}

	public void copyMemoryWait(){
		for(int i = 0; i < 24; i++){
			cycler.nextCycle();
		}
	}

	public int[] loadInstruction(int adress){
		int blockNum = blockAddress(adress, 8);
		int cacheAdress = blockNum % 8;
		if((blockNum != instructionCache[cacheAdress].blockNum) || (instructionCache[cacheAdress].status != Block.Status.C)){
			instructionCache[cacheAdress] = getInstructionBlockFromMemory(adress, blockNum);
		}

		int wordNum = wordNumber(adress, 8) / 4;
		return instructionCache[cacheAdress].words[wordNum];
	}

	public int wordNumber(int adress, int size){
		return adress % size;
	}

	public int loadData(){
		int sopa = 0;
		return sopa;
	}

	public void storeData(){

	}



	InstructionBlock getInstructionBlockFromMemory(int memoryAddress, int blockNumber){
		copyMemoryWait();
		int initialIndex = blockNumber * 8;
		int words[][] = new int[2][4];
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 4; j++){
				words[i][j] = sharedMemory.instructionMemory[initialIndex++];
			}
		}
		return new InstructionBlock(blockNumber, words);
	}

    int blockAddress(int memoryAddress, int blockSize){
      return(memoryAddress / blockSize);
    }

/*
	void cacheWhenStore(int wordNumber1, int wordNumber2, int blockNumber, boolean failureStatus){
		if(!failureStatus){
			this.w1 = wordNumber1;
			this.w2 = wordNumber2;
		 	this.status = Status.C;
		}
    }


    void cacheWhenModification(int wordNumber1, int wordNumber2, int blockNumber, boolean failureStatus){
		if(!failureStatus){
			this.w1 = wordNumber1;
			this.w2 = wordNumber2;
		 	this.status = Status.M;
		}
    }

	boolean cacheFailure(){
		return(this.status == "I");
    }

	boolean getWord(int word){
		return(true); //(this.__ )
	}
*/
}
