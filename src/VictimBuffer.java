import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.*;

public class VictimBuffer implements Runnable{
	Messenger messenger;

	//Simulation stats
	Cycler cycler;
	ArrayList<DataBlock> buffer;

    /*
    *   This is the Buffer Constructor
    */

    public VictimBuffer(CyclicBarrier cycle, Messenger messenger){
        this.cycler = new Cycler(cycle);
		this.messenger = messenger;
		buffer = new ArrayList<DataBlock>();
    }

    @Override
    public void run() {
		messenger.setAvailableSpace(true);
        while (!messenger.isOver()){
			responseForBlockInBuffer();
			cycler.nextCycle();
        }

		finishExecution();

		System.out.print("Total de ciclos en Buffer: " + cycler.getCycles() + "\n\n" +
		"______________________________________________________\n\n\n");
    }

	DataBlock lookup(int blockNumber){
		DataBlock block = new DataBlock();
		for(int i = 0; i < buffer.size(); i++){
			if(buffer.get(i).blockNum == blockNumber){
				block = buffer.get(i);
				buffer.remove(i);
			}
		}
		return block;
	}

	void finishExecution(){
		Thread.currentThread().interrupt();
	}


    /*
	void insertInVictimBuffer(int newBlock){    //DUDA CON LUISK DE COMO IMPLEMENTÓ LA CLASE BLOQUE PARA EL INSER EN EL BUFFER
        if(8 > numberOfBlocksInBuffer){
            ourBuffer.addLast(newBlock);
            ++numberOfBlocksInBuffer;
        }
    }

    /*

    void extractFromVictimBuffer(){
        if(0 < numberOfBlocksInBuffer){
            ourBuffer.removeFirst();
            --numberOfBlocksInBuffer;
        }
    }

    */

	void responseForBlockInBuffer(){
		if (messenger.lookupAsk){
			cycler.nonCountingCycle();
			DataBlock block = new DataBlock();
			block = lookup(messenger.blockNumberAsk);
			messenger.lookupAsk = false;

			if (block.status != Block.Status.I){
				block.status = Block.Status.C;
				messenger.lookupResponse = true;
			}
			cycler.nonCountingCycle();
					messenger.lookupBlock = block;
		}
	}
}
