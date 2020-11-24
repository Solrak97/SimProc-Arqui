import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class Cycler{
	int cycleCounter;
	CyclicBarrier cycle;

	public Cycler(CyclicBarrier cycle){
		cycleCounter = 0;
		this.cycle = cycle;
	}

	public void nextCycle(){
		try {
			cycleCounter++;
			cycle.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public int getCycles(){
		return cycleCounter;
	}
}
