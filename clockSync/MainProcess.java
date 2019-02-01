package clockSync;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;

class ProcessMain implements Runnable {
    String name;
    Thread t;
    LogicalClock logicalClock;
    int counter;
    int noOfProcessThreads;
    CounterObject counterObject;

    ProcessMain(String threadname, LogicalClock logicalClock) {
    	counterObject =  new CounterObject();
        counterObject.start();
        
        this.name = threadname;
        this.t = new Thread(this, name);
        System.out.println("New Main Process: " + t);
        this.logicalClock = logicalClock;
        this.t.start();
        
    }
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int numberIterations = 0;
		while(true)
		{
			logicalClock.setCounter(this.name,counterObject);
			boolean check = receiveTime(this.logicalClock);
			numberIterations = numberIterations + 1;
			if(check)
			{
				for(int i = 0; i < logicalClock.threadNames.length; i++)
				{
					logicalClock.setEndFlag(logicalClock.threadNames[i], true);
				}
				counterObject.stop();
				break;
			}
				
		}
		System.out.println("numberIterations" + numberIterations);
		
	}
	
	private boolean receiveTime(LogicalClock logicalClock) {
		Set<Long>values = new HashSet<Long>();
		while(true)
		{	
		
			if(!logicalClock.getTimerFlag().values().contains(false))
			{
				
				Long[] numbers = logicalClock.getThreadsCounter().values().toArray(new Long[logicalClock.getThreadsCounter().values().size()]);
				
				
				List<Long> list = Arrays.asList(numbers);
				System.out.println(Arrays.toString(list.toArray()));
				int[] num = list.parallelStream().mapToInt(Long::intValue).toArray();
				OptionalDouble averageThreads = Arrays.stream(num).average();
				Double average = averageThreads.getAsDouble();
				System.out.println("Average is "+average + logicalClock.threadNames.length);
				for(int i = 0; i < logicalClock.threadNames.length; i++)
				{
					Long offset = average.intValue() - logicalClock.getCounter(logicalClock.getThreadNames()[i]) ;
					CounterObject counterObject = logicalClock.getThreadCounterObject(logicalClock.getThreadNames()[i]);
					long realValue = counterObject.getCounter();
					long value = realValue+offset;
					
					System.out.println("threadName="+logicalClock.getThreadNames()[i]+" offset="+offset+" realValue+offset="+value);
					values.add(realValue);
					counterObject.setCounter(value);
					logicalClock.setCounter(logicalClock.threadNames[i], counterObject);
				}
				break;
			}
			
		}
		if(values.size()==1)
		{
			return true;
		}
		else
		{
			return false;
		}
			
		
	}
}

public class MainProcess {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		LogicalClock logicalClock = new LogicalClock(new String[] {"A", "B", "C", "D", "Main"});
		ProcessMain processMain = new ProcessMain("Main", logicalClock);
		ProcessThread processThread1 = new ProcessThread("A", logicalClock);
		ProcessThread processThread2 = new ProcessThread("B", logicalClock);
		ProcessThread processThread3 = new ProcessThread("C", logicalClock);
		ProcessThread processThread4 = new ProcessThread("D", logicalClock);
				
		

	}

}
