import java.util.Arrays;


public class Cache {
    String Bloque[][];
    int instructionsCache[][];
    String instructionsCacheStatus[];
    int dataCache[][];
    String dataCacheStatus[];

/*
    public Cache(){
        Bloque = new int[4][4]; // Define que intArray almacenará 10 valores enteros
        //String cache = new Bloque[4][4];
        Arrays.fill(cache, new Bloque());

        instructionsCache = new int[2][8];
        instructionsCache = {   { 0, 0, 0, 0, 0, 0, 0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0 }
                            };

        instructionsCacheStatus = new String[8];
        instructionsCacheStatus =   { "I", "I", "I", "I", "I", "I", "I", "I" };

        dataCache = new String[2][4];
        dataCache =         {   { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 }
                            };

        dataCacheStatus = new String[4];
        dataCacheStatus = { "I", "I", "I", "I" };

    }
*/

    /*
    *   Retorna la posición en que se encuentra un bloque en memoria
    */
    int blockAddress(int memoryAddress, int blockSize){
      return(memoryAddress / blockSize);
    }

    /*
    *   It w
    */
    void cacheWhenStore(){
        //escribimos el valor en la cache
        //status = C
    }

    /*
    *   It w
    */
    void cacheWhenModification(){
        //escribimos el valor en la cache
        //status = M
    }

}
