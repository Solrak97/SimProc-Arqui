import java.util.Arrays;


public class Cache {
    String Bloque[][];
    String instructionsCache[][];
    String dataCache[][];

    public Cache(){
        Bloque = new int[4][4]; // Define que intArray almacenará 10 valores enteros
        //String cache = new Bloque[4][4];
        instructionsCache = new String[4][8];
        dataCache = new String[4][4];
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
