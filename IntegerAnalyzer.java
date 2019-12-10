import java.io.*;
public class IntegerAnalyzer implements Runnable, AnalyzerInterface
{
	private long count;
	private long[] source;
	private int numberOfThreads;
	private ThreadController threadController;
	private AnalyzerInterface analysisType;

	public IntegerAnalyzer(long[] source)
	{
		this.count = count;
		this.source = source;

		if (source == null)
		{
			throw new NullPointerException("source cannot be null");
		}
	}

	public IntegerAnalyzer(long[] source, int numberOfThreads)
	{
		this(source);
		this.numberOfThreads = numberOfThreads;

		if (numberOfThreads > source.length)
		{
			throw new IllegalArgumentException(getClass().getName() + "");
		}
	}

	public IntegerAnalyzer(long[] source, int numberOfThreads, AnalyzerInterface analysisType)
	{
		this(source, numberOfThreads);
		this.analysisType = analysisType;
	}

	public int count(long x)
	{
		return 0;
	}

	private long[] getSource()
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
		//return getAnalysisType().count(x);

		int result;
		result = 0;

		while (x != 0)
		{
			if (x < 0)
			{
				result = result + 1;
			}
			x = x << 1;

		}
		return result;

	}

	public void run()
	{
		BitCounter bitCounter;
		Thread thread;

		System.out.println(this + "start");

		try
		{
			bitCounter = new BitCounter(0, getSource().length - 1);
			thread = new Thread(bitCounter);

			thread.start();
			thread.join();
		}
		catch(InterruptedException ie)
		{
			throw new RuntimeException(ie.getMessage());
		}

		System.out.println(this + "end");
	}

	private ThreadController getThreadController()
	{
		return this.threadController;
	}

	private AnalyzerInterface getAnalysisType()
	{
		return analysisType;
	}

	public int getNumberOfThreads()
	{
		return this.numberOfThreads;
	}

	public long getElapsedNanoTime()
	{
		return threadController.getElapsedNanoTime();
	}

	public String getElapsedTimeInSeconds()
	{
		String x;
		x = "" + (getElapsedNanoTime() / 1000000);
		return  x;
	}

	private class BitCounter implements Runnable
	{
		private int begin;
		private int end;

		public BitCounter(int begin, int end)
		{
			this.begin = begin;
			this.end = end;
		}

		public int begin()
		{
			return this.begin;
		}

		public int end()
		{
			return this.end;
		}

		public void run()
		{
			long count;
			long hold;

			System.out.println(this + "started");
			count = 0;
			while (threadController.ready(this) != false)
			{
				//count = 0;
				for (int i = begin(); i<end(); i++)
				{
					hold = IntegerAnalyzer.this.getSource()[i];
					count = count + IntegerAnalyzer.this.countUsing(hold);
				}
				IntegerAnalyzer.this.updateCount(count);
			}

			threadController.finished(this);

			System.out.println(this + "ended");
		}
	}
}