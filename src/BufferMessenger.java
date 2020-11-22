
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

	public synchronized void copyCacheToMessenger(int block){
		copyToBufferInProgress = true;
		copyBlock = block;
	}

	public synchronized int copyMessengerToCache(){
		copyToCacheInProgress = false;
	}

	public synchronized void copyBufferToMessenger(){
		copyToBufferInProgress = true;
	}

	public synchronized int copyMessengerToBuffer(){
		copyToCacheInProgress = false;
	}

}
