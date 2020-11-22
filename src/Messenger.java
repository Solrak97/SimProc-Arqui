import java.util.LinkedList;

public class Messenger{
	boolean availableSpace;
	boolean isBussy;
	LinkedList<Integer> processorMessages;
	LinkedList<Integer> bufferMessages;


	public Messenger(){
		availableSpace = false;
		isBussy = false;

		bufferMessages = new LinkedList<Integer>();
		processorMessages = new LinkedList<Integer>();
	}

	public synchronized boolean isBussy(){
		return !bufferMessages.isEmpty() && !processorMessages.isEmpty();
	}

	public synchronized void setAvailableSpace(boolean space){
		availableSpace = space;
	}

	public synchronized void sendProcessorMessage(int element){
		processorMessages.addLast(element);
	}

	public synchronized void sendBufferMessage(int element){
		bufferMessages.addLast(element);
	}

	public synchronized int getProcessorMessage(){
		return processorMessages.getFirst();
	}

	public synchronized int getBufferMessage(){
		return bufferMessages.getFirst();
	}
}
