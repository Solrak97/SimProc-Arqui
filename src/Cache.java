import java.util.Arrays;


public class Cache {

	DataBlock dataCache[];
	InstructionBlock instructionCache[];

    public Cache(){
		dataCache = new DataBlock[4];
		instructionCache = new InstructionBlock[8];
    }

    /*

    int blockAddress(int memoryAddress, int blockSize){
      return(memoryAddress / blockSize);
    }


    void cacheWhenStore(int value, int wordNumber, int blockNumber, boolean failureStatus){
        dataCache[wordNumber][blockNumber] = value;
        dataCacheStatus[blockNumber] = "C";
    }


    void cacheWhenModification(int value, int wordNumber, int blockNumber, boolean failureStatus){
        dataCache[wordNumber][blockNumber] = value;
        dataCacheStatus[blockNumber] = "M";
    }

    boolean cacheFailure(){
        boolean failureStatus = false;
        //condición de fallo de caché
        return(failureStatus);
    }
	*/
}
