
public class BufferMessenger{
	bool availableSpace;
	bool blockInBuffer;
	bool copyToBufferInProgress;
	bool copyToCacheInProgress;

//Pasar esto a memoryBlock luego
	int copyBlock;

	public BufferMessenger(){
		availableSpace = false;
		blockInBuffer = false;
		copyToBufferInProgress = false;
		copyToCacheInProgress = false;
	}

	public synchronized bool isBufferAvailable(){
		return availableSpace;
	}

	public synchronized bool isBlockInBuffer(int blockNum){
		return blockInBuffer;
	}

	public synchronized void copyCacheToMessenger(){
		
	}

	public synchronized void copyMessengerToCache(){

	}

	public synchronized void copyBufferToMessenger(){

	}

	public synchronized void copyMessengerToBuffer(){

	}

}
