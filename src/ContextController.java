import java.util.ArrayList;

public class ContextController {
	ArrayList<Context> threadContext;

	public ContextController(){
		threadContext = new ArrayList<Context>();
	}

	public void addContext(Context context){
		threadContext.add(context);
	}

	public boolean isEmpty(){
		return threadContext.isEmpty();
	}

	public void check(){
		for(Context c: threadContext){
			System.out.println(c.pc);
		}
	}
}

class Context {
	int pc;
	int rl;

	int[] registers;

	public Context(int pc){
		this.pc = pc;
		this.rl = 0;
		registers = new int[32];
	}

	public void CopyContext(int pc, int rl, int[] regs){
		this.pc = pc;
		this.rl = rl;
		this.registers = regs;
	}
}
