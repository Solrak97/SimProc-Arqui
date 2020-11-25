import java.util.Arrays;



public class Cache {
	Cycler cycler;

	DataBlock dataCache[][];
	int[][] dataMap;

	InstructionBlock instructionCache[];

	Memory sharedMemory;
	Messenger messenger;

  //VictimBuffer buffer;


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

//
	public int wordNumber(int address, int size){
		return address % size;
	}



//Carga de instrucciones, toma como parametro la direccion de memoria a cargar
//retorna el valor en esa direccion
//en caso de darse unb fallo de cache, carga el bloque de memoria asociado en el cache
//y luego devuelve el dato requerido
	public int[] loadInstruction(int address){

		//Era necesario que la primer posicion de memoria para instruccion fuera 384
		//Incluso cuando 2 vectores son separados, es asi por instruccion nada más
		address = address - 384;
		int blockNum = blockAddress(address, 8);
		int cacheAddress = blockNum % 8;

		//Fallo de cache, como es por asociacon directa y de lectura, solo es necesario
		//Revisar la direccion
		if((blockNum != instructionCache[cacheAddress].blockNum)){
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
		address = address / 4;
		int blockNum = blockAddress(address, 2);
		int set = blockNum % 2;
		int blockIndex = getBlockIndex(blockNum, set);

		/*
		//Fallo de cache
		if(blockIndex == -1 || dataCache[set][blockIndex].status != DataBlock.Status.C ){
			//blockIndex = getLessUsed(set);
			dataCache[set][blockIndex] = getDataBlockFromMemory(blockNum);
		}
		*/

		//Prueba

		return sharedMemory.dataMemory[address];

		//


		//return dataCache[set][blockIndex].words[wordNumber(address, 2)];
	}

	/*
	*	*****************************************************
	*/
void writeInBuffer(int blockNum){
	DataBlock block = askForBlockInBuffer(blockNum);
	if(block.status == Block.Status.M){
		block = getDataBlockFromMemory(blockNum);
		messenger.sendBufferMessage(block);
	}
}


	public void storeData(int value, int address){
		System.out.println("address: " + address);
		address = address / 4;
		System.out.println("address 2: " + address);
		int blockNum = blockAddress(address, 2);
		int set = blockNum % 2;
		int blockIndex = getBlockIndex(blockNum, set);


		/*

		//Se da un fallo de cache cuando el bloque no se encuentra
		if(blockIndex == -1 || dataCache[set][blockIndex].status != DataBlock.Status.I ){
			blockIndex = getLessUsed(set);
			dataMap[set][blockIndex]++;

			//Si el bloque de memoria a reemplazar fue modificado, este se pasa al buffer victima
			if(da1taCache[set][blockIndex].status == Block.Status.M){
				messenger.sendProcessorMessage(dataCache[set][blockIndex]);
			}

			//Asigna el bloque obtenido en el fallo de cache
			dataCache[set][blockIndex] = cacheFailure(blockNum);
		}
		dataCache[set][blockIndex].words[wordNumber(address, 2)] = value;
		dataCache[set][blockIndex].status = Block.Status.M;

		*/

		sharedMemory.dataMemory[address] = value;
	}

	DataBlock cacheFailure(int blockNum){
		DataBlock block = askForBlockInBuffer(blockNum);
		if (block.status == Block.Status.I){
			block = getDataBlockFromMemory(blockNum);
		}
		return block;
	}

	DataBlock getDataBlockFromMemory(int blockNumber){
		copyMemoryWait();
		int initialIndex = blockNumber * 2;
		int words[] = new int[2];
		for(int i = 0; i < 2; i++){
			words[i] = sharedMemory.dataMemory[initialIndex++];
		}
		return new DataBlock(blockNumber, words);
	}

	DataBlock askForBlockInBuffer(int blockNum){
		DataBlock block = new DataBlock();
		messenger.lookupAsk = true;
		messenger.lookupResponse = false;
		messenger.blockNumberAsk = blockNum;
		cycler.nonCountingCycle();
		cycler.nonCountingCycle();
		if(messenger.lookupResponse){
			block = messenger.lookupBlock;
		}
		return block;
	}

}
