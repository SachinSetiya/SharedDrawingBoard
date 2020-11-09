package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.w3c.dom.css.Counter;

public class NetworkGameImpl extends UnicastRemoteObject implements NetworkGameInterface
{
  /**
   * 
   */
  private static final long serialVersionUID= 1L;
  public char[][] data;

  public NetworkGameImpl() throws RemoteException
  {
    data= new char[3][3];
    initialize('B');
  }
  
  public NetworkGameImpl(char[][] data) throws RemoteException
  {
    this.data= data;
  }
  
  
  public void initialize(char c)
  {
    for (int i= 0; i < 3; i++)
      for (int j= 0; j < 3; j++)
        data[i][j]= c;
  }

  @Override
  public char[][] getData(String id) throws RemoteException
  {
    // TODO Auto-generated method stub
    return data;
  }

  @Override
  public void sendData(String id, char[][] data) throws RemoteException
  {
    TicTacData.copy_data(this.data, data);
  }
  
  @Override
  public void saveGame(String id) throws RemoteException
  {
    // TODO Auto-generated method stub
    
  }

}
