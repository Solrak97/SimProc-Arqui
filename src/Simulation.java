import java.util.concurrent.CyclicBarrier;
import java.io.*;

public class Simulation {
    private CyclicBarrier cycle;
	private Messenger messenger;

	ContextController threadContext;
	Memory sharedMemory;
    Thread processor;
    Thread buffer;
	int quantum;

    public Simulation(String[] args){
		messenger = new Messenger();
		threadContext = new ContextController();
		sharedMemory = new Memory();
		quantum = Integer.parseInt(args[0]);

		File[] threadFiles = getThreadFiles(args);
		loadThreads(threadFiles, sharedMemory, threadContext);

		sharedMemory.check();
    	cycle = new CyclicBarrier(2);
    }

	public void loadThreads(File[] threadFiles, Memory mem, ContextController context){
		FileReader fr;
		BufferedReader br;
		StringBuffer sb = new StringBuffer();
		String line;

		int memIndex = 0;

		for(File f: threadFiles){
			context.addContext(new Context(memIndex));
			try{
				fr = new FileReader(f);
				br = new BufferedReader(fr);
				while((line = br.readLine()) != null){
					String[] dataLine = line.split(" ");
					for(int i = 0; i < dataLine.length; i++){
						mem.instructionMemory[memIndex] = Integer.parseInt(dataLine[i]);
						memIndex++;
					}
				}
			}
			catch(IOException e){
				System.out.println("El archivo de un hilillo no fue encontrado");
				System.exit(0);
			}
		}
	}

	public File[] getThreadFiles(String[] args){
		File[] threadList = new File[args.length - 1];
		for(int i = 1; i < args.length; i++){
			threadList[i - 1] = new File(args[i]);
		}
		return threadList;
	}

    public void run(){

    	processor = new Thread(new Processor(cycle, messenger, threadContext, quantum));
    	buffer = new Thread(new VictimBuffer(cycle, messenger));
    	processor.start();
    	buffer.start();
    }
}
