import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class VictimBuffer implements Runnable{
    CyclicBarrier tick;

    public VictimBuffer(CyclicBarrier tick){
        this.tick = tick;
    }

    @Override
    public void run() {
        while (true){
            System.out.println("tic");
            try {
                tick.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
    /*
    LinkedList<Integer> ourBuffer = new LinkedList<Integer>();
    int numberOfBlocksInBuffer;


    *   Insertamos bloque de dos palabras (mientras aún haya espacio)

    void insertInVictimBuffer(int newBlock){    //DUDA CON LUISK DE COMO IMPLEMENTÓ LA CLASE BLOQUE PARA EL INSER EN EL BUFFER
        if(8 > numberOfBlocksInBuffer){
            ourBuffer.addLast(newBlock);
            ++numberOfBlocksInBuffer;
        }
    }

    *   Extraemos bloque de dos palabras (mientras se pueda extraer)

    void extractFromVictimBuffer(){
        if(0 < numberOfBlocksInBuffer){
            ourBuffer.removeLast();
            --numberOfBlocksInBuffer;
        }
    }
*/

}
