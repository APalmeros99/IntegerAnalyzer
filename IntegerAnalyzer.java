import java.io.*;
import java.util.*;
/*
	AntonioPalmeros
	December 12, 2019

	this class will analyze and produce data about integers and their binary value.

	Instance Variables:
		count
			stores a long of count.
		source
			stores an object.
		numberOfThreads
			stores the number of threads.
		threadController
			stores a thread controller object.
		analysisType
			stores an alyzer integer object


	Constructors:
		IntegerAnalyzer(Object source)
			calls the two parameter constructor.

		IntegerAnalzyer(Object source, int numberOfThreads)
			checks of source is a long[] or a File and returns the correct value.

		IntegerAnalyzer(Object source, int numberOfThreads, AnalyzerInterface analysisType)
			calls the two parameter constructor and initiates analysis type and number of threads.

	Methods:
		private int count(long x)
			returns the count but will be overridden.

		private Object getSource()
			returns the source instance variable.

		private long getCount()
			returns the count instance varaible.

		private void updateCount(long x)
			increments count by 1.

		private int countUsing(long x)
			returns the analysis type varaible by calling the count method.

		public void run()
			checks if the object is a long[] or a File and calculates the first and last subscripts
			depending on the number of threads.

		private ThreadController getThreadController()
			returns an instance of thread controller object.

		private Analyze analysisType()
			returns and instance of the analyze object.

		public int getNumberOfThreads()
			returns the number of threads instance variable.

		public long getElapsedNanoTime()
			returns the elapsedNanoTime calculated in thread controller.

		public String getElapsedNanoTimeInSeconds()
			returns the elapsedNanoTime in secons.

		public int getSourceLength()
			returns the length of source.
*/
public class IntegerAnalyzer implements Runnable, AnalyzerInterface
{
	private long count;
	private Object source;
	private int numberOfThreads;
	private ThreadController threadController;
	private AnalyzerInterface analysisType;

	public IntegerAnalyzer(Object source)
	{
		this(source, 1);
	}

	public IntegerAnalyzer(Object source, int numberOfThreads)
	{
		try
		{
			this.source = ((long[])source).clone();
		}
		catch(ClassCastException cce)
		{
			this.source = (File)source;
		}

		if (numberOfThreads > getSourceLength())
		{
			throw new IllegalArgumentException(getClass().getName() + "");
		}
		this.source = source;
		this.numberOfThreads = numberOfThreads;
		this.analysisType = analysisType;
	}

	public IntegerAnalyzer(Object source, int numberOfThreads, AnalyzerInterface analysisType)
	{
		this(source, numberOfThreads);
	}

	public int count(long x)
	{
		return 0;
	}

	private Object getSource()
	{
		return this.source;
	}

	public long getCount()
	{
		return this.count;
	}
	private void updateCount(long x)
	{
		this.count = this.count + x;
	}

	private int countUsing(long x)
	{
		return getAnalysisType().count(x);
	}

	public void run()
	{

		BitCounter[] bitCounter;
		Thread[] thread;
		DataSource[] dataSource;
		int start;
		int end;
		start = 0;
		end = (getSourceLength() / getNumberOfThreads()) - 1;
		dataSource = new ArrayDataSource[getSourceLength()];
		bitCounter = new BitCounter[getSourceLength()];
		thread = new Thread[getSourceLength()];

		try
		{
			try
			{
				for (int i=0; i<getNumberOfThreads(); i++)
				{
					if (i == getNumberOfThreads() - 1 && i != getSourceLength() - 1)
					{
						end = getSourceLength() - 1;
					}
					start = end + 1;
					end = end + ((getSourceLength() / getNumberOfThreads()) - 1);
					dataSource[i] = new ArrayDataSource(start, end, (long[])getSource());
					bitCounter[i] = new BitCounter(dataSource[i]);
					thread[i] = new Thread(bitCounter[i]);
					thread[i].start();

				}
			}
			catch (ClassCastException cce)
			{
				for (int i=0; i < getNumberOfThreads(); i++)
				{
					if (i == getNumberOfThreads() - 1 && i != getSourceLength() - 1)
					{
						end = getSourceLength() -1;
					}
					start = end + 1;
					end = end + ((getSourceLength() / getNumberOfThreads()) - 1);
					dataSource[i] = new FileDataSource(start, end, (File)getSource());
					bitCounter[i] = new BitCounter(dataSource[i]);
					thread[i] = new Thread(bitCounter[i]);
					thread[i].start();
				}

			}
			for (int i=0; i < getNumberOfThreads(); i++)
			{
				thread[i].join();
			}
		}
		catch(IllegalArgumentException iae)
		{
			throw new IllegalArgumentException(iae.getMessage());
		}
		catch(IOException ioe){}
		catch(InterruptedException ie){}
	}

	private ThreadController getThreadController()
	{
		this.threadController = new ThreadController(getNumberOfThreads());
		return this.threadController;
	}

	private AnalyzerInterface getAnalysisType()
	{
		this.analysisType = new IntegerAnalyzer(getSource());
		return this.analysisType;
	}

	public int getNumberOfThreads()
	{
		return this.numberOfThreads;
	}

	public long getElapsedNanoTime()
	{
		return getThreadController().getElapsedNanoTime();
	}

	public String getElapsedTimeInSeconds()
	{
		String x;
		x = "" + (getElapsedNanoTime() / 1000000);
		return  x;
	}

	public int getSourceLength()
	{
		return ((long[])getSource()).length;
	}

	private class BitCounter implements Runnable
	{
		private DataSource dataSource;

		public BitCounter(DataSource dataSource)
		{
			this.dataSource = dataSource;
		}

		public void run()
		{
			long count;
			count = 0;

			while (!getThreadController().ready(this)){}
			while (dataSource.hasNext())
			{
				count = count + IntegerAnalyzer.this.countUsing(dataSource.getNext());
			}
			IntegerAnalyzer.this.updateCount(count);

			getThreadController().finished(this);
		}
	}
}