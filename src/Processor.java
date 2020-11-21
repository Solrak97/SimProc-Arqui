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

    void addi(int x1, int x2, int n){
        x1 = x2 + n;
    }

    void add(int x1, int x2, int x3){
        x1 = x2 + x3;
    }

    void sub(int x1, int x2, int x3){
        x1 = x2 - x3;
    }

    void mul(int x1, int x2, int x3){
        x1 = x2 * x3;
    }

    void div(int x1, int x2, int x3){
        x1 = x2 / x3;
    }

    void lw(int x1, int x2, int n){
        //Carga desde memoria y escribe en el Buffer
        //x1 <-- M[n + x2]
    }

    void sw(int x1, int x2, int n){
        //Guarda en Memoria el valor x1
        //M[n + x2] <-- x1
    }

    void beq(int x1, int x2, int etiq){
        if(x1 == x2){
            //pc += inm*4;
            //pc += etiq * 4;
        }
    }

    void bne(int x1, int x2, int etiq){
        if(x1 != x2){
            //pc += inm*4;
            //pc += etiq * 4;
        }
    }

    void lr(int x1, int x2){
        //x1 = M[x2];
        rl = x2;
    }

    void sc(int x1, int x2, int n){
            if(rl == (n + x2)){
                //M(n + x2) = x1;
            }else{
                x1 = 0;
            }
    }

    void jal(int x1, int n){
        x1 = pc;
        pc = pc + n;
    }

    void jalr(int x1, int x2, int n){
        x1 = pc;
        pc = x2 + n;
    }

    void FIN(){
        //we shoud end the program here
        System.exit(0);     //I'm not really sure if this is an allwed expression
    }


    void decodificar(){
        switch(instruccion[0]){
            case 19:
                addi(registros[instruccion[1]], registros[instruccion[2]], instruccion[3]);
                break;
            case 71:
                add(registros[instruccion[1]], registros[instruccion[2]], registros[instruccion[3]]);
                break;
            case 83:
                sub(registros[instruccion[1]], registros[instruccion[2]], registros[instruccion[3]]);
                break;
            case 72:
                mul(registros[instruccion[1]], registros[instruccion[2]], registros[instruccion[3]]);
                break;
            case 56:
                div(registros[instruccion[1]], registros[instruccion[2]], registros[instruccion[3]]);
                break;
            case 5:
                lw(registros[instruccion[1]], registros[instruccion[2]], registros[instruccion[3]]);
                break;
            case 37:
                sw(registros[instruccion[1]], registros[instruccion[2]], registros[instruccion[3]]);
                break;
            case 99:
                beq(registros[instruccion[1]], registros[instruccion[2]], registros[instruccion[3]]);
                break;
            case 100:
                bne(registros[instruccion[1]], registros[instruccion[2]], registros[instruccion[3]]);
                break;
            case 51:
                lr(registros[instruccion[1]], registros[instruccion[2]]);
                break;
            case 52:
                sc(registros[instruccion[1]], registros[instruccion[2]], registros[instruccion[3]]);
                break;
            case 111:
                jal(registros[instruccion[1]], registros[instruccion[3]]);
                break;
            case 183:
                jalr(registros[instruccion[1]], registros[instruccion[2]], registros[instruccion[3]]);
                break;
            case 999:
                //FIN
                break;
        }
    }

}
