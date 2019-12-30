import java.io.*;
import java.util.*;
/*
	Antonio Palmeros
	December 12, 2019

	this class will analyze a RandomAccesFile and get the first and last subscripts of the file

	Instance Variables
		source
			stores an object of RandomAccessFile

	Constructors:
		public FileDataSource(int first, int last, File, source)
			calls dataSource and initiates source

	Methods:
		public long getNext()
			increments source by number of bytes(8)

		public boolean hasNext()
			checks if hasNext() is true and closes the file
*/
public class FileDataSource extends DataSource
{
	private RandomAccessFile source;

	public FileDataSource(int first, int last, File source) throws IOException
	{
		super(first, last);
		this.source = new RandomAccessFile(source, "r");
		this.source.seek(first);
	}

	public long getNext()
	{
		try
		{
			return source.readLong() + numberOfBytesPerLong();
		}
		catch(IOException iae)
		{
			throw new IllegalArgumentException(iae.getMessage());
		}
	}

	public boolean hasNext()
	{
		boolean result;
		result = true;

		try
		{
			if(hasNext() == false)
			{
				source.close();
			}
		}
		catch(IOException ioe)
		{
			throw new IllegalArgumentException(ioe.getMessage());
		}
		return result;
	}
}
