public class Bloque {
    enum estadoBloque{Invalido, Compartido, Modificado}

    int[] palabras;
    int numeroBloque;
    estadoBloque estado;

    public Bloque(){
        palabras = new int[2];
        numeroBloque = -1;
        estado = estadoBloque.Invalido;
    }
}
