import java.util.Arrays;


public class Cache {
    String Bloque[][];
    int instructionsCache[][];
    char instructionsCacheStatus[];
    int dataCache[][];
    char dataCacheStatus[];

    public Cache(){
        Bloque = new int[4][4]; // Define que intArray almacenará 10 valores enteros
        //String cache = new Bloque[4][4];
        Arrays.fill(cache, new Bloque());

        instructionsCache = new int[2][8];
        instructionsCache = {   { 0, 0, 0, 0, 0, 0, 0, 0 },
                                { 0, 0, 0, 0, 0, 0, 0, 0 }
                            };

        instructionsCacheStatus = new char[8];
        instructionsCacheStatus =   { 'I', 'I', 'I', 'I', 'I', 'I', 'I', 'I' };

        dataCache = new int[2][4];
        dataCache =         {   { 0, 0, 0, 0 },
                                { 0, 0, 0, 0 }
                            };

        dataCacheStatus = new char[4];
        dataCacheStatus = { 'I', 'I', 'I', 'I' };

    }

    /*
    *   Retorna la posición en que se encuentra un bloque en memoria
    */
    int blockAddress(int memoryAddress, int blockSize){
      return(memoryAddress / blockSize);
    }

    /*
    *   It w
    */
    void cacheWhenStore(int value, int wordNumber, int blockNumber, boolean failureStatus){
        dataCache[wordNumber][blockNumber] = value;
        dataCacheStatus[blockNumber] = "C";
    }

    /*
    *   It w
    */
    void cacheWhenModification(int value, int wordNumber, int blockNumber, boolean failureStatus){
        dataCache[wordNumber][blockNumber] = value;
        dataCacheStatus[blockNumber] = "M";
    }

    boolean cacheFailure(){
        boolean failureStatus = false;
        //condición de fallo de caché
        return(failureStatus);
    }

}
