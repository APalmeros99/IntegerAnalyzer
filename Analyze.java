import java.io.*;
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
			long reversed;
			long remainder;
			result = 0;
			reversed = 0;
			remainder = 0;

			while (x != 0)
			{
				remainder = x % 10;
				reversed = reversed * 10 + remainder;
				x = x / 10;
			}
			if (x == reversed)
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
		return 0;
	}

	public static void main(String[] args)
	{
		Analyze[] array;
		long[] source;

		array = Analyze.values();
		source = new long[] { -1, 234, 0, 1, 9, 171, -3993 };

		for (int i=0; i<source.length; i++)
		{
			for (int j=0; j<array.length; j++)
			{
				System.out.println(array[j] + "	input: "
											+ source[i] + "	output: " + array[j].count(source[i]));
			}
			System.out.println();
		}
	}
}