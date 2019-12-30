import java.io.*;
/*
 	Antonio Palmeros
 	December 12, 2019

 	this enum will gather data on the binary numbers

 	Constructors:
 		Analyze()
 			default constructor

 	Methods:
 		ZeroBits()
 			counts the number of 0.

 		OddParity()
 			checks of the parity of odd

 		EvenParity()
 			checks if the parity is even

 		ZeroValues()
 			checks of its a zero.

 		NegativeValues()
 			checks if the value is negative

 		Palindromes()
 			checks if the value is a palindrome

 		PerfectSquares()
 			checks if the value is a prefect square.

 		public static rangeCount()
 			checks of the x is in range of high and low
*/
public enum Analyze implements AnalyzerInterface
{
	ZeroBits()
	{
		public int count(long x)
		{
			int result;
			result = 0;
			while (x != 0)
			{
				if (x % 2 == 0)
				{
					result = result + 1;
				}
				x = x / 2;
			}
			return result;
		}
	},
	OddParity()
	{
		public int count(long x)
		{
			int result;
			result = 0;
			if (EvenParity.count(x) == 1)
			{
				result = 0;
			}
			else
			{
				result = 1;
			}
			return result;
		}
	},
	EvenParity()
	{
		public int count(long x)
		{
			int result;
			result = 0;
			while (x != 0)
			{
				if (x < 0)
				{
					result = (result + 1) % 2;
				}
				x = x << 1;
			}
			return (result + 1) % 2;
		}
	},
	ZeroValues()
	{
		public int count(long x)
		{
			int result;
			result = 0;
			if (x ==0)
			{
				result = result + 1;
			}
			return result;
		}
	},
	NegativeValues()
	{
		public int count(long x)
		{
			int result;
			result = 0;
			if ( (1 + (x >> 63) - (-x >> 63)) == 0)
			{
				result = 1;
			}
			return result;
		}
	},
	PostitiveValues()
	{
		public int count(long x)
		{
			int result;
			result = 0;
			if (NegativeValues.count(x) == 1)
			{
				result = 0;
			}
			else
			{
				result = 1;
			}
			return result;
		}
	},
	Palindromes()
	{
		public int count(long x)
		{
			int result;
			int dig;
			int reversed;
			int in;
			result = 0;
			reversed = 0;
			in = (int)Math.abs(x);

			while (in > 0)
			{
				dig = (in % 10);
				reversed = (reversed * 10) + dig;
				in = in / 10;
			}
			if (Math.abs(x) == reversed)
			{
				result = 1;
			}
			else
			{
				result = 0;
			}
			return result;
		}
	},
	PerfectSquares()
	{
		public int count(long x)
		{
			int result;
			result = 0;
			for (int i=1; i * i <= x; i++)
			{
				if (( x % i == 0) && (x / i == i))
				{
					result = 1;
				}
				else
				{
					result = 0;
				}
			}
			return result;
		}
	};

	private Analyze()
	{}

	 public int count(long x)
	{
		return 0;
	}

	public static int rangeCount(long low, long high, long x)
	{
		int result;
		if (x >= low && x <= high)
		{
			result = 1;
		}
		else
		{
			result = 0;
		}
		return result;
	}
}