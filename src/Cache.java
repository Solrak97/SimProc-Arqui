import java.util.Arrays;


public class Cache {
    int Bloque[][];
    String instructionsCache[][];
    String dataCache[][];

/*
    public Cache(){
        Bloque = new int[4][4]; // Define que intArray almacenará 10 valores enteros
        //String cache = new Bloque[4][4];
        instructionsCache = new String[4][8];
        Arrays.fill(cache, new Bloque());
        instructionsCache = {   { "0", "0", "0", "0", "0", "0", "0", "0" },
                                { "0", "0", "0", "0", "0", "0", "0", "0" },
                                { "0", "0", "0", "0", "0", "0", "0", "0" },
                                { "I", "I", "I", "I", "I", "I", "I", "I" }
                            };

        dataCache = new String[4][4];
        dataCache =         {   { "0", "0", "0", "0" },
                                { "0", "0", "0", "0" },
                                { "0", "0", "0", "0" },
                                { "I", "I", "I", "I" }
                            };
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
