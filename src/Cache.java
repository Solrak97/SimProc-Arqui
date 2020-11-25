import java.util.Arrays;



public class Cache {
	Cycler cycler;

	DataBlock dataCache[][];
	int[][] dataMap;

	InstructionBlock instructionCache[];

	Memory sharedMemory;
	Messenger messenger;




    public Cache(Memory sharedMemory, Messenger messenger, Cycler cycler){
		this.cycler = cycler;

		dataCache = new DataBlock[2][2];
		dataMap = new int[2][2];
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 2; j++){
				dataCache[i][j] = new DataBlock();
			}
		}


		instructionCache = new InstructionBlock[8];
		for(int i = 0; i < instructionCache.length; i++){
			instructionCache[i] = new InstructionBlock();
		}

		this.sharedMemory = sharedMemory;
		this.messenger = messenger;
    }




//Funciones de espera en memoria
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




//Funciones para calcular direcciones en memoria
	int blockAddress(int memoryAddress, int blockSize){
	  return(memoryAddress / blockSize);
	}

	public int wordNumber(int address, int size){
		return address % size;
	}



//Carga de instrucciones, toma como parametro la direccion de memoria a cargar
//retorna el valor en esa direccion
//en caso de darse unb fallo de cache, carga el bloque de memoria asociado en el cache
//y luego devuelve el dato requerido
	public int[] loadInstruction(int address){
		int blockNum = blockAddress(address, 8);
		int cacheAddress = blockNum % 8;

		//Fallo de cache
		if((blockNum != instructionCache[cacheAddress].blockNum) || (instructionCache[cacheAddress].status != Block.Status.C)){
			instructionCache[cacheAddress] = getInstructionBlockFromMemory(blockNum);
		}
		int wordNum = wordNumber(address, 8) / 4;
		return instructionCache[cacheAddress].words[wordNum];
	}

//A partir de el numero de bloque, trae este bloque de Memoria
//Esta instruccion toma 24 ciclos para ser ejecutada
	InstructionBlock getInstructionBlockFromMemory(int blockNumber){
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




//Trae el elemento menos usado de todos en el cache de datos
	public int getLessUsed(int set){
		int min = 0;
		for(int i = 0; i < 2; i++){
			if(dataMap[set][min] >= dataMap[set][i]){
				min = i;
			}
		}
		return min;
	}

//Trae el indice del bloque en la cache de datos a partir del numero de bloque
//y el numero de conjunto, se le pasa por parametro para no calcularlo 2 veces
	public int getBlockIndex(int blockNum, int set){
		int index = -1;

		for(int i = 0; i < 2; i++){
			if (dataCache[set][i].blockNum == blockNum){
				index = i;
			}
		}
		return index;
	}


	public int loadData(int address){
		int blockNum = blockAddress(address, 2);
		int set = blockNum % 2;
		int blockIndex = getBlockIndex(blockNum, set);

		//Fallo de cache
		if(blockIndex == -1 || dataCache[set][blockIndex].status != DataBlock.Status.C ){

		}

		
		return 0;
	}

/*


	public void storeData(){

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
*/
}
