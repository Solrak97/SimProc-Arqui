import java.util.LinkedList;

public class Messenger{
	boolean availableSpace;
	boolean isBussy;
	boolean isOver;
	LinkedList<DataBlock> processorMessages;
	LinkedList<DataBlock> bufferMessages;
	boolean lookupAsk;
	int blockNumberAsk;
	boolean lookupResponse;
	DataBlock lookupBlock;


	public Messenger(){
		availableSpace = false;
		isBussy = false;
		isOver = false;
		lookupAsk = false;
		lookupResponse = false;
		blockNumberAsk = 0;
		bufferMessages = new LinkedList<DataBlock>();
		processorMessages = new LinkedList<DataBlock>();
	}

	public synchronized void finish(){
		isOver = true;
	}

	public synchronized boolean isOver(){
		return isOver;
	}

	public synchronized boolean isBussy(){
		return !bufferMessages.isEmpty() && !processorMessages.isEmpty();
	}

	public synchronized void setAvailableSpace(boolean space){
		availableSpace = space;
	}

	public synchronized void sendProcessorMessage(DataBlock block){
		processorMessages.addLast(block);
	}

	public synchronized void sendBufferMessage(DataBlock block){
		bufferMessages.addLast(block);
	}

	public synchronized DataBlock getProcessorMessage(){
		return processorMessages.getFirst();
	}

	public synchronized DataBlock getBufferMessage(){
		return bufferMessages.getFirst();
	}
}
