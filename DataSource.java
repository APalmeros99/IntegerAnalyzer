public abstract class DataSource
/*
 	Antonio Palmeros
 	December 12, 2019

 	this class will increment the current position in the array while its in range of first and last

 	Instance Variables:
 		current
 			stores and int value of the current value.
 		first
 			stores an int value of the first subscript.
 		last
 			stores an int value of the last subscript.

 	Constructors:
 		DataSource(int first, int last)
 			initiates the instance variables.

 	Methods:
 		public int getCurrent()
 			returns the current instance variable.

 		public int getFirst()
 			returns the first instance variable

 		public int getLast()
 		returns the last instance variable.

 		public boolean hasNext()
 			returns the boolean value if current is in range of first and last.

 		public long next()
 			increments current by one if hasNext() is true.

 		public abstract long getNext()
 			abstrace method.

 		public int numberOfBytes()
 			returns 8 for the number of bytes.
*/
{
	private int current;
	private int first;
	private int last;

	public DataSource(int first, int last)
	{
		this.first = first;
		this.last = last;
		this.current = first;
	}

	public int getCurrent()
	{
		return this.current;
	}

	public int getFirst()
	{
		return this.first;
	}

	public int getLast()
	{
		return this.last;
	}

	public boolean hasNext()
	{
		return getCurrent() >= getFirst() && getCurrent() <= getLast();
	}

	public long next()
	{
		long hold;
		hold = current;

		while (hasNext() != false)
		{
			current = getCurrent() + 1;
		}
		return hold;
	}

	public abstract long getNext();

	public static int numberOfBytesPerLong()
	{
		return 8;
	}
}
