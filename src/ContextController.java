import java.util.ArrayList;

public class ContextController {
	ArrayList<Context> threadContext;

	public ContextController(){
		threadContext = new ArrayList<Context>();
	}

	public void addContext(Context context){
		threadContext.add(context);
	}

	public void change(){

	}
}

class Context {
	int pc;
	int ir;
	int rl;

	int[] registers;

	public Context(int pc, int ir){
		this.pc = pc;
		this.ir = ir;
		this.rl = 0;
		registers = new int[32];
	}
}
