import java.util.Arrays;
import DataBlock.java
import InstructionBlock.java


public class Cache {

	DataBlock dataCache[];
	InstructionBlock instructionCache[];
	Memory sharedMemory;
	Messenger messenger;



    public Cache(Memory sharedMemory, Messenger messenger){
		dataCache = new DataBlock[4];
		instructionCache = new InstructionBlock[8];
		this.sharedMemory = sharedMemory;
		this.messenger = messenger;
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
        boolean failureStatus = false;

        //condición de fallo de caché

        return(failureStatus);
    }



}
