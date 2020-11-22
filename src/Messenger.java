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

	public synchronized void isBussy(){
		return (!bufferMessages.isEmpty() && !processorMessages.isEmpty());
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
		int message = null;
		if(!processorMessages.isEmpty()){
			message = processMessages.getFirst();
		}
		return message;
	}

	public synchronized int getBufferMessage(){
		int message = null;
		if(!bufferMessages.isEmpty()){
			message = bufferMessages.getFirst();
		}
		return message;
	}
}
