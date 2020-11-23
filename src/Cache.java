import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import DataBlock.java
import InstructionBlock.java


public class Cache {

	DataBlock dataCache[];
	InstructionBlock instructionCache[];
	Memory sharedMemory;
	Messenger messenger;
	Queue<Integer> cacheIndexQueue;


    public Cache(Memory sharedMemory, Messenger messenger){
		dataCache = new DataBlock[4];
		instructionCache = new InstructionBlock[8];
		indexQueue = new LinkedList<>();		//PriorityQueue<>();
		this.sharedMemory = sharedMemory;
		this.messenger = messenger;
    }

	void cacheMaping(int cacheIndex){
		if(indexAmount < 4){
			indexQueue.addLast(cacheIndex);
		}else if (indexAmount == 4) {
			int toBuffer = indexQueue.peek();
			indexQueue.remove();
			indexQueue.addLast(cacheIndex);
		}
	}

    int blockAddress(int memoryAddress, int blockSize){
      return(memoryAddress / blockSize);
    }


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

}
