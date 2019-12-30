import java.util.concurrent.atomic.AtomicBoolean;
import java.util.*;
/*
	Antonio Palmeros
	December 12, 2019

	this class will controll the number of threads in the integer analyzer and calculate
	the time it takes for each thread to complete.

	Instance Variables:
		endTime
			stores a long value for the end nano time.
		startTime
			stores a long value for the start nano time.
		AtomicBoolean allStarted
			stores an Atomic Boolean to check if the thread has started.
		AtomicBoolean allEnded
			stores an Atomic Boolean to check if the thread has ended.
		numberOfThreads
			stores the number of threads.
		HashSet<Object> set = new HashSet<>():
			stores and initiates a new HashSet.

	Constructors:
		ThreadController(int numberOfThreads)
			initiates the number of threads.

	Methods:
		public int getNumberOfThreads()
			returns the number of threads.

		private HashSet<Object> getSet()
			returns the set

		private boolean getAllEnded()
			returns the allEnded instance variable and its boolean value.

		private boolean getAllStarted()
			returns the allStarted instance variable and its boolean value.

		private boolean setAllEnded()
			sets the allEnded instance varaible to false.

		private boolean setAllStarted()
			sets the allEnded instance variavle to false.

		public boolean ready(Object object)
			checks if object is in the set and adds it while it starts the thread.

		public boolean finished(Object object)
			checks if the object is in the set and removes it while it finishes the thread.

		public long getElapsedNanoTime()
			returns the long value of the difference between startTime and endTime.
*/
public class ThreadController
{
	private long endTime;
	private long startTime;
	private AtomicBoolean allStarted;
	private AtomicBoolean allEnded;
	private int numberOfThreads;
	private HashSet<Object> set = new HashSet<>();

	public ThreadController(int numberOfThreads)
	{
		this.numberOfThreads = numberOfThreads;
		allStarted = new AtomicBoolean(false);
		allEnded = new AtomicBoolean(false);
	}

	public int getNumberOfThreads()
	{
		return this.numberOfThreads;
	}

	private HashSet<Object> getSet()
	{
		return this.set;
	}

	private boolean getAllEnded()
	{
		return allEnded.get();
	}

	private boolean getAllStarted()
	{
		return this.allStarted.get();
	}

	private void setAllEnded(boolean x)
	{
		x = false;
	}

	private void setAllStarted(boolean x)
	{
		x = false;
	}

	synchronized
	public boolean ready(Object object)
	{
		if (!getAllStarted())
		{
			set.add(object);
			if (getSet().size() == numberOfThreads)
			{
				allStarted.set(true);
				this.startTime = System.nanoTime();
			}
		}
		return allStarted.get();
	}

	synchronized
	public boolean finished(Object object)
	{
		if (!getAllStarted())
		{
			set.remove(object);
			if (getSet().size() == numberOfThreads)
			{
				allEnded.set(true);
				this.endTime = System.nanoTime();
			}
		}
		return allEnded.get();
	}

	public long getElapsedNanoTime()
	{
		return endTime - startTime;
	}
}
