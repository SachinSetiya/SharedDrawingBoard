package server;

public class PaintArea {
	
	static int DATA_SIZE= 600;
	static String HEADER="600x600\nVersion=1.0\nSachin Kumar\n";
	char[][] data_array;
	PaintArea()
	{
		data_array= new char[DATA_SIZE][DATA_SIZE];
		initializeToZero();
	}
	
	void initializeToZero()
	{
		for(int i= 0; i < DATA_SIZE; i++)
			for(int j= 0; j < DATA_SIZE; j++)
				data_array[i][j]= 0;
	}
	@Override
	public String toString() {
		//String data= new Str;
//		for(int i= 0; i < DATA_SIZE; i++)
//			for(int j= 0; j < DATA_SIZE; j++)
//				data+= data_array[i][j];
		return data_array.toString();
	}

}
