import java.util.ArrayList;
import java.util.*;

public class ContextController {
	ArrayList<Context> threadContext;

	Iterator<Context> contextIterator;

	public ContextController(){
		threadContext = new ArrayList<Context>();
		contextIterator = threadContext.iterator();
	}

	public void addContext(Context context){
		threadContext.add(context);
	}

	public void check(){
		for(Context c: threadContext){
			System.out.println(c.pc);
		}
	}

	public boolean nextContext(){
		boolean success;
		if(contextIterator.hasNext()){
			System.out.print("W1" );
			contextIterator.next();
			success = true;
		}
		else if (!threadContext.isEmpty()){
			System.out.print("W2" );
			contextIterator = threadContext.iterator();
			success = true;
		}
		else{
			System.out.print("W3" );
			success = false;
		}
		System.out.print("Iteracion de contexto" );
		return success;
	}

	public void copyContext(int pc, int rl, int[] registers){
		//contextIterator.pc = pc;
		//contextIterator.rl = rl;
		//contextIterator.registers = registers;
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
