package client;

import java.rmi.Naming;

import server.NetworkGameInterface;
import server.TicTacData;

public class TryRMI
{

  public static void main(String[] args)
  {
    // TODO Auto-generated method stub
    NetworkGameInterface game;
    char [][]data= new char[3][3];
    TicTacData.initialize(data,'Z');
    String url= "rmi:///";
    try
    {
      game= (NetworkGameInterface) Naming.lookup(url + "tictac");
      game.sendData("", data);
      System.out.println(TicTacData.getStrData(game.getData("")));
    } catch (Exception e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
