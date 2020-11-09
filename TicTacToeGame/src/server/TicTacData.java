package server;

public class TicTacData
{

  static int DATA_SIZE= 3;
  static String HEADER= "3x3\nVersion=1.0\n";
  char[][] data_array;
  String sessionName, player1, player2;

  TicTacData(String sessionName, String name1, String name2)
  {
    data_array= new char[DATA_SIZE][DATA_SIZE];
    initialize(data_array,(char)0);
    player1= name1;
    player2= name2;
    this.sessionName= sessionName;
  }

  public static void initialize(char[][] data_array, char c)
  {
    for (int i= 0; i < DATA_SIZE; i++)
      for (int j= 0; j < DATA_SIZE; j++)
        data_array[i][j]= c;
  }

  public static void copy_data(char[][] dst, char[][] src)
  {
    for (int i= 0; i < DATA_SIZE; i++)
      for (int j= 0; j < DATA_SIZE; j++)
        dst[i][j]= src[i][j];
  }

  public static boolean equal(char[][] a, char[][] b)
  {
    for (int i= 0; i < DATA_SIZE; i++)
      for (int j= 0; j < DATA_SIZE; j++)
        if (a[i][j] != b[i][j])
          return false;
    return true;
  }

  @Override
  public String toString()
  {
    String data= "" + HEADER;
    data+= "\n" + player1 + "\n" + player2;
    data+= getStrData(data_array);
    return data;
  }

  public static String getStrData(char[][] data_array)
  {
    String data= "";
    for (int i= 0; i < DATA_SIZE; i++)
      for (int j= 0; j < DATA_SIZE; j++)
        data+= data_array[i][j];
    return data;
  }

}
