import java.util.Arrays;
import java.util.LinkedList;        //para el buffer víctima
int blocksInBuffer;
LinkedList<int> ourBuffer = new LinkedList<int>();

public class Cache {
    Bloque[] cache;

    public Cache(){
        cache = new Bloque[8];
        Arrays.fill(cache, new Bloque());
    }

    int blockAddress(int memoryAddress, int blockSize){
      return(memoryAddress / blockSize);
    }

    void cacheFailure(){

    }

    /*
    *   Insertamos bloque de dos palabras (mientras aún haya espacio)
    */
    void insertInVictimBuffer(int newBlock){
        if(8 > blocksInBuffer){
            ourBuffer.addLast(newBlock);
            ++blocksInBuffer;
        }
    }

    /*
    *   Extraemos bloque de dos palabras (mientras se pueda extraer)
    */
    void extractFromVictimBuffer(){
        if(0 < blocksInBuffer){
            ourBuffer.removeLast();
            --blocksInBuffer;
        }
    }


}
