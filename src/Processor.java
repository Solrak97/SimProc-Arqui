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
					//System.out.println(pc);
					instruction = cache.loadInstruction(pc);
					if(pc == 404){
						sharedMemory.checkData();
					}
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

	void lw(int x1, int x2, int n){
		registers[x1] = cache.loadData(n + registers[x2]);
	}

	void addi(int x1, int x2, int n){
		registers[x1] = registers[x2] + n;
	}

	void sw(int x2, int x1, int n){
		cache.storeData(registers[x1], n + registers[x2]);
	}

	void lr(int x1, int x2){
		registers[x1] = cache.loadData(registers[x2]);
		rl = registers[x2];
	}

	void sc(int x2, int x1, int n){
		if(rl == (n + registers[x2])){
			cache.storeData(registers[x1], n + registers[x2]);
		}
		else{
			registers[x1] = 0;
		}
	}

	void div(int x1, int x2, int x3){
		registers[x1] = registers[x2] / registers[x3];
	}

	void add(int x1, int x2, int x3){
		registers[x1] = registers[x2] + registers[x3];
	}

	void mul(int x1, int x2, int x3){
		registers[x1] = registers[x2] * registers[x3];
	}
	void sub(int x1, int x2, int x3){
		registers[x1] = registers[x2] - registers[x3];
	}

	void beq(int x1, int x2, int n){
		if(registers[x1] == registers[x2]){
			pc += n * 4;
		}
	}

	void bne(int x1, int x2, int n){
		if(registers[x1] != registers[x2]){
			pc += n * 4;
		}
	}

	void jalr(int x1, int x2, int n){
		registers[x1] = pc;
		pc = registers[x2] + n;
	}

	void jal(int x1, int n){
		registers[x1] = pc;
		pc = pc + n;
	}

	void fin(){
		threadOver = true;
		context.markAsInvalid();
	}

    void decoder(){
		//Para no hacer muy largas las cosas

        switch(instruction[0]){
            case  5:	//lw
				lw(instruction[1], instruction[2], instruction[3]);
                break;

            case 19:	//addi
                addi(instruction[1], instruction[2], instruction[3]);
                break;

            case 37:	//sw
				sw(instruction[1], instruction[2], instruction[3]);
                break;

            case 51:	//lr
                lr(instruction[1], instruction[2]);
                break;

            case 52:	//sc
				sc(instruction[1], instruction[2], instruction[3]);
                break;

            case 56:	//div
				div(instruction[1], instruction[2], instruction[3]);
                break;

            case 71:	//add
				add(instruction[1], instruction[2], instruction[3]);
                break;

            case 72:	//mul
                mul(instruction[1], instruction[2], instruction[3]);
                break;

            case 83:	//sub
                sub(instruction[1], instruction[2], instruction[3]);
                break;

            case 99:	//beq
				beq(instruction[1], instruction[2], instruction[3]);
                break;

            case 100:	//bne
				bne(instruction[1], instruction[2], instruction[3]);
                break;

            case 103:	//jalr
				jalr(instruction[1], instruction[2], instruction[3]);
                break;

            case 111:	//jal
				jal(instruction[1], instruction[3]);
				break;

            case 999:	//fin
				fin();
                break;
        }
    }

}
