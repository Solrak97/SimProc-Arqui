import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Processor implements Runnable{
    CyclicBarrier cycle;
	Messenger messenger;

    int[] registers;
    int[] instruction;
    int pc;
    int rl;

    Processor(CyclicBarrier cycle, Messenger messenger, ContextController context){
        this.cycle = cycle;
		this.messenger = messenger;

        registers = new int[32];
        instruction = new int[4];
        pc = -1;
        rl = -1;
    }


    public void fetch(){

    }

    @Override
    public void run() {
		int i = 0;
        while (i < 10){
			i++;

            try {

				cycle.await();

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
            pc += etiq * 4;
        }
    }

    void bne(int x1, int x2, int etiq){
        if(x1 != x2){
            pc += etiq * 4;
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

    void fin(){
        //we shoud end the program here
        System.exit(0);     //I'm not really sure if this is an allwed expression
    }


    void decoder(){
        switch(instruction[0]){
            case 19:
                addi(registers[instruction[1]], registers[instruction[2]], instruction[3]);
                break;
            case 71:
                add(registers[instruction[1]], registers[instruction[2]], registers[instruction[3]]);
                break;
            case 83:
                sub(registers[instruction[1]], registers[instruction[2]], registers[instruction[3]]);
                break;
            case 72:
                mul(registers[instruction[1]], registers[instruction[2]], registers[instruction[3]]);
                break;
            case 56:
                div(registers[instruction[1]], registers[instruction[2]], registers[instruction[3]]);
                break;
            case 5:
                lw(registers[instruction[1]], registers[instruction[2]], registers[instruction[3]]);
                break;
            case 37:
                sw(registers[instruction[1]], registers[instruction[2]], registers[instruction[3]]);
                break;
            case 99:
                beq(registers[instruction[1]], registers[instruction[2]], registers[instruction[3]]);
                break;
            case 100:
                bne(registers[instruction[1]], registers[instruction[2]], registers[instruction[3]]);
                break;
            case 51:
                lr(registers[instruction[1]], registers[instruction[2]]);
                break;
            case 52:
                sc(registers[instruction[1]], registers[instruction[2]], registers[instruction[3]]);
                break;
            case 111:
                jal(registers[instruction[1]], registers[instruction[3]]);
                break;
            case 183:
                jalr(registers[instruction[1]], registers[instruction[2]], registers[instruction[3]]);
                break;
            case 999:
                fin();
                break;
        }
    }

}
