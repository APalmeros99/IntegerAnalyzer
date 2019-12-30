import java.io.*;
/*
	Antonio Palmeros
	December 12, 2019

	this class will calcuate iterations for a value to get to one.

	Instance Variables
		maximumIterations
			stores the max iterations.
		valueCausingMaximumIterations
			stores the value of max iterations.

	Constructors:
		Collatz()
			initiates the instance varaibles and sets them to 0.

	Methods:
		public int count(long x)
			calculates the iterations it takes to get the value to one.

		public int getMaximumIterations()
			returns the instance variable.

		public int getValueCausingMaximumIterations()
			returns the instance variable.
*/
public class Collatz implements AnalyzerInterface
{
	private int maximumIterations;
	private long valueCausingMaximumIterations;

	public Collatz()
	{
		this.maximumIterations = 0;
		this.valueCausingMaximumIterations = 0;
	}

	public int count(long x)
	{
		long hold;
		hold = maximumIterations;
		this.valueCausingMaximumIterations = x;
		while (x != 1)
		{
			if ((x & 1) == 1)
			{
				x = x * 3 + 1;
			}
			else
			{
				x = x / 2;
			}
			maximumIterations = getMaximumIterations() + 1;
		}
		return (int)x;
	}

	public int getMaximumIterations()
	{
		return maximumIterations;
	}

	public long getValueCausingMaximumIterations()
	{
		return valueCausingMaximumIterations;
	}
}