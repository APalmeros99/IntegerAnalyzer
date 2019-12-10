import java.util.concurrent.atomic.AtomicBoolean;
import java.util.*;
public class ThreadController
{
	private long endTime;
	private long startTime;
	private AtomicBoolean allStarted;
	private AtomicBoolean allEnded;
	private int numberOfThreads;
	private HashSet<Object> set;

	public ThreadController(int numberOfThreads)
	{
		this.endTime = endTime;
		this.startTime = startTime;
		this.allStarted = new AtomicBoolean(false);
		this.allEnded = new AtomicBoolean(false);
		this.numberOfThreads = numberOfThreads;
		this.set = set;
		if (numberOfThreads > set.size())
		{
			throw new IllegalArgumentException("thread size cannot exceed array size");
		}
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
		if (set.contains(object) == false)
		{
			set.add(object);
			if (getSet().size() == numberOfThreads)
			{
				allStarted.set(true);
				startTime = System.nanoTime();
			}
		}
		return allStarted.get();
	}

	synchronized
	public boolean finished(Object object)
	{
		if (set.contains(object) == true)
		{
			set.remove(object);
			if (getSet().size() == numberOfThreads)
			{
				allEnded.set(true);
				endTime = System.nanoTime();
			}
		}
		return allEnded.get();
	}

	public long getElapsedNanoTime()
	{
		return startTime - endTime;
	}
}
