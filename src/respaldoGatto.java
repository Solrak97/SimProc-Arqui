import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class Cache {
	DataBlock dataCache[];
	InstructionBlock instructionCache[];
	Memory sharedMemory;
	Messenger messenger;
	Queue<Integer> indexQueue;

    public Cache(Memory sharedMemory, Messenger messenger){
		dataCache = new DataBlock[4];
		instructionCache = new InstructionBlock[8];
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

	public int[] loadInstruction(){
		int sopa[] = new int[4];
		return sopa;
	}

	public void storeInstruction(){

	}

	public int loadData(int memoryAddress){
		//int dataCache[] = new DataBlock[4];

		int words[2];
		words[0] = sharedMemory.dataMemory[memoryAddress];	//jalar solo un bloque de memoria
		words[1] = sharedMemory.dataMemory[memoryAddress+1];

		return dataBlockLoaded;

	}

	public void storeData(){

	}

	void getInstructionBlockFromMemory(int memoryAddress){
		int initialIndex = blockAddress(memoryAddress, 8) * 8;
		int word1[];
		int word2[];

		return new InstructionBlock();
	}

    int blockAddress(int memoryAddress, int blockSize){
      return(memoryAddress / blockSize);
    }


	// storeData
	void cacheWhenStore(int wordNumber1, int wordNumber2, int blockNumber, boolean failureStatus){
		if(!failureStatus){
			this.w1 = wordNumber1;
			this.w2 = wordNumber2;
		 	this.status = Status.C;
		}
    }

	//modifyData
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

}
