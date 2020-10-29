public class Memoria {
    int[] memoriaDatos;
    int[] memoriaInstrucciones;

    public Memoria(){
        memoriaDatos = new int[96];
        memoriaInstrucciones = new int[640];

        for (int palabra = 0; palabra < memoriaDatos.length; palabra++) {
            palabra = 1;
        }

        for (int palabra = 0; palabra < memoriaInstrucciones.length; palabra++) {
            palabra = 0;
        }
    }
}
