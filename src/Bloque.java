import java.util.Arrays;

public class Bloque {
    enum estadoBloque{Invalido, Compartido, Modificado}

    int[] palabras;
    int numeroBloque;
    estadoBloque estado;

    public Bloque(){
        palabras = new int[2];
        Arrays.fill(palabras, 0);
        numeroBloque = -1;
        estado = estadoBloque.Invalido;
    }

    public Bloque(int[] palabras, int numeroBloque){
        this.palabras = palabras;
        this.numeroBloque = numeroBloque;
        estado = estadoBloque.Compartido;
    }

    public void modificaPalabra(int palabra, int indice){
        palabras[indice] = palabra;
        estado = estadoBloque.Modificado;
    }
}
