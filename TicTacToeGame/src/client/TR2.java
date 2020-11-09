package client;

import java.rmi.Naming;

import server.NetworkGameInterface;
import server.TicTacData;

public class TR2
{
  public static void main(String[] args)
  {
    // TODO Auto-generated method stub
    NetworkGameInterface game;
    String url= "rmi:///";
    try
    {
      game= (NetworkGameInterface) Naming.lookup(url + "tictac");
      System.out.println(TicTacData.getStrData(game.getData("")));
    } catch (Exception e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
