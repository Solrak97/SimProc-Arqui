import java.util.ArrayList;
import java.util.*;

public class ContextController {
	ArrayList<Context> threadContext;

	Iterator<Context> contextIterator;

	public ContextController(){
		threadContext = new ArrayList<Context>();
		contextIterator = new threadContext.Iterator();
	}

	public void addContext(Context context){
		threadContext.add(context);
	}

	public void check(){
		for(Context c: threadContext){
			System.out.println(c.pc);
		}
	}

	public boolean nexContext(){
		boolean success;
		if(contextIterator.hasNext()){
			contextIterator.next();
			success = true;
		}
		else if (!threadContext.isEmpty()){
			contextIterator = new threadContext.Iterator();
			success = true;
		}
		else{
			success = false;
		}
		return success;
	}

	public void copyContext(int pc, int rl, int[] registers){
		contextIterator.pc = pc;
		contextIterator.rl = rl;
		contextIterator.registers = registers;
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
