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
    initializeToZero();
    player1= name1;
    player2= name2;
    this.sessionName= sessionName;
  }

  void initializeToZero()
  {
    for (int i= 0; i < DATA_SIZE; i++)
      for (int j= 0; j < DATA_SIZE; j++)
        data_array[i][j]= 0;
  }

  @Override
  public String toString()
  {
    String data= "" + HEADER;
    data+= "\n"+player1+"\n"+player2;
    for (int i= 0; i < DATA_SIZE; i++)
      for (int j= 0; j < DATA_SIZE; j++)
        data+= data_array[i][j];
    return data;
  }

}
