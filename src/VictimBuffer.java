import java.util.LinkedList;        //para el buffer víctima

public class Buffer {
    LinkedList<int> ourBuffer = new LinkedList<int>();
    int numberOfBlocksInBuffer;

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
            ourBuffer.removeLast();
            --numberOfBlocksInBuffer;
        }
    }

}
