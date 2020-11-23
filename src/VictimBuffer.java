import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.*;

public class VictimBuffer implements Runnable{
    CyclicBarrier cycle;
	Messenger messenger;

	//Simulation stats
	int cycleCounter;

    LinkedList<Integer> ourBuffer = new LinkedList<Integer>();
    int numberOfBlocksInBuffer;
    private int word1;
    private int word2;

    /*
    *   This is the Buffer Constructor
    */
    public VictimBuffer(CyclicBarrier cycle, Messenger messenger){
        this.cycle = cycle;
		this.messenger = messenger;
		this.cycleCounter = 0;
    }

    @Override
    public void run() {
		int i = 0;
		messenger.setAvailableSpace(true);
        while (i < 100){
			i++;
			
			endOfCycle();
        }
		System.out.print("Total de ciclos en Buffer: " + cycleCounter + "\n\n" +
		"______________________________________________________\n\n\n");
    }



	private void endOfCycle(){
		try {
			cycleCounter++;
			cycle.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}


    /*
    *   Insertamos bloque de dos palabras (mientras aún haya espacio)
    */
    void insertInVictimBuffer(int newBlock){    //DUDA CON LUISK DE COMO IMPLEMENTÓ LA CLASE BLOQUE PARA EL INSER EN EL BUFFER
        if(8 > numberOfBlocksInBuffer){
            ourBuffer.addLast(newBlock);
            ++numberOfBlocksInBuffer;
        }
    }

    /*
    *   Extraemos bloque de dos palabras (mientras se pueda extraer)
    */
    void extractFromVictimBuffer(){
        if(0 < numberOfBlocksInBuffer){
            ourBuffer.removeFirst();
            --numberOfBlocksInBuffer;
        }
    }


}
