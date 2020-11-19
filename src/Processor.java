import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Processor implements Runnable{
    CyclicBarrier tick;
    int[] registers;
    int[] instruction;
    int pc;
    int rl;

    Processor(CyclicBarrier tick){
        this.tick = tick;
        registers = new int[32];
        instruction = new int[4];
        pc = -1;
        rl = -1;
    }

    public void fetch(){
      
    }

    @Override
    public void run() {
        while (true){
            System.out.println("toc");
            try {
                tick.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    void decodificar(){
        switch(instruccion[0]){
            case 19: //addi
                registros[instruccion[1]] = registros[instruccion[2]] + instruccion[3];
                break;
            case 71:    //add
                registros[instruccion[1]] = registros[instruccion[2]] + registros[instruccion[3]];
                break;
            case 83:    //sub
                registros[instruccion[1]] = registros[instruccion[2]] - registros[instruccion[3]];
                break;
            case 72:    //mul
                registros[instruccion[1]] = registros[instruccion[2]] * registros[instruccion[3]];
                break;
            case 56:    //div
                registros[instruccion[1]] = registros[instruccion[2]] / registros[instruccion[3]];
                break;
            case 5:     //lw
                break;
            case 37:    //sw
                break;
            case 99:    //beq
                if (registros[instruccion[1]] == registros[instruccion[2]]) pc += instruccion[3] * 4;
                break;
            case 100:   //bne
                if (registros[instruccion[1]] != registros[instruccion[2]]) pc += instruccion[3] * 4;
                break;
            case 51:    //lr
                break;
            case 52:    //sc
                break;
            case 111:   //jal
                registros[1] = pc;
                pc = pc + instruccion[3];
                break;
            case 183:   //jalr
                registros[1] = pc;
                pc = registros[2] + instruccion[3];
                break;
            case 999:   //FIN
                break;
        }
    }
        */
}