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

public class ServerFrame
{

  private JFrame frame;
  private JTextField textSessionName;
  private JComboBox comboSessions;
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
    fillData();
    startRmi(textSessionName.getText());
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize()
  {
    frame= new JFrame();
    frame.setBounds(100, 100, 962, 570);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JLabel lblSelectSession= new JLabel("Select Session");
    lblSelectSession.setBounds(79, 34, 133, 15);
    frame.getContentPane().add(lblSelectSession);

    JButton btnConnect= new JButton("Connect Session");
    btnConnect.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        connectSession();
      }
    });
    btnConnect.setBounds(643, 69, 155, 25);
    frame.getContentPane().add(btnConnect);

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
        createNewSession(textSessionName.getText(), textPlayer1Name.getText(), textPlayer2Name.getText());
//        createNewSession("xyz", "One", "Two");
        
      }
    });
    btnNewSession.setBounds(643, 311, 155, 30);
    frame.getContentPane().add(btnNewSession);

    textSessionName= new JTextField();
    textSessionName.setBounds(398, 317, 114, 19);
    frame.getContentPane().add(textSessionName);
    textSessionName.setColumns(10);

    JButton btnDeleteSession= new JButton("Delete Session");
    btnDeleteSession.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        deleteSession();
      }
    });
    btnDeleteSession.setBounds(643, 136, 155, 25);
    frame.getContentPane().add(btnDeleteSession);

    JLabel lblOrCreateNew= new JLabel("Or Create new Session");
    lblOrCreateNew.setBounds(79, 258, 211, 25);
    frame.getContentPane().add(lblOrCreateNew);

    JLabel lblActiveSessions= new JLabel("Avaliable Sessions");
    lblActiveSessions.setBounds(187, 71, 146, 20);
    frame.getContentPane().add(lblActiveSessions);

    JLabel lblEnterSessionName= new JLabel("Enter Session Name");
    lblEnterSessionName.setBounds(129, 319, 204, 15);
    frame.getContentPane().add(lblEnterSessionName);

    comboSessions= new JComboBox();
    // comboSessions.setModel(new DefaultComboBoxModel(new String[] {"ss", "dd"}));
    comboSessions.setBounds(398, 73, 139, 24);
    frame.getContentPane().add(comboSessions);

    JLabel lblEnterPlayerName= new JLabel("Enter Player1 Name");
    lblEnterPlayerName.setBounds(129, 375, 204, 15);
    frame.getContentPane().add(lblEnterPlayerName);

    JLabel lblEnterPlayerName_1= new JLabel("Enter Player2 Name");
    lblEnterPlayerName_1.setBounds(129, 445, 204, 15);
    frame.getContentPane().add(lblEnterPlayerName_1);

    textPlayer1Name= new JTextField();
    textPlayer1Name.setColumns(10);
    textPlayer1Name.setBounds(398, 373, 114, 19);
    frame.getContentPane().add(textPlayer1Name);

    textPlayer2Name= new JTextField();
    textPlayer2Name.setColumns(10);
    textPlayer2Name.setBounds(398, 425, 114, 19);
    frame.getContentPane().add(textPlayer2Name);

    JMenuBar menuBar= new JMenuBar();
    frame.setJMenuBar(menuBar);

    JMenu mnFile= new JMenu("File");
    menuBar.add(mnFile);

    JMenu mnSave= new JMenu("Save All Sessions");
    mnFile.add(mnSave);

    JMenu mnReset= new JMenu("Reset");
    mnFile.add(mnReset);
  }

  /**
   * Function used for filling data
   */
  void fillData()
  {
    // Fill comboSessions
    ArrayList<String> list= FileHelper.fileNames(FileHelper.SESSION_FOLDER);
    for (String str : list)
    {
      comboSessions.addItem(str);
    }
    paintAreas= new ArrayList<TicTacData>();
  }

  void connectSession()
  {
    System.out.println(comboSessions.getSelectedItem());
  }

  void deleteSession()
  {
    FileHelper.deleteFile(FileHelper.SESSION_FOLDER + "/" + comboSessions.getSelectedItem().toString());
    comboSessions.removeAllItems();
    fillData();
  }

  void createNewSession(String sessionName, String name1, String name2)
  {
    TicTacData data= new TicTacData(sessionName, name1, name2);
    FileHelper.createFile(FileHelper.SESSION_FOLDER + "/" + sessionName, data);
    fillData();
    TicTacToe player1= new TicTacToe(sessionName, name1, true);
    player1.setVisible();
    TicTacToe player2= new TicTacToe(sessionName, name2, false);
    player2.setVisible();
  }

  void startRmi(String sessionName)
  {
    try
    {
      LocateRegistry.createRegistry(1099);
      System.out.println("java RMI registry created. with "+sessionName);
      NetworkGameImpl game= new NetworkGameImpl();
      Naming.rebind("tic", game);
    } catch (Exception e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}
