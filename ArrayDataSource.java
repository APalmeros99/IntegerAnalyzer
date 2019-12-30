public class ArrayDataSource extends DataSource
{
/*
	Antonio Palmeros
	December 12, 2019

	this class extends DataSource and adds a long array

	Instance Variables:
		source
			stores a long array.

	Constructors:
		ArrayDataSource(int first, int last, long[] source)
			calls DataSource and initiates source

	Methods:
		public long getNext()
			returns the next method.
*/
	private long[] source;

	public ArrayDataSource(int first, int last, long[] source)
	{
		super(first, last);
		this.source = source;
	}

	public long getNext()
	{
		return source[(int)next() - 1];
	}
}