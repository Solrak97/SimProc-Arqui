import java.util.Arrays;

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
}
