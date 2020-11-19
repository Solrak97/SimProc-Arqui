import java.util.Arrays;


public class Cache {
    Bloque[] cache;

    public Cache(){
        cache = new Bloque[8];
        Arrays.fill(cache, new Bloque());
    }

    /*
    *   Retorna la posición en que se encuentra un bloque en memoria
    */
    int blockAddress(int memoryAddress, int blockSize){
      return(memoryAddress / blockSize);
    }

    /*
    *   It works with the cache failure monitoring
    */
    void cacheFailure(){

    }

}
