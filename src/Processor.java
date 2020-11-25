import java.util.concurrent.CyclicBarrier;


public class Processor implements Runnable{
//Shared variables
	Messenger messenger;
	Memory sharedMemory;

//Paramterized variables
	ContextController context;
	int quantum;

//Simulation stats
	Cycler cycler;

//Simulated variables
	int[] registers;
    int[] instruction;
    int pc;
    int rl;
	Cache cache;
	boolean threadOver;

//Consructor
    Processor(CyclicBarrier cycle, Messenger messenger, ContextController context, int quantum, Memory sharedMemory){
        this.cycler = new Cycler(cycle);
		this.messenger = messenger;
		this.context = context;
		this.quantum = quantum;
		this.sharedMemory = sharedMemory;
        registers = new int[32];
        instruction = new int[4];
        pc = -1;
        rl = -1;

		threadOver = false;
		cache = new Cache(sharedMemory, messenger, cycler);
    }


//Thread Runnable
	@Override
	public void run() {
		while (context.isValid()){

			loadContext();

			for(int q = 0; q < quantum; q++){
				if(!threadOver){
					instruction = cache.loadInstruction(pc);
					//cache.storeData(0,0);
					decoder();
					pc += 4;
				}
				cycler.nextCycle();
			}

			context.saveContext(pc, rl, registers);
			context.nextContext();
			cycler.nextCycle();
		}

		killProgram();

		System.out.print("Total de ciclos en Procesador: " + cycler.getCycles() + "\n\n" +
		"______________________________________________________\n\n\n");
	}

	void loadContext(){
		threadOver = false;
		Context actualContext = context.getContext();
		this.pc = actualContext.pc;
		this.rl = actualContext.rl;
		this.registers = actualContext.registers;
	}

	void killProgram(){
		messenger.finish();
		cycler.nextCycle();
		Thread.currentThread().interrupt();
	}

    public void fetch(){

    }

    void decoder(){
		//Para no hacer muy largas las cosas
		int x1 = instruction[1];
		int x2 = instruction[2];
		int x3 = instruction[3];

        switch(instruction[0]){
            case  5:	//lw
			//Load
                break;

            case 19:	//addi
                //registers[x1] = registers[x2] + x3;
                break;

            case 37:	//sw
			//sw
                break;

            case 51:	//lr
                //Link
                break;

            case 52:	//sc
                //StoreCompare
                break;


            case 56:	//div
				//registers[x1] = registers[x2] / registers[x3];
                break;

            case 71:	//add
                //registers[x1] = registers[x2] + registers[x3];
                break;

            case 72:	//mul
                //registers[x1] = registers[x2] * registers[x3];
                break;

            case 83:	//sub
                //registers[x1] = registers[x2] - registers[x3];
                break;

            case 99:	//beq
                //if(registers[x1] == registers[x2]){
				//	pc += x3 * 4;
				//}
                break;

            case 100:	//bne
			if(registers[x1] != registers[x2]){
				//pc += x3 * 4;
			}
                break;

            case 103:	//jalr
                //registers[x1] = pc;
				//pc = x2 + x3;
                break;

            case 111:	//jal
				//registers[x1] = pc;
				//pc = pc + x3;
				break;

            case 999:	//fin
				threadOver = true;
				context.markAsInvalid();
                break;
        }
    }

}
