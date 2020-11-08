package server;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NetworkGameInterface extends Remote
{
  char[][] getData(String id) throws RemoteException;
  void sendData(String id, char[][] data) throws RemoteException;
  void saveGame(String id) throws RemoteException;
}
