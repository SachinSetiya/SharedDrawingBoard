package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.BasicStroke;
import java.awt.Color;

public class TicTacToe
{

  private JFrame frame;

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
          TicTacToe window= new TicTacToe();
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
  public TicTacToe()
  {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize()
  {
    frame= new JFrame();
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

    JPanel panel= new TicPanel();
    frame.getContentPane().add(panel);
  }
}

class TicPanel extends JPanel
{
  static int PANEL_HEIGHT= 300;
  static int PANEL_WIDTH= 400;

  public TicPanel()
  {
    setBorder(new LineBorder(new Color(64, 64, 64), 3));
    setBounds(175, 100, 400, 300);
    setBackground(Color.gray);
    // MAke tic tac toe lines
    MouseAdapter ma= new clickEvent();
    addMouseListener(ma);
    //
  }

  class clickEvent extends MouseAdapter
  {
    @Override
    public void mouseClicked(MouseEvent e)
    {
      // TODO Auto-generated method stub
      super.mouseClicked(e);
      Graphics2D g= (Graphics2D)getGraphics();
      drawCircle(g, e.getX(), e.getY());
    }
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    // TODO Auto-generated method stub
    super.paintComponent(g);
    drawGame((Graphics2D) g);
  }

  void drawGame(Graphics2D g)
  {
    g.setStroke(new BasicStroke(3));
    g.setColor(Color.blue);
    // 2 Vertical lines
    g.drawLine(PANEL_WIDTH / 3, 0, PANEL_WIDTH / 3, PANEL_HEIGHT);
    g.drawLine(PANEL_WIDTH * 2 / 3, 0, PANEL_WIDTH * 2 / 3, PANEL_HEIGHT);
    // 2 Horizonlel lines
    g.drawLine(0, PANEL_HEIGHT / 3, PANEL_WIDTH, PANEL_HEIGHT / 3);
    g.drawLine(0, PANEL_HEIGHT * 2 / 3, PANEL_WIDTH, PANEL_HEIGHT * 2 / 3);
  }

  void drawCircle(Graphics2D g, int x, int y)
  {
    int center_x, center_y;
    center_x= ((x/(PANEL_WIDTH/3)) * PANEL_WIDTH/3) + PANEL_WIDTH/6;
    center_y= ((y/(PANEL_HEIGHT/3)) * PANEL_HEIGHT/3) + PANEL_HEIGHT/6;
  //  System.out.println(center_x +"   "+center_y);
    drawCenterCircle(g, center_x, center_y);
  }

  void drawCross(Graphics2D g, int x, int y)
  {

  }

  void drawCenterCircle(Graphics2D g, int center_x, int center_y)
  {

  }

  void drawCenterCross(Graphics2D g, int center_x, int center_y)
  {

  }
}
