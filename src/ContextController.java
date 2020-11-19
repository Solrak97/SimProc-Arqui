import java.util.LinkedList;

public class ContextController {
	LinkedList<Context> threadContext;

	public ContextController(){
		threadContext = new LinkedList<Context>();
	}
}

class Context {
	int pc;
	int ir;
	int rl;

	int[] registers;

	public Context(int pc, int ir){
		registers = new int[32];
		this.pc = pc;
		this.ir = ir;
		this.rl = 0;
	}
}
