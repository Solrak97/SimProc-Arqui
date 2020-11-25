import java.util.Arrays;

public class Memory {
    public int[] dataMemory;
    public int[] instructionMemory;

    public Memory(){
		dataMemory = new int[96];
		instructionMemory = new int[640];

        Arrays.fill(dataMemory, 1);
    }

	public void check(){
		int index = 0;
		System.out.print("\n\nContenido de memoria de instrucciones\n");
		System.out.print("______________________________________________________\n\n");
		for(int i = 0; i < 32; i++){
			for (int j = 0; j < 20; j++){
					System.out.print(instructionMemory[index++] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("______________________________________________________\n\n\n");
	}

	public void checkData(){
		int index = 0;
		System.out.print("\n\nContenido de memoria de datos\n");
		System.out.print("______________________________________________________\n\n");
		for(int i = 0; i < 8; i++){
			for (int j = 0; j < 12; j++){
					System.out.print(dataMemory[index++] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("______________________________________________________\n\n\n");
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
