import java.util.Arrays;

public class Memoria {
    int[] memoriaDatos;
    int[] memoriaInstrucciones;

    public Memoria(){
        memoriaDatos = new int[96];
        memoriaInstrucciones = new int[640];

        Arrays.fill(memoriaDatos, 1);
    }

    public void fill(){

    }

    /*
    public Bloque traeDatos(int direccion){
        int numeroBloque = direccion / 2;
        return new Bloque(palabras, numeroBloque);
    }


    public Bloque traeInstrucciones(int direccion){

    }
    */
}
