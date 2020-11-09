package client;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import server.NetworkGameInterface;
import server.TicTacData;

public class TicTacToe
{

  private JFrame frame;
  private boolean isCircle;
  private char[][] data;
  private NetworkGameInterface game;
  private String sessionName;
  TicPanel panel;

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
          TicTacToe window= new TicTacToe("","", true);
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
  public TicTacToe(String sessionName, String PlayerName, boolean isCircle)
  {
    this.isCircle= isCircle;
    this.sessionName= sessionName;
    data= new char[3][3];
    initiateConnection();
    initialize(PlayerName);
    Thread t = new Thread(new Transfer(game, panel));
    t.start();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize(String title)
  {
    frame= new JFrame(title);
    frame.setBounds(100, 100, 804, 554);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JLabel lblPlayer= new JLabel("Player 1");
    lblPlayer.setFont(new Font("Dialog", Font.BOLD, 16));
    lblPlayer.setBounds(31, 104, 107, 31);
    frame.getContentPane().add(lblPlayer);

    JLabel lblPlayer_2= new JLabel("Player 2");
    lblPlayer_2.setFont(new Font("Dialog", Font.BOLD, 16));
    lblPlayer_2.setBounds(635, 104, 107, 31);
    frame.getContentPane().add(lblPlayer_2);

    panel= new TicPanel(isCircle, data, game);
    frame.getContentPane().add(panel);
  }

  public void setVisible()
  {
    frame.setVisible(true);
  }

  void initiateConnection()
  {
    String url= "rmi:///";
    try
    {
      game= (server.NetworkGameInterface) Naming.lookup(url + sessionName);
      TicTacData.copy_data(data, game.getData(""));
    } catch (Exception e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}

class TicPanel extends JPanel
{
  static int PANEL_HEIGHT= 300;
  static int PANEL_WIDTH= 400;

  boolean drawCircle;
  char[][] data;
  NetworkGameInterface game;

  public TicPanel(boolean drawCircle, char[][] data, NetworkGameInterface game)
  {
    setBorder(new LineBorder(new Color(64, 64, 64), 3));
    setBounds(175, 100, 400, 300);
    setBackground(Color.gray);
    // MAke tic tac toe lines
    MouseAdapter ma= new clickEvent();
    addMouseListener(ma);
    this.drawCircle= drawCircle;
    this.data= data;
    this.game= game;
  }

  class clickEvent extends MouseAdapter
  {
    @Override
    public void mouseClicked(MouseEvent e)
    {
      // TODO Auto-generated method stub
      super.mouseClicked(e);
      Graphics2D g= (Graphics2D) getGraphics();
      drawCircleOrCross(g, e.getX(), e.getY(), drawCircle);

    }
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    // TODO Auto-generated method stub
    super.paintComponent(g);
    drawGame((Graphics2D) g);
    drawData(data, (Graphics2D) g);
  }

  void drawGame(Graphics2D g)
  {
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setStroke(new BasicStroke(3));
    g.setColor(Color.blue);
    // 2 Vertical lines
    g.drawLine(PANEL_WIDTH / 3, 0, PANEL_WIDTH / 3, PANEL_HEIGHT);
    g.drawLine(PANEL_WIDTH * 2 / 3, 0, PANEL_WIDTH * 2 / 3, PANEL_HEIGHT);
    // 2 Horizonlel lines
    g.drawLine(0, PANEL_HEIGHT / 3, PANEL_WIDTH, PANEL_HEIGHT / 3);
    g.drawLine(0, PANEL_HEIGHT * 2 / 3, PANEL_WIDTH, PANEL_HEIGHT * 2 / 3);

  }

  void drawCircleOrCross(Graphics2D g, int x, int y, boolean drawCir)
  {
    int center_x, center_y;
    center_x= ((x / (PANEL_WIDTH / 3)) * PANEL_WIDTH / 3) + PANEL_WIDTH / 6;
    center_y= ((y / (PANEL_HEIGHT / 3)) * PANEL_HEIGHT / 3) + PANEL_HEIGHT / 6;
    // System.out.println(center_x +" "+center_y);
    if (drawCir)
      drawCenterCircle(g, center_x, center_y);
    else
      drawCenterCross(g, center_x, center_y);
    try
    {
      game.sendData("", data);
    } catch (RemoteException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  void drawCenterCircle(Graphics2D g, int center_x, int center_y)
  {
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setStroke(new BasicStroke(3));
    g.setColor(Color.blue);
    g.drawOval(center_x - 33, center_y - 33, 66, 66);
    data[center_x / (PANEL_WIDTH / 3)][center_y / (PANEL_HEIGHT / 3)]= 'O';
  }

  void drawCenterCross(Graphics2D g, int center_x, int center_y)
  {
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setStroke(new BasicStroke(4));
    g.setColor(Color.red);
    g.drawLine(center_x - 33, center_y - 33, center_x + 33, center_y + 33);
    g.drawLine(center_x + 33, center_y - 33, center_x - 33, center_y + 33);
    data[center_x / (PANEL_WIDTH / 3)][center_y / (PANEL_HEIGHT / 3)]= 'X';
  }

  void drawData(char[][] data, Graphics2D g)
  {
    for (int i= 0; i < 3; i++)
      for (int j= 0; j < 3; j++)
      {
        if ('X' == data[i][j])
        {
          System.out.println("X");
          drawCircleOrCross(g, convertToPixelX(i), convertToPixelY(j), false);
        } else if ('O' == data[i][j])
        {
          System.out.println("O");
          drawCircleOrCross(g, convertToPixelX(i), convertToPixelY(j), true);
        }
      }
  }
  void drawData()
  {
    drawData(data, (Graphics2D)getGraphics());
  }

  int convertToPixelX(int row)
  {
    return row * (PANEL_WIDTH / 3) + 30;
  }

  int convertToPixelY(int column)
  {
    return column * (PANEL_HEIGHT / 3) + 30;
  }
}

class Transfer implements Runnable
{
  NetworkGameInterface game;
  TicPanel panel;

  Transfer( NetworkGameInterface game, TicPanel panel)
  {
    this.game= game;
    this.panel= panel;
  }

  @Override
  public void run()
  {

    try
    {
      while (true)
      {
        char [][]new_data= game.getData("");
        if (!TicTacData.equal(new_data, panel.data))
        {
          TicTacData.copy_data(panel.data, new_data);
          panel.drawData();
          panel.repaint();
          System.out.println("ss");
        }
        Thread.sleep(100);
        
      }
    } catch (Exception e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
