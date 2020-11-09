package server;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import client.TicTacToe;
import java.awt.Font;

public class ServerFrame
{

  private JFrame frame;
  private JTextField textSessionName;
  ArrayList<TicTacData> paintAreas;
  private JTextField textPlayer1Name;
  private JTextField textPlayer2Name;

  /**
   * Launch the application.
   */
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          ServerFrame window= new ServerFrame();
          window.frame.setVisible(true);
        } catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public ServerFrame()
  {
    initialize();
   // fillData();
    createRegistry();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize()
  {
    frame= new JFrame();
    frame.setBounds(100, 100, 648, 597);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JButton btnNewSession= new JButton("New Session");
    btnNewSession.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if (textSessionName.getText().equals(""))
        {
          JOptionPane.showMessageDialog(null, "Session name is empty");
          return;
        }
        if (textPlayer1Name.getText().equals(""))
        {
          JOptionPane.showMessageDialog(null, "Player1 name is empty");
          return;
        }
        if (textPlayer2Name.getText().equals(""))
        {
          JOptionPane.showMessageDialog(null, "Player2 name is empty");
          return;
        }
        startRmi(textSessionName.getText());
        createNewSession(textSessionName.getText(), textPlayer1Name.getText(), textPlayer2Name.getText());
//        createNewSession("xyz", "One", "Two");

      }
    });
    btnNewSession.setBounds(250, 449, 155, 30);
    frame.getContentPane().add(btnNewSession);

    textSessionName= new JTextField();
    textSessionName.setBounds(398, 159, 114, 19);
    frame.getContentPane().add(textSessionName);
    textSessionName.setColumns(10);

    JLabel lblOrCreateNew= new JLabel("Create new Session");
    lblOrCreateNew.setFont(new Font("Dialog", Font.BOLD, 15));
    lblOrCreateNew.setBounds(250, 78, 211, 25);
    frame.getContentPane().add(lblOrCreateNew);

    JLabel lblEnterSessionName= new JLabel("Enter Session Name");
    lblEnterSessionName.setBounds(143, 159, 204, 15);
    frame.getContentPane().add(lblEnterSessionName);

    JLabel lblEnterPlayerName= new JLabel("Enter Player1 Name");
    lblEnterPlayerName.setBounds(143, 258, 204, 15);
    frame.getContentPane().add(lblEnterPlayerName);

    JLabel lblEnterPlayerName_1= new JLabel("Enter Player2 Name");
    lblEnterPlayerName_1.setBounds(143, 359, 204, 15);
    frame.getContentPane().add(lblEnterPlayerName_1);

    textPlayer1Name= new JTextField();
    textPlayer1Name.setColumns(10);
    textPlayer1Name.setBounds(398, 256, 114, 19);
    frame.getContentPane().add(textPlayer1Name);

    textPlayer2Name= new JTextField();
    textPlayer2Name.setColumns(10);
    textPlayer2Name.setBounds(398, 357, 114, 19);
    frame.getContentPane().add(textPlayer2Name);

    JMenuBar menuBar= new JMenuBar();
    frame.setJMenuBar(menuBar);
  }

  /**
   * Function used for filling data
   */
//  void fillData()
//  {
//    // Fill comboSessions
//    ArrayList<String> list= FileHelper.fileNames(FileHelper.SESSION_FOLDER);
//    for (String str : list)
//    {
//      comboSessions.addItem(str);
//    }
//    paintAreas= new ArrayList<TicTacData>();
//  }

  void createNewSession(String sessionName, String name1, String name2)
  {
    TicTacData data= new TicTacData(sessionName, name1, name2);
  //  FileHelper.createFile(FileHelper.SESSION_FOLDER + "/" + sessionName, data);
//    fillData();
    TicTacToe player1= new TicTacToe(sessionName, name1, true);
    player1.setVisible();
    TicTacToe player2= new TicTacToe(sessionName, name2, false);
    player2.setVisible();
  }

  void startRmi(String sessionName)
  {
    try
    {
      System.out.println("Created session Name " + sessionName);
      NetworkGameImpl game= new NetworkGameImpl();
      Naming.rebind(sessionName, game);
    } catch (Exception e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  void createRegistry()
  {
    try
    {
      LocateRegistry.createRegistry(1099);
    } catch (RemoteException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
