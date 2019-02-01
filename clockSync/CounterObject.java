package clockSync;

public class CounterObject extends Thread{

	long counter = 0;
	
	public CounterObject()
	{
		
	}
	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			
			counter = counter +1;
			
		}
	}
	
	

	
}
