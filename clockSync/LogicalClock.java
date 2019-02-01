package clockSync;

import java.util.HashMap;

public class LogicalClock {

	String[] threadNames;

	HashMap<String, Long> threadsCounter;
	HashMap<String, CounterObject> threadsCounterObject;
	HashMap<String, Boolean> threadsEndFlag;
	HashMap<String, Boolean> timerFlag;
	

	public LogicalClock(String[] threadNames) {
		this.threadNames = threadNames;
		threadsCounter = new HashMap<String, Long>();
		threadsCounterObject = new HashMap<String, CounterObject>();
		threadsEndFlag = new HashMap<String, Boolean>();
		
		timerFlag = new HashMap<String, Boolean>();
		for (int i = 0; i < threadNames.length; i++) {
			timerFlag.put(threadNames[i], false);
			threadsEndFlag.put(threadNames[i], false);
		}
	}

	public HashMap<String, Boolean> getThreadsEndFlag() {
		return threadsEndFlag;
	}

	public void setThreadsEndFlag(HashMap<String, Boolean> threadsEndFlag) {
		this.threadsEndFlag = threadsEndFlag;
	}

	public HashMap<String, Boolean> getTimerFlag() {
		return timerFlag;
	}

	public void setTimerFlag(HashMap<String, Boolean> timerFlag) {
		this.timerFlag = timerFlag;
	}

	public Long getCounter(String threadName) {
		timerFlag.put(threadName, false);
		return threadsCounter.get(threadName);
	}

	public String[] getThreadNames() {
		return threadNames;
	}

	public HashMap<String, Long> getThreadsCounter() {
		return threadsCounter;
	}

	public CounterObject getThreadCounterObject(String threadName) {
		return threadsCounterObject.get(threadName);
	}

	public void setThreadsCounterObject(HashMap<String, CounterObject> threadsCounterObject) {
		this.threadsCounterObject = threadsCounterObject;
	}

	public void setThreadNames(String[] threadNames) {
		this.threadNames = threadNames;
	}

	public void setCounter(String threadName, CounterObject counterObject) {
		timerFlag.put(threadName, true);
		threadsCounter.put(threadName, counterObject.getCounter());
		threadsCounterObject.put(threadName, counterObject);
	}
	
	public void setEndFlag(String threadName, boolean flag)
	{
		threadsEndFlag.put(threadName, flag);
	}

}
