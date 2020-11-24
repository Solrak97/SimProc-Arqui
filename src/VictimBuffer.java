import java.util.concurrent.CyclicBarrier;
import java.util.*;

public class VictimBuffer implements Runnable{
	Messenger messenger;

	//Simulation stats
	Cycler cycler;

    LinkedList<Integer> ourBuffer = new LinkedList<Integer>();
    int numberOfBlocksInBuffer;
    private int word1;
    private int word2;

    /*
    *   This is the Buffer Constructor
    */
    public VictimBuffer(CyclicBarrier cycle, Messenger messenger){
        this.cycler = new Cycler(cycle);
		this.messenger = messenger;
    }

    @Override
    public void run() {
		messenger.setAvailableSpace(true);
        while (!messenger.isOver()){
			cycler.nextCycle();
        }

		finishExecution();

		System.out.print("Total de ciclos en Buffer: " + cycler.getCycles() + "\n\n" +
		"______________________________________________________\n\n\n");
    }

	void finishExecution(){
		Thread.currentThread().interrupt();
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
