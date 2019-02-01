package clockSync;

class ProcessThread implements Runnable {
    String name;
    Thread t;
    LogicalClock logicalClock;
    CounterObject counterObject;
    boolean processA=false;
    boolean processB=false;
    
    ProcessThread(String threadname, LogicalClock logicalClock) {
    	counterObject =  new CounterObject();
 		
        counterObject.start(); 
        this.name = threadname;
        this.t = new Thread(this, name);
        System.out.println("New Process: " + t);
        this.t.start();
        this.logicalClock = logicalClock;    
           
    } 

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true)
		{
			
			if(logicalClock.getTimerFlag().get(this.name) == false)
			{
					logicalClock.setCounter(this.name,counterObject);
			}
			if(logicalClock.getThreadsEndFlag().get(this.name) == true)
			{
				counterObject.stop();
				break;
			}
			
		}
	
	}

	public int receive() {
		return 0;
		
	}
   
}