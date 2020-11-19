import java.util.concurrent.CyclicBarrier;

public class Simulation {
    private CyclicBarrier tick;

    Thread processor;
    Thread buffer;

    public Simulation(){
    	tick = new CyclicBarrier(2);
    }

    public void run(){
    	processor = new Thread(new Processor(tick));
    	buffer = new Thread(new VictimBuffer(tick));
    	processor.start();
    	buffer.start();
    }
}
