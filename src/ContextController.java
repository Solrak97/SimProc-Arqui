import java.util.ArrayList;
import java.util.*;

public class ContextController {
	int index;
	ArrayList<Context> threadContext;


	public ContextController(){
		threadContext = new ArrayList<Context>();
		index = 0;
	}

	public void addContext(Context context){
		threadContext.add(context);
	}

	public void check(){
		for(Context c: threadContext){
			System.out.println(c.pc);
		}
	}

	public boolean isValid(){
		boolean isValid = false;
		for(Context context: threadContext){
			if(context.isValid){
				isValid = true;
			}
		}
		return isValid;
	}

	public boolean nextContext(){
		boolean success = false;
		if(isValid()){
			index++;
			while(!getContext().isValid()){
				index++;
			}
			success = true;
		}
		return success;
	}

	public Context getContext(){
		return threadContext.get(index % threadContext.size());
	}

	public void markAsInvalid(){
		getContext().isValid = false;
	}

	public void saveContext(int pc, int rl, int[] registers){
		getContext().CopyContext(pc, rl, registers);
	}
}

class Context {
	boolean isValid;
	int pc;
	int rl;
	int[] registers;

	public Context(int pc){
		isValid = true;
		this.pc = pc;
		this.rl = 0;
		registers = new int[32];
	}

	public boolean isValid(){
		return isValid;
	}

	public void CopyContext(int pc, int rl, int[] regs){
		this.pc = pc;
		this.rl = rl;
		this.registers = regs;
	}
}
